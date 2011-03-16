package com.bidover.detail.database.dao;

import com.bidover.detail.database.connectionpool.ConnectionPool;
import com.bidover.detail.model.bean.Category;
import com.bidover.detail.model.bean.Detail;
import com.bidover.detail.model.bean.Item;
import com.bidover.detail.model.bean.Make;
import com.bidover.detail.model.bean.Model;
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
public class DetailDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findDetailById;
    private String findAll;
    private String findOther;
    private static final String DB_NAME = " bidover_details.detail";
    private static final int NONE_ITEM = 428;

    public DetailDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_DETAIL_REQUEST);
        findDetailById = databaseProperties.getProperty(DatabaseProperties.FIND_DETAIL_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_DETAIL_REQUEST);
        findOther = databaseProperties.getProperty(DatabaseProperties.FIND_OTHER_REQUEST);
    }

    public Integer addDetail(Detail detail) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, detail.getId());
            preparedStatement.setInt(2, detail.getMake().getId());
            preparedStatement.setInt(3, detail.getModel().getId());
            preparedStatement.setInt(4, detail.getCategory().getId());
            preparedStatement.setInt(5, detail.getSubCategory().getId());
            if (detail.getItem().getId() == null) {
                detail.getItem().setId(NONE_ITEM);
            }
            preparedStatement.setInt(6, detail.getItem().getId());
            preparedStatement.setInt(7, detail.getBeginYear());
            preparedStatement.setInt(8, detail.getEndYear());
            //preparedStatement.setInt(8, detail.getLot().getId());
            preparedStatement.execute();
            generatedId=detail.getId();
            /*resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }*/
        } catch (SQLException ex) {
            ex.printStackTrace();
            generatedId = null;
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return generatedId;
    }

    public Detail findDetailById(int id) {
        Detail detail = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findDetailById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                detail = createDetail(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return detail;
    }

    public List<Detail> findAllDetail() {
        List<Detail> details = new ArrayList<Detail>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Detail detail = createDetail(resultSet);
                details.add(detail);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return details;
    }

    public List<Detail> find(Detail detail) {
        List<Detail> details = new ArrayList<Detail>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            String request = createRequest(detail);
            preparedStatement = (PreparedStatement) connection.prepareStatement(findOther + request);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Detail detailTemp = createDetail(resultSet);
                details.add(detailTemp);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return details;
    }

    private String createRequest(Detail detail) {
        String request = "";
        if (detail != null) {
            if (detail.getMake() != null && detail.getMake().getId() != null) {
                request += DB_NAME + ".makeFK='" + detail.getMake().getId() + "' ";
            }
            if (detail.getModel() != null && detail.getModel().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".modelFK='" + detail.getModel().getId() + "' ";
            }
            if (detail.getCategory() != null && detail.getCategory().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".categoryFK='" + detail.getCategory().getId() + "' ";
            }
            if (detail.getSubCategory() != null && detail.getSubCategory().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".subcategoryFK='" + detail.getSubCategory().getId() + "' ";
            }
            if (detail.getItem() != null && detail.getItem().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".itemFK='" + detail.getItem().getId() + "' ";
            }
            if (detail.getLot() != null && detail.getLot().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".lotFK='" + detail.getLot().getId() + "' ";
            }

            if (detail.getLot() != null && detail.getLot().getId() != null) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".lotFK='" + detail.getLot().getId() + "' ";
            }
            if (detail.getBeginYear() != null && detail.getEndYear() != null && detail.getBeginYear() < detail.getEndYear()) {
                if (!request.isEmpty()) {
                    request += " AND ";
                }
                request += DB_NAME + ".begin_year<='" + detail.getEndYear() + "' AND";
                request += DB_NAME + ".end_year>='" + detail.getBeginYear() + "' ";
            }
        }
        return request;
    }

    public static Detail createDetail(ResultSet resultSet) throws SQLException {
        Detail detail = new Detail();
        int id = resultSet.getInt("detail.idDetail");
        int yearBegin = resultSet.getInt("detail.begin_year");
        int yearEnd = resultSet.getInt("detail.end_year");
        Category category = CategoryDAO.createCategory(resultSet);
        SubCategory subCategory = SubCategoryDAO.createSubCategory(resultSet);
        Item item = ItemDAO.createItem(resultSet);
        Make make = MakeDAO.createMake(resultSet);
        Model model = ModelDAO.createModel(resultSet);
        detail.setId(id);
        detail.setBeginYear(yearBegin);
        detail.setEndYear(yearEnd);
        detail.setCategory(category);
        detail.setSubCategory(subCategory);
        detail.setItem(item);
        detail.setMake(make);
        detail.setModel(model);
        return detail;
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
