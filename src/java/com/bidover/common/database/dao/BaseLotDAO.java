/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.logger.Logger;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.model.bean.User;
import com.bidover.util.DateSets;
import com.bidover.util.UtilSets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jedai
 */
public abstract class BaseLotDAO<L extends Lot> extends BaseDAO {
    
    private BidHistoryDAO bidHistoryDAO = new BidHistoryDAO();
    
    public Integer add(L lot) {
        int id = addLotInfo(lot);
        lot.setId(id);
        addSpecificInfo(lot);
        return id;
    }
    
    public abstract Integer addSpecificInfo(L lot);
    
    public int addLotInfo(Lot lot) {
        int generatedId = -1;
        ResultSet resultSet = null;
        try {

            // LOCK TABLE
            executeRequest("LOCK TABLES bidover_db.lot WRITE");

            // INSERT RECORD
            String query = "INSERT INTO bidover_db.lot SET " +
                    "distribution_time=" + lot.getDistributionTime() +
                    ", expiration_time=" + lot.getExpirationTime() +
                    ", seller_id=" + lot.getSellerId().getId() +
                //    ", condition_code=" + lot.getCondition().getId() +
                    ", location_code='" + lot.getLocation() + "'" +
                    ", forbid_bidding=" + lot.getForbidBidding() +
                    ", fixed_price=" + lot.getFixedPrice() +
                    ", fixed_price_only=" + lot.getFixedPriceOnly() +
                    ", floor_price=" + lot.getFloorPrice() +
                    ", starting_bid=" + lot.getStartingBid() +
                    ", currency_code='" + lot.getCurrency() + "'" +
                    ", payment_method='" + lot.getPaymentMethod() + "'" +
                    ", payment_instructions='" + lot.getPaymentInstructions() + "'" +
                    ", free_shipping='" + lot.getFreeShipping() + "'" +
                    ", paid_shipping='" + lot.getPaidShipping() + "'" +
                    ", handling_time=" + lot.getHandlingTime() +
                    ", qnt_items_available=" + lot.getItemsQnt() +
                    ", description='" + lot.getDescription() + "'";
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            peparedStatement.execute();
            resultSet = peparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
            closeAll(resultSet, peparedStatement, connection);
         
            bidHistoryDAO.createBidHistory(generatedId); 
            
            // UNLOCK TABLE
            executeRequest("UNLOCK TABLES");

        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return generatedId;
    }
    
    public L createLot(ResultSet resultSet) throws SQLException {
        L lot = createNewInstance();
        populateLotInfo(lot, resultSet);
        populateSpecificInfo(lot, resultSet);
        return lot;
    }
    
    public abstract L createNewInstance();
    
    public void populateLotInfo(Lot lot, ResultSet resultSet)  throws SQLException{
        String id = resultSet.getString("lot.id");
        String sellerId = resultSet.getString("lot.seller_id");
        String forbid_bidding = resultSet.getString("lot.forbid_bidding");
        String location = resultSet.getString("lot.location_code");
        String expirationTime = resultSet.getString("lot.expiration_time");
        String floorPrice = resultSet.getString("lot.floor_price");
        String startingBid = resultSet.getString("lot.starting_bid");
        String fixedPrice = resultSet.getString("lot.fixed_price");
        String fixedPriceOnly = resultSet.getString("lot.fixed_price_only");
        String itemsQnt = resultSet.getString("lot.qnt_items_available");
        String shipCost = resultSet.getString("lot.shipping_cost");
        String currency = resultSet.getString("lot.currency_code");
        String status = resultSet.getString("lot.status");

        User seller = new User();
        lot.setSellerId(seller);
        lot.setId(Integer.valueOf(id));
        lot.setLocation(location);
        lot.setForbidBidding(Byte.valueOf(forbid_bidding));
        lot.setFloorPrice(Double.parseDouble(floorPrice));
        lot.setStartingBid(Double.parseDouble(startingBid));
        lot.setFixedPrice(Double.parseDouble(fixedPrice));
        lot.setFixedPriceOnly(Byte.valueOf(fixedPriceOnly));
        lot.setItemsQnt(Integer.parseInt(itemsQnt));
        lot.setShippingCost(Double.parseDouble(shipCost));
        lot.setCurrency(currency);
        lot.setStatus(Byte.valueOf(status));

        //ConditionDAO conditionDAO = new ConditionDAO();
        //lot.setCondition(conditionDAO.createCondition(resultSet));

        long millisTimeLeft = Long.valueOf(expirationTime) - System.currentTimeMillis();
        lot.setMillisTimeLeft(millisTimeLeft);
        boolean isBiddingClosed = millisTimeLeft < 0 || lot.getStatus() > 0 ? true : false;
        lot.setIsBiddingClosed(isBiddingClosed);
        lot.setExpirationTime(Long.valueOf(expirationTime));

        DateSets dt = new DateSets();
        lot.setFormattedExpirationTime(dt.convMillsToDate(Long.valueOf(expirationTime), "MMM dd, yyyy  HH:mm:ss  z"));
        lot.setFormattedTimeLeft(dt.getTimeLeft(millisTimeLeft));

        UtilSets utl = new UtilSets();
        double stabid = lot.getStartingBid();
        Double currentBid = null;
        double outBid = 0;
        int biddingRecords = 0;
        
        currentBid = bidHistoryDAO.getMaxBid(lot.getId());
        

        if (currentBid != null) {
            outBid = currentBid + utl.getBidInc(currentBid);
        } else {
            outBid = stabid > 0 ? stabid : 0.5;
        }

        lot.setCurrentBid(currentBid);
        lot.setOutBid(outBid);
        lot.setBidHistoryRecords(biddingRecords);

        boolean isFloorPriceReached = currentBid < Double.parseDouble(floorPrice) ? false : true;
        lot.setIsFloorPriceReached(isFloorPriceReached);

        boolean isBiddingPermitted = lot.getForbidBidding() == 0 & lot.getSellerId().getId() != Integer.valueOf(sellerId) ? true : false;
        lot.setIsBiddingPermitted(isBiddingPermitted);

        boolean isLastHour = millisTimeLeft < 3600000 ? true : false;
        lot.setIsLastHour(isLastHour);

//        boolean isCountDownStart = lot.getIsLastHour() & lot.getCurrentBid()>0 & lot.getFixedPrice()==0 ? true : false;
        boolean isCountDownStart = lot.getIsLastHour() & lot.getCurrentBid() > 0 ? true : false;
        lot.setIsCountDownStart(isCountDownStart);
    }
    
    public abstract void populateSpecificInfo(L lot, ResultSet resulSet) throws SQLException;
    
}
