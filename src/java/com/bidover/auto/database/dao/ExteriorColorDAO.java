/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.ExteriorColor;
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
public class ExteriorColorDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public ExteriorColorDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(ExteriorColor exteriorColor) {
        boolean addFlag = false;
        if (exteriorColor != null && exteriorColor.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.exterior_color(title) VALUES('" + exteriorColor.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<ExteriorColor> find(ExteriorColor exteriorColor) {
        List<ExteriorColor> exteriorColors = null;
        String dbRequest = createDBRequest(exteriorColor);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.exterior_color WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        exteriorColors = new ArrayList<ExteriorColor>();
                    }
                    exteriorColors.add(createExteriorColor(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return exteriorColors;
    }

    public List<ExteriorColor> findAll() {
        List<ExteriorColor> exteriorColors = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.exterior_color");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    exteriorColors = new ArrayList<ExteriorColor>();
                }
                exteriorColors.add(createExteriorColor(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return exteriorColors;

    }

    public boolean update(ExteriorColor curExteriorColor, ExteriorColor newExteriorColor) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curExteriorColor);
        if (newExteriorColor != null && newExteriorColor.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.drive_train SET title='" + newExteriorColor.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(ExteriorColor exteriorColor) {
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

    public ExteriorColor createExteriorColor(ResultSet resultSet) throws SQLException {
        ExteriorColor exteriorColor = new ExteriorColor();
        String id = resultSet.getString("exterior_color.id");
        String title = resultSet.getString("exterior_color.title");
        exteriorColor.setId(Integer.valueOf(id));
        exteriorColor.setTitle(title);
        return exteriorColor;
    }

    private String createDBRequest(ExteriorColor exteriorColor) {
        String dbRequest = "";
        if (exteriorColor != null) {
            if (exteriorColor.getId() != null) {
                dbRequest += "id='" + exteriorColor.getId() + "' ";
            }
            if (exteriorColor.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + exteriorColor.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
