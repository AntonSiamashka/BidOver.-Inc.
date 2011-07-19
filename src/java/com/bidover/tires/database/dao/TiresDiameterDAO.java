/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.tires.model.bean.TiresDiameter;
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
public class TiresDiameterDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findTiresById;
    private String findAll;
    private static final String ID_COLUMN = "tires_diameter.id";
    private static final String VALUE_COLUMN = "tires_diameter.value";

    public TiresDiameterDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_TIRES_DIAMETER_REQUEST);
        findTiresById = databaseProperties.getProperty(DatabaseProperties.FIND_TIRES_DIAMETER_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_TIRES_DIAMETER_REQUEST);
    }

    public Integer add(TiresDiameter tires) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkEntity(tires)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, tires.getValue());
                preparedStatement.execute();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }
            } catch (SQLException ex) {
                generatedId = null;
            } finally {
                closeAll(resultSet, preparedStatement, connection);
            }
        }
        return generatedId;
    }

    public TiresDiameter findById(int id) {
        TiresDiameter tiresWidth = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findTiresById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tiresWidth = createEntity(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresWidth;
    }

    public List<TiresDiameter> findAll() {
        List<TiresDiameter> tiresList = new ArrayList<TiresDiameter>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TiresDiameter tires = createEntity(resultSet);
                tiresList.add(tires);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresList;
    }

    private boolean checkEntity(TiresDiameter entity) {
        boolean checked = true;
        if (entity == null || entity.getValue() == null) {
            checked = false;
        }
        return checked;

    }

    public static TiresDiameter createEntity(ResultSet resultSet) throws SQLException {
        TiresDiameter tiresWidth = new TiresDiameter();
        Integer id = resultSet.getInt(ID_COLUMN);
        Double value = resultSet.getDouble(VALUE_COLUMN);
        tiresWidth.setId(id);
        tiresWidth.setValue(value);
        return tiresWidth;
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
