package com.bidover.detail.database.dao;

import com.bidover.detail.database.connectionpool.ConnectionPool;
import com.bidover.detail.model.bean.Category;
import com.bidover.detail.model.bean.SubCategory;
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
public class SubCategoryDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findSubcategoryById;
    private String findSubcategoryByCategoryId;
    private String findAll;
    
    public SubCategoryDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_SUBCATEGORY_REQUEST);
        findSubcategoryById = databaseProperties.getProperty(DatabaseProperties.FIND_CATEGORY_BY_ID_REQUEST);
        findSubcategoryByCategoryId = databaseProperties.getProperty(DatabaseProperties.FIND_SUBCATEGORY_BY_CATEGORYID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_SUBCATEGORY_REQUEST);
    }

    public Integer addSubCategory(SubCategory category) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkSubCategory(category)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, category.getName());
                preparedStatement.setInt(2, category.getCategory().getId());
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

    public SubCategory findSubCategoryById(int id) {
        SubCategory subCategory = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findSubcategoryById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subCategory = createSubCategory(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return subCategory;
    }

     public List<SubCategory> findSubCategoryByCategoryId(int id) {
        List<SubCategory> subCategory = new ArrayList<SubCategory>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findSubcategoryByCategoryId);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subCategory.add(createSubCategory(resultSet));
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return subCategory;
    }

     public List<SubCategory> findAllSubCategory() {
        List<SubCategory> categorys = new ArrayList<SubCategory>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubCategory category = createSubCategory(resultSet);
                categorys.add(category);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return categorys;
    }

    private boolean checkSubCategory(SubCategory category) {
        boolean checked = true;
        if (category == null || category.getName() == null) {
            checked = false;
        }

        return checked;

    }

    public static SubCategory createSubCategory(ResultSet resultSet) throws SQLException {
        SubCategory subCategory = new SubCategory();
        int id = resultSet.getInt("subcategory.idSubcategory");
        String name = resultSet.getString("subcategory.nameSubcategory");
        Category category = CategoryDAO.createCategory(resultSet);
        subCategory.setId(id);
        subCategory.setName(name);
        subCategory.setCategory(category);
        return subCategory;
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
