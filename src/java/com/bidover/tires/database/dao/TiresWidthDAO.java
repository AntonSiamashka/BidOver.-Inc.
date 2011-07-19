/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.tires.model.bean.TiresWidth;
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
public class TiresWidthDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findTiresById;
    private String findAll;
    private static final String ID_COLUMN = "tires_width.id";
    private static final String VALUE_COLUMN = "tires_width.value";

    public TiresWidthDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_TIRES_WIDTH_REQUEST);
        findTiresById = databaseProperties.getProperty(DatabaseProperties.FIND_TIRES_WIDTH_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_TIRES_WIDTH_REQUEST);
    }

    public Integer add(TiresWidth tires) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkTiresWidth(tires)) {
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

    public TiresWidth findById(int id) {
        TiresWidth tiresWidth = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findTiresById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tiresWidth = createTiresWidth(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresWidth;
    }

    public List<TiresWidth> findAll() {
        List<TiresWidth> tiresList = new ArrayList<TiresWidth>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TiresWidth tires = createTiresWidth(resultSet);
                tiresList.add(tires);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresList;
    }

    private boolean checkTiresWidth(TiresWidth tiresWidth) {
        boolean checked = true;
        if (tiresWidth == null || tiresWidth.getValue() == null) {
            checked = false;
        }
        return checked;

    }

    public static TiresWidth createTiresWidth(ResultSet resultSet) throws SQLException {
        TiresWidth tiresWidth = new TiresWidth();
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
