/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsED;
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
public class WheelsEDDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findTiresById;
    private String findAll;
    private static final String ID_COLUMN = "wheels_ed.id";
    private static final String VALUE_COLUMN = "wheels_ed.value";

    public WheelsEDDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_WHEELS_ED_REQUEST);
        findTiresById = databaseProperties.getProperty(DatabaseProperties.FIND_WHEELS_ED_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_WHEELS_ED_REQUEST);
    }

    public Integer add(WheelsED entity) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkEntity(entity)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, entity.getValue());
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

    public WheelsED findById(int id) {
        WheelsED entity = null;
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

    public List<WheelsED> findAll() {
        List<WheelsED> entityList = new ArrayList<WheelsED>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                WheelsED tires = createEntity(resultSet);
                entityList.add(tires);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return entityList;
    }

    private boolean checkEntity(WheelsED entity) {
        boolean checked = true;
        if (entity == null || entity.getValue() == null) {
            checked = false;
        }
        return checked;

    }

    public static WheelsED createEntity(ResultSet resultSet) throws SQLException {
        WheelsED entity = new WheelsED();
        Integer id = resultSet.getInt(ID_COLUMN);
        String value = resultSet.getString(VALUE_COLUMN);
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
