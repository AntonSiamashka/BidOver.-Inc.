/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.tires.database.connectionpool.ConnectionPool;
import com.bidover.tires.model.bean.Wheels;
import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsDiameter;
import com.bidover.tires.model.bean.WheelsED;
import com.bidover.tires.model.bean.WheelsWidth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.tires.properties.DatabaseProperties;

/**
 *
 * @author Jedai
 */
public class WheelsDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findWheelsById;
    private String findAll;
    private String findOther;
    private static final String ID_COLUMN = "wheels.id";
    private static final String WIDTH_COLUMN = "wheels.width";
    private static final String DIAMETER_COLUMN = "wheels.diameter";
    private static final String BCD_COLUMN = "wheels.BCD";
    private static final String ED_COLUMN = "wheels.ED";

    public WheelsDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_WHEELS_REQUEST);
        findWheelsById = databaseProperties.getProperty(DatabaseProperties.FIND_WHEELS_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_WHEELS_REQUEST);
        findOther = databaseProperties.getProperty(DatabaseProperties.FIND_OTHER_WHEELS_REQUEST);
    }

    public Integer addWheels(Wheels wheels) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkWheels(wheels)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, wheels.getId());
                preparedStatement.setInt(2, wheels.getWheelsWidth().getId());
                preparedStatement.setInt(3, wheels.getWheelsDiameter().getId());
                preparedStatement.setInt(4, wheels.getWheelsBCD().getId());
                preparedStatement.setInt(5, wheels.getWheelsED().getId());
                preparedStatement.execute();
                generatedId=wheels.getId();
                /*resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }*/
            } catch (SQLException ex) {
                generatedId = null;
            } finally {
                closeAll(resultSet, preparedStatement, connection);
            }
        }
        return generatedId;
    }

    public Wheels findWheelsById(int id) {
        Wheels wheels = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findWheelsById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                wheels = createWheels(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return wheels;
    }

    public List<Wheels> findAllWheels() {
        List<Wheels> wheelsList = new ArrayList<Wheels>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wheels wheels = createWheels(resultSet);
                wheelsList.add(wheels);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return wheelsList;
    }

    public List<Wheels> find(Wheels wheels) {
        List<Wheels> wheelsList = new ArrayList<Wheels>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            String request = createRequest(wheels);
            preparedStatement = (PreparedStatement) connection.prepareStatement(findOther + " " + request);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Wheels wheelsTemp = createWheels(resultSet);
                wheelsList.add(wheelsTemp);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return wheelsList;
    }

    public static String createRequest(Wheels wheels) {
        StringBuilder requestBuilder = new StringBuilder(200);
        if (wheels != null) {
            if (wheels.getWheelsWidth() != null && wheels.getWheelsWidth().getId() != null) {
                requestBuilder.append(WIDTH_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(wheels.getWheelsWidth().getId());
                requestBuilder.append("' ");
            }
            if (wheels.getWheelsDiameter() != null && wheels.getWheelsDiameter().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(DIAMETER_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(wheels.getWheelsDiameter().getId());
                requestBuilder.append("' ");
            }
            if (wheels.getWheelsBCD() != null && wheels.getWheelsBCD().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(BCD_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(wheels.getWheelsBCD().getId());
                requestBuilder.append("' ");
            }
            if (wheels.getWheelsED() != null && wheels.getWheelsED().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(ED_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(wheels.getWheelsED().getId());
                requestBuilder.append("' ");
            }
        }
        return requestBuilder.toString();
    }

    private boolean checkWheels(Wheels wheels) {
        boolean checked = true;

        return checked;

    }

    public static Wheels createWheels(ResultSet resultSet) throws SQLException {
        Wheels wheels = new Wheels();
        WheelsWidth wheelsWidth = WheelsWidthDAO.createEntity(resultSet);
        WheelsDiameter wheelsDiameter = WheelsDiameterDAO.createEntity(resultSet);
        WheelsBCD wheelsBCD = WheelsBCDDAO.createEntity(resultSet);
        WheelsED wheelsED = WheelsEDDAO.createEntity(resultSet);
        Integer id = resultSet.getInt(ID_COLUMN);
        wheels.setId(id);
        wheels.setWheelsWidth(wheelsWidth);
        wheels.setWheelsDiameter(wheelsDiameter);
        wheels.setWheelsBCD(wheelsBCD);
        wheels.setWheelsED(wheelsED);
        return wheels;
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
