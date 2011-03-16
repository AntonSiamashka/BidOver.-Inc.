/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;


import com.bidover.auto.model.bean.DriveTrain;
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
public class DriveTrainDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public DriveTrainDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(DriveTrain driveTrain) {
        boolean addFlag = false;
        if (driveTrain != null && driveTrain.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.drive_train(title) VALUES('" + driveTrain.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<DriveTrain> find(DriveTrain driveTrain) {
        List<DriveTrain> driveTrains = null;
        String dbRequest = createDBRequest(driveTrain);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.drive_train WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        driveTrains = new ArrayList<DriveTrain>();
                    }
                    driveTrains.add(createDriveTrain(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return driveTrains;
    }

    public List<DriveTrain> findAll() {
        List<DriveTrain> driveTrains = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.drive_train");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    driveTrains = new ArrayList<DriveTrain>();
                }
                driveTrains.add(createDriveTrain(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return driveTrains;

    }

    public boolean update(DriveTrain curDriveTrain, DriveTrain newDriveTrain) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curDriveTrain);
        if (newDriveTrain != null && newDriveTrain.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.drive_train SET title='" + newDriveTrain.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(DriveTrain driveTrain) {
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

    public DriveTrain createDriveTrain(ResultSet resultSet) throws SQLException {
        DriveTrain driveTrain = new DriveTrain();
        String id = resultSet.getString("drive_train.id");
        String title = resultSet.getString("drive_train.title");
        driveTrain.setId(Integer.valueOf(id));
        driveTrain.setTitle(title);
        return driveTrain;
    }

    private String createDBRequest(DriveTrain driveTrain) {
        String dbRequest = "";
        if (driveTrain != null) {
            if (driveTrain.getId() != null) {
                dbRequest += "id='" + driveTrain.getId() + "' ";
            }
            if (driveTrain.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + driveTrain.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
