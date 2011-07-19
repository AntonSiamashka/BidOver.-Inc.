/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.database.dao;

import com.bidover.detail.model.bean.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.detail.model.bean.Make;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.bidover.detail.properties.DatabaseProperties;
import com.bidover.common.logger.Logger;

public class ModelDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findModelById;
    private String findAllModels;
    private String findModelByMakeId;
    private String incrementRequest;
    private String decrementRequest;

    public ModelDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_MODEL_REQUEST);
        findModelById = databaseProperties.getProperty(DatabaseProperties.FIND_MODEL_BY_ID_REQUEST);
        findModelByMakeId = databaseProperties.getProperty(DatabaseProperties.FIND_MODEL_BY_MAKEID_REQUEST);
        findAllModels = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_MODELS_REQUEST);
        incrementRequest = databaseProperties.getProperty(DatabaseProperties.INCREMENT_MODEL_REQUEST);
        decrementRequest = databaseProperties.getProperty(DatabaseProperties.DECREMENT_MODEL_REQUEST);
    }

    public Integer addModel(Model model) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getTitle());
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
                Model model = findModelById(id);
                int count = model.getCount();
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
                Model model = findModelById(id);
                int count = model.getCount();
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

    public Model findModelById(int id) {
        Model model = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findModelById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                model = createModel(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return model;
    }

    public List<Model> findModelByMakeId(int id) {
        List<Model> models = new ArrayList<Model>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findModelByMakeId);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Model model = createModel(resultSet);
                models.add(model);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return models;
    }

    public List<Model> findAll() {
        List<Model> models = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAllModels);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    models = new ArrayList<Model>();
                }
                models.add(createModel(resultSet));
            }
        } catch (SQLException ex) {
            Logger.log(ex);
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return models;

    }

    public static Model createModel(ResultSet resultSet) throws SQLException {
        Model model = new Model();
        String id = resultSet.getString("model.id");
        String title = resultSet.getString("model.title");
        int count = resultSet.getInt("model.count");
        int beginYear = resultSet.getInt("model.begin_year");
        int endYear = resultSet.getInt("model.end_year");
        Make make = MakeDAO.createMake(resultSet);
        model.setMake(make);
        model.setId(Integer.valueOf(id));
        model.setTitle(title);
        model.setCount(count);
        model.setBegYear(beginYear);
        model.setEndYear(endYear);
        return model;
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

