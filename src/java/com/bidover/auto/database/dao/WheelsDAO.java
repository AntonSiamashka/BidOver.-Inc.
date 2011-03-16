/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.Wheels;
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
public class WheelsDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public WheelsDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Wheels wheel) {
        boolean addFlag = false;
        if (wheel != null && wheel.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.wheels(title) VALUES('" + wheel.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<Wheels> find(Wheels wheel) {
        List<Wheels> wheels = null;
        String dbRequest = createDBRequest(wheel);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.wheels WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        wheels = new ArrayList<Wheels>();
                    }
                    wheels.add(createWheels(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return wheels;
    }

    public List<Wheels> findAll() {
        List<Wheels> wheels = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.wheels");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    wheels = new ArrayList<Wheels>();
                }
                wheels.add(createWheels(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return wheels;

    }

    public boolean update(Wheels curWheels, Wheels newWheels) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curWheels);
        if (newWheels != null && newWheels.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.wheels SET title='" + newWheels.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(Wheels wheel) {
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

    public Wheels createWheels(ResultSet resultSet) throws SQLException {
        Wheels wheel = new Wheels();
        String id = resultSet.getString("wheels.id");
        String title = resultSet.getString("wheels.title");
        wheel.setId(Integer.valueOf(id));
        wheel.setTitle(title);
        return wheel;
    }

    private String createDBRequest(Wheels wheel) {
        String dbRequest = "";
        if (wheel != null) {
            if (wheel.getId() != null) {
                dbRequest += "id='" + wheel.getId() + "' ";
            }
            if (wheel.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + wheel.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
