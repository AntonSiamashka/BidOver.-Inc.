/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.InteriorColor;
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
public class InteriorColorDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public InteriorColorDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(InteriorColor interiorColor) {
        boolean addFlag = false;
        if (interiorColor != null && interiorColor.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.interior_color(title) VALUES('" + interiorColor.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<InteriorColor> find(InteriorColor interiorColor) {
        List<InteriorColor> interiorColors = null;
        String dbRequest = createDBRequest(interiorColor);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.interior_color WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        interiorColors = new ArrayList<InteriorColor>();
                    }
                    interiorColors.add(createInteriorColor(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return interiorColors;
    }

    public List<InteriorColor> findAll() {
        List<InteriorColor> interiorColors = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.interior_color");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    interiorColors = new ArrayList<InteriorColor>();
                }
                interiorColors.add(createInteriorColor(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return interiorColors;

    }

    public boolean update(InteriorColor curInteriorColor, InteriorColor newInteriorColor) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curInteriorColor);
        if (newInteriorColor != null && newInteriorColor.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.interior_color SET title='" + newInteriorColor.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(InteriorColor interiorColor) {
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

    public InteriorColor createInteriorColor(ResultSet resultSet) throws SQLException {
        InteriorColor interiorColor = new InteriorColor();
        String id = resultSet.getString("interior_color.id");
        String title = resultSet.getString("interior_color.title");
        interiorColor.setId(Integer.valueOf(id));
        interiorColor.setTitle(title);
        return interiorColor;
    }

    private String createDBRequest(InteriorColor interiorColor) {
        String dbRequest = "";
        if (interiorColor != null) {
            if (interiorColor.getId() != null) {
                dbRequest += "id='" + interiorColor.getId() + "' ";
            }
            if (interiorColor.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + interiorColor.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
