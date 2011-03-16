package com.bidover.detail.database.dao;

import com.bidover.detail.database.connectionpool.ConnectionPool;
import com.bidover.detail.model.bean.Item;
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
public class ItemDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findItemById;
    private String findItemBySubcategoryId;
    private String findAll;

    public ItemDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_ITEM_REQUEST);
        findItemById = databaseProperties.getProperty(DatabaseProperties.FIND_ITEM_BY_ID_REQUEST);
        findItemBySubcategoryId = databaseProperties.getProperty(DatabaseProperties.FIND_ITEM_BY_SUBCATEGORYID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_ITEM_REQUEST);
    }

    public Integer addItem(Item item) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkItem(item)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, item.getName());
                preparedStatement.setInt(2, item.getSubCategory().getId());
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

    public Item findItemById(int id) {
        Item item = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findItemById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item = createItem(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return item;
    }

     public List<Item> findItemsBySubCategoryId(int id) {
        List<Item> items = new ArrayList<Item>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findItemBySubcategoryId);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = createItem(resultSet);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return items;
    }

     public List<Item> findAllItem() {
        List<Item> items = new ArrayList<Item>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = createItem(resultSet);
                items.add(item);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return items;
    }

    private boolean checkItem(Item item) {
        boolean checked = true;
        if (item == null || item.getName() == null) {
            checked = false;
        }
        return checked;
    }

    public static Item createItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        int id = resultSet.getInt("item.idItem");
        String name = resultSet.getString("item.name");
        SubCategory subCategory = SubCategoryDAO.createSubCategory(resultSet);
        item.setId(id);
        item.setName(name);
        item.setSubCategory(subCategory);
        return item;
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
