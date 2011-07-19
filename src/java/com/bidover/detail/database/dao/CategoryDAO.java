package com.bidover.detail.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.detail.model.bean.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.detail.properties.DatabaseProperties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jedai
 */
public class CategoryDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findCategoryById;
    private String findAll;

    public CategoryDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_CATEGORY_REQUEST);
        findCategoryById = databaseProperties.getProperty(DatabaseProperties.FIND_CATEGORY_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_CATEGORY_REQUEST);
    }

    public Integer addCategory(Category category) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkCategory(category)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, category.getName());
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

    public Category findCategoryById(int id) {
        Category category = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findCategoryById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = createCategory(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return category;
    }

    public List<Category> findAllCategory() {
        List<Category> categorys = new ArrayList<Category>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = createCategory(resultSet);
                categorys.add(category);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return categorys;
    }

    private boolean checkCategory(Category category) {
        boolean checked = true;
        if (category == null || category.getName() == null) {
            checked = false;
        }

        return checked;

    }

    public static Category createCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        int id = resultSet.getInt("category.idCategory");
        String name = resultSet.getString("category.name");
        category.setId(id);
        category.setName(name);
        return category;
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
