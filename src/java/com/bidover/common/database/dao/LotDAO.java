package com.bidover.common.database.dao;

import com.bidover.common.logger.Logger;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.DateSets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class LotDAO extends BaseLotDAO {

    public LotDAO() {
        super();
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
                    "WHERE lot.id=" + lotId);

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

    @Override
    public Lot createNewInstance() {
        return new Lot();
    }

    @Override
    public void populateSpecificInfo(Lot lot, ResultSet resulSet) {
    }

    @Override
    public Integer addSpecificInfo(Lot lot) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
