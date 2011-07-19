/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.database.dao;

import com.bidover.detail.model.bean.Make;
import com.bidover.common.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.detail.properties.DatabaseProperties;
import com.bidover.common.logger.Logger;

/**
 *
 * @author Jedai
 */
public class MakeDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findMakeById;
    private String findAllMakes;
    private String incrementRequest;
    private String decrementRequest;

    public MakeDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_MAKE_REQUEST);
        findMakeById = databaseProperties.getProperty(DatabaseProperties.FIND_MAKE_BY_ID_REQUEST);
        findAllMakes = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_MAKES_REQUEST);
        incrementRequest = databaseProperties.getProperty(DatabaseProperties.INCREMENT_MAKE_REQUEST);
        decrementRequest = databaseProperties.getProperty(DatabaseProperties.DECREMENT_MAKE_REQUEST);
    }

    public Integer addMake(Make make) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, make.getTitle());
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

        return generatedId;
    }

    public boolean increment(Integer id) {
        boolean addFlag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (id != null) {
            try {
                Make make = findMakeById(id);
                int count = make.getCount();
                count++;
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(incrementRequest);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            } finally {
                closeAll(preparedStatement, connection);
            }
        }
        return addFlag;
    }

    public boolean decrement(Integer id) {
        boolean addFlag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (id != null) {
            try {
                Make make = findMakeById(id);
                int count = make.getCount();
                count--;
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(decrementRequest);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            } finally {
                closeAll(preparedStatement, connection);
            }
        }
        return addFlag;
    }

    public Make findMakeById(int id) {
        Make make = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findMakeById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                make = createMake(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return make;
    }

    public List<Make> findAll() {
        List<Make> makes = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAllMakes);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    makes = new ArrayList<Make>();
                }
                makes.add(createMake(resultSet));
            }
        } catch (SQLException ex) {
            Logger.log(ex);
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return makes;

    }

    public static Make createMake(ResultSet resultSet) throws SQLException {
        Make make = new Make();
        String id = resultSet.getString("make.id");
        String title = resultSet.getString("make.title");
        int count = resultSet.getInt("make.count");
        make.setId(Integer.valueOf(id));
        make.setTitle(title);
        make.setCount(count);
        return make;
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

    private void closeAll(PreparedStatement preparedStatement, Connection connection) {
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
