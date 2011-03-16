package com.bidover.common.database.dao;

import com.bidover.common.database.dao.ConditionDAO;
import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.common.logger.Logger;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.model.bean.User;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.DateSets;
import com.bidover.util.UtilSets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class LotDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public LotDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public int add(Lot lot) {
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
                    ", condition_code=" + lot.getCondition().getId() +
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

            // UNLOCK TABLE
            executeRequest("UNLOCK TABLES");

        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return generatedId;
    }

    public List<Lot> find(Lot lot) {
        List<Lot> lots = null;
        String dbRequest = createDBRequest(lot);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.lot WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        lots = new ArrayList<Lot>();
                    }
                    lots.add(createLotPreview(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return lots;
    }
    /*
    public List<Lot> findAll() {
    List<Lot> lots = null;

    try {
    connection = connectionPool.getConnection();
    peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.lot");
    ResultSet resultSet = peparedStatement.executeQuery();
    while (resultSet.next()) {
    if (resultSet.isFirst()) {
    lots = new ArrayList<Lot>();
    }
    lots.add(createLot(resultSet));
    }
    peparedStatement.close();
    connectionPool.releaseConnection(connection);
    } catch (SQLException ex) {
    Logger.log(ex);
    }

    return lots;

    }
     */

    public Lot getNextLot() {
        int id = 0;
        long expirationTime = 0;
        Lot lot = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.lot WHERE lot.status=0 ORDER BY lot.expiration_time ");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                expirationTime = resultSet.getLong("expiration_time");
                lot = new Lot();
                lot.setId(id);
                lot.setExpirationTime(expirationTime);
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lot;
    }

// my insert
    public Lot getLotByID(int lotId) {
        Lot lot = null;
        try {
            connection = connectionPool.getConnection();
//            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.lot WHERE lot.id='"+lotId+"' ORDER BY lot.id");
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.lot " +
                    "INNER JOIN bidover_db.condition ON lot.condition_code=condition.id WHERE lot.id=" + lotId);

            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                lot = createLot(resultSet);
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lot;
    }

    public int getLastInsertId() {
        int Id = -1;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = peparedStatement.executeQuery();
            resultSet.first();
            Id = resultSet.getInt(1);
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Id;
    }

    public int getLotQnt(Integer lotId) {
        int qnt = 0;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT lot.qnt_items_available FROM bidover_db.lot WHERE lot.id=" + lotId);
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                qnt = resultSet.getInt(1);
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qnt;
    }

    public Double getLotFloorPrice(Integer lotId) {
        double floorPrice = 0;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT lot.floor_price FROM bidover_db.lot WHERE lot.id=" + lotId);
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                floorPrice = resultSet.getInt(1);
            }
            resultSet.close();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return floorPrice;
    }

    public void setLotQnt(Integer lotId, Integer lotQnt) {
        try {
            executeRequest("UPDATE bidover_db.lot SET lot.qnt_items_available=" + lotQnt + " WHERE lot.id=" + lotId);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public void setLotAsSold(Integer lotId) {
        try {
            executeRequest("UPDATE bidover_db.lot SET lot.status=1, lot.qnt_items_available=0 WHERE lot.id=" + lotId);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public void setLotAsDependence(Integer lotId) {
        try {
            executeRequest("UPDATE bidover_db.lot SET lot.status=2 WHERE lot.id=" + lotId);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public void setLotAsNotSold(Integer lotId) {
        try {
            executeRequest("UPDATE bidover_db.lot SET lot.status=3 WHERE lot.id=" + lotId);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    /*    public void bidOver(Integer id) {
    try {
    executeRequest("UPDATE bidover_db.lot SET lot.status=1 WHERE lot.id='" + id + "'");
    } catch (SQLException ex) {
    Logger.log(ex);
    }

    }
     */
    public void closeBidding(Integer lotId) {
        try {
            LotDAO lotDAO = new LotDAO();
            //int lotId = Integer.parseInt(request.getParameter("lotId"));
            int buyerId = 0;
            double currentBid = 0;
            boolean isBidExists = false;

            String fileName = lotId + "/bidhistory.dat";
            FileIOSet fio = new FileIOSet();
            if (fio.fileExists(fileName)) {
                String[] allStrings = fio.readStringsFromFile(fileName);
//                UtilSets utl = new UtilSets();
                int biddingRecords = allStrings.length;
                if (biddingRecords > 0) {
                    String str = allStrings[biddingRecords - 1];
                    String parts[] = str.split(";");
                    buyerId = Integer.parseInt(parts[1]);
                    currentBid = Double.parseDouble(parts[2]);
                    isBidExists = true;
                }
            }

            if (isBidExists) {
                double floorPrice = lotDAO.getLotFloorPrice(lotId);
                boolean isFloorPriceExists = floorPrice > 0 ? true : false;
                boolean isFloorPriceReached = currentBid < floorPrice ? false : true;
                if (isFloorPriceExists) {
                    if (isFloorPriceReached) {
                        // SOLD
                        lotDAO.setLotAsSold(lotId);
                        // DECREMENT BUYER BALANCE
                        // userDAO.setUserBalance(userBalance - fee, userId);
                    } else {
                        // WAITING DECISION
                        lotDAO.setLotAsDependence(lotId);
                    }
                } else {
                    // SOLD
                    lotDAO.setLotAsSold(lotId);
                    // DECREMENT BUYER BALANCE
                    // userDAO.setUserBalance(userBalance - fee, userId);
                }
            } else {
                // NOT SOLD
                lotDAO.setLotAsNotSold(lotId);
            }

            // SEND EMAILS
            // ...

            // DECREMENT QNT AUTOLOT
            //AutoDAO autoDAO = new AutoDAO();
            //MakeDAO makeDAO = new MakeDAO();
            //ModelDAO modelDAO = new ModelDAO();
            // ModificationDAO modificationDAO = new ModificationDAO();
            //Auto auto = autoDAO.findPreviewByLotId(lotId);
            //makeDAO.decrement(auto.getIdMake().getId());
            // modelDAO.decrement(auto.getIdModel().getId());
            //modificationDAO.decrement(auto.getIdModification().getId());

        } catch (Exception ex) {
            Logger.log(ex);
        }

    }

    public void delete(Lot lot) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");
            // TO DO ...
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public Lot createLotPreview(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("lot.id");
        String expirationTime = resultSet.getString("lot.expiration_time");
        String status = resultSet.getString("lot.status");
        Lot lot = new Lot();
        lot.setId(Integer.valueOf(id));
        long millisTimeLeft = Long.valueOf(expirationTime) - System.currentTimeMillis();
        lot.setStatus(Byte.valueOf(status));
        boolean isBiddingClosed = millisTimeLeft < 0 || lot.getStatus() > 0 ? true : false;
        lot.setIsBiddingClosed(isBiddingClosed);
        if (!isBiddingClosed) {
            DateSets dt = new DateSets();
            lot.setFormattedTimeLeft(dt.getTimeLeft(millisTimeLeft));
        }
        return lot;
    }

    public Lot createLot(ResultSet resultSet) throws SQLException {
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
        Lot lot = new Lot();
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

        ConditionDAO conditionDAO = new ConditionDAO();
        lot.setCondition(conditionDAO.createCondition(resultSet));

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
        double currentBid = 0;
        double outBid = 0;
        int biddingRecords = 0;
        String fileName = lot.getId() + "/bidhistory.dat";
        FileIOSet fio = new FileIOSet();
        if (fio.fileExists(fileName)) {
            String[] allStrings = fio.readStringsFromFile(fileName);
            biddingRecords = allStrings.length;
            if (biddingRecords > 0) {
                String str = allStrings[biddingRecords - 1];
                String parts[] = str.split(";");
                currentBid = Double.parseDouble(parts[2]);
                outBid = currentBid + utl.getBidInc(currentBid);
            } else {
                outBid = stabid > 0 ? stabid : 0.5;
            }
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

        return lot;
    }

    private String createDBRequest(Lot lot) {
        String dbRequest = "";
        if (lot != null) {
            if (lot.getId() != null) {
                dbRequest += "lot.id=" + lot.getId();
            }

            if (lot.getSellerId() != null && lot.getSellerId().getId() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "lot.seller_id=" + lot.getSellerId().getId();
            }

            /*            if (lot.getDistributionTime() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.distribution_time=" + lot.getDistributionTime();
            }
            if (lot.getBuyerId() != null && lot.getBuyerId().getId() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.buyer_id=" + lot.getBuyerId().getId();
            }

            if (lot.getStatus() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.status=" + lot.getStatus();
            }
            if (lot.getLotAddDate() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.lot_add_date=" + lot.getLotAddDate();
            }
            if (lot.getLotSaleDate() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.lot_sale_date=" + lot.getLotSaleDate();
            }
            if (lot.getFloorPrice() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.floor_price=" + lot.getFloorPrice();
            }
            if (lot.getShippingCost() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.ship_cost=" + lot.getShippingCost();
            }

            if (lot.getLocation() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.location=" + lot.getLocation();
            }
            if (lot.getSalesDuration() != null) {
            if (!dbRequest.isEmpty()) {
            dbRequest += " AND ";
            }
            dbRequest += "lot.sales_duration=" + lot.getSalesDuration();
            }
             */        }
        return dbRequest;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
            }
        }
        if (connection != null) {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
            }
        }
    }
}
