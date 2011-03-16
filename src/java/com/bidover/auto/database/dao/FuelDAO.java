/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.Fuel;
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
public class FuelDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public FuelDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Fuel fuel) {
        boolean addFlag = false;
        if (fuel != null && fuel.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.fuel(title) VALUES('" + fuel.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<Fuel> find(Fuel fuel) {
        List<Fuel> fuels = null;
        String dbRequest = createDBRequest(fuel);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.fuel WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        fuels = new ArrayList<Fuel>();
                    }
                    fuels.add(createFuel(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return fuels;
    }

    public List<Fuel> findAll() {
        List<Fuel> fuels = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.fuel");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    fuels = new ArrayList<Fuel>();
                }
                fuels.add(createFuel(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return fuels;

    }

    public boolean update(Fuel curFuel, Fuel newFuel) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curFuel);
        if (newFuel != null && newFuel.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.fuel SET title='" + newFuel.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(Fuel fuel) {
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

    public Fuel createFuel(ResultSet resultSet) throws SQLException {
        Fuel fuel = new Fuel();
        String id = resultSet.getString("fuel.id");
        String title = resultSet.getString("fuel.title");
        fuel.setId(Integer.valueOf(id));
        fuel.setTitle(title);
        return fuel;
    }

    private String createDBRequest(Fuel fuel) {
        String dbRequest = "";
        if (fuel != null) {
            if (fuel.getId() != null) {
                dbRequest += "id='" + fuel.getId() + "' ";
            }
            if (fuel.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + fuel.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
