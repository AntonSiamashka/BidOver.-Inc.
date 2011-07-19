/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
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
public class WheelsWidthDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findTiresById;
    private String findAll;
    private static final String ID_COLUMN = "wheels_width.id";
    private static final String VALUE_COLUMN = "wheels_width.value";

    public WheelsWidthDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_WHEELS_WIDTH_REQUEST);
        findTiresById = databaseProperties.getProperty(DatabaseProperties.FIND_WHEELS_WIDTH_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_WHEELS_WIDTH_REQUEST);
    }

    public Integer add(WheelsWidth entity) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkEntity(entity)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setDouble(1, entity.getValue());
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

    public WheelsWidth findById(int id) {
        WheelsWidth entity = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findTiresById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = createEntity(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return entity;
    }

    public List<WheelsWidth> findAll() {
        List<WheelsWidth> entityList = new ArrayList<WheelsWidth>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WheelsWidth tires = createEntity(resultSet);
                entityList.add(tires);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return entityList;
    }

    private boolean checkEntity(WheelsWidth entity) {
        boolean checked = true;
        if (entity == null || entity.getValue() == null) {
            checked = false;
        }
        return checked;

    }

    public static WheelsWidth createEntity(ResultSet resultSet) throws SQLException {
        WheelsWidth entity = new WheelsWidth();
        Integer id = resultSet.getInt(ID_COLUMN);
        Double value = resultSet.getDouble(VALUE_COLUMN);
        entity.setId(id);
        entity.setValue(value);
        return entity;
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
