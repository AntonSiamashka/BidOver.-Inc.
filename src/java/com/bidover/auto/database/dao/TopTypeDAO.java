/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.TopType;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;
/**
 *
 * @author Jedai
 */
public class TopTypeDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public TopTypeDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(TopType topType) {
        boolean addFlag = false;
        if (topType != null && topType.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.top_type(title) VALUES('" + topType.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<TopType> find(TopType topType) {
        List<TopType> topTypes = null;
        String dbRequest = createDBRequest(topType);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.top_type WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        topTypes = new ArrayList<TopType>();
                    }
                    topTypes.add(createTopType(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return topTypes;
    }

    public List<TopType> findAll() {
        List<TopType> topTypes = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.top_type");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    topTypes = new ArrayList<TopType>();
                }
                topTypes.add(createTopType(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return topTypes;

    }

    public boolean update(TopType curFuel, TopType newFuel) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curFuel);
        if (newFuel != null && newFuel.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.top_type SET title='" + newFuel.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(TopType topType) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");

            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public TopType createTopType(ResultSet resultSet) throws SQLException {
        TopType topType = new TopType();
        String id = resultSet.getString("top_type.id");
        String title = resultSet.getString("top_type.title");
        topType.setId(Integer.valueOf(id));
        topType.setTitle(title);
        return topType;
    }

    private String createDBRequest(TopType topType) {
        String dbRequest = "";
        if (topType != null) {
            if (topType.getId() != null) {
                dbRequest += "id='" + topType.getId() + "' ";
            }
            if (topType.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + topType.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
