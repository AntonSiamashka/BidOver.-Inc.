/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.model.bean.Bid;
import com.bidover.common.model.bean.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jedai
 */
public class BidHistoryDAO extends BaseDAO {

    private final String BASE = "bid_history";

    public BidHistoryDAO() {
        super();
    }

    public boolean createBidHistory(Integer lotId) {
        String createBidHistory = "CREATE TABLE `bidover_history`.`bid_history_" + lotId + "` ("
                + "`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "`date` BIGINT(20) NOT NULL,"
                + "`user` VARCHAR(45) NOT NULL,"
                + "`bid` DOUBLE NOT NULL,"
                + "  PRIMARY KEY (`id`)"
                + ")"
                + "ENGINE = InnoDB;";
        try {
            executeRequest(createBidHistory);
        } catch (SQLException ex) {
            Logger.getLogger(BidHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean addBid(Bid bid) {
        connection = connectionPool.getConnection();
        try {
            peparedStatement = (PreparedStatement) connection.prepareStatement(
                    "INSERT INTO bidover_history.bid_history_" + bid.getLotId() + "("
                    + "date, "
                    + "user, "
                    + "bid"
                    + ") VALUES(?,?,?)");

            peparedStatement.setLong(1, bid.getDate().getTime());
            peparedStatement.setInt(2, bid.getUser().getId());
            peparedStatement.setDouble(3, bid.getBid());
            peparedStatement.execute();
            peparedStatement.close();
            connectionPool.releaseConnection(connection);

        } catch (SQLException ex) {
            Logger.getLogger(BidHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Double getMaxBid(Integer lotId) {
        Double bid = null;
        if (lotId != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT max(bid) as bid FROM bidover_history.bid_history_" + lotId + " ");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    bid = resultSet.getDouble("bid");
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(BidHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bid;
    }
    
    public List<Bid> findAll(Integer lotId) {
        List<Bid> beans = null;
        try {
            String baseTable = BASE+"_"+lotId;
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_history." + baseTable + " INNER JOIN bidover_db.user on "+ baseTable + ".user=user.id");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    beans = new ArrayList<Bid>();
                }
                beans.add(createBean(resultSet, lotId));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(BidHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beans;

    }

    public Bid createBean(ResultSet resultSet, Integer lotId) throws SQLException {
        Bid bean = new Bid();
        Integer id = resultSet.getInt(BASE + "_" + lotId + ".id");
        Long date = resultSet.getLong(BASE + "_" + lotId + ".date");
        Double bid = resultSet.getDouble(BASE + "_" + lotId + ".bid");
        Integer userId = resultSet.getInt("user.Id");
        String firstName = resultSet.getString("user.firstName");
        String lastName = resultSet.getString("user.lastName");
        String nickName = resultSet.getString("user.nickName");
        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNickName(nickName);
        bean.setId(id);
        bean.setDate(new Date(date));
        bean.setBid(bid);
        bean.setLotId(lotId);
        bean.setUser(user);
        return bean;
    }

}
