/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.database.dao;

import com.bidover.tires.database.connectionpool.ConnectionPool;
import com.bidover.tires.model.bean.Tires;
import com.bidover.tires.model.bean.TiresContour;
import com.bidover.tires.model.bean.TiresDiameter;
import com.bidover.tires.model.bean.TiresMake;
import com.bidover.tires.model.bean.TiresSeason;
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
public class TiresDAO {

    private ConnectionPool connectionPool;
    private String addRequest;
    private String findTiresById;
    private String findAll;
    private String findOther;
    private static final String ID_COLUMN = "tires.id";
    private static final String WIDTH_COLUMN = "tires.width";
    private static final String CONTOUR_COLUMN = "tires.contour";
    private static final String DIAMETER_COLUMN = "tires.diameter";
    private static final String SEASON_COLUMN = "tires.season";
    private static final String MAKE_COLUMN = "tires.make";

    public TiresDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
        DatabaseProperties databaseProperties = DatabaseProperties.createDatabaseProperties();
        addRequest = databaseProperties.getProperty(DatabaseProperties.ADD_TIRES_REQUEST);
        findTiresById = databaseProperties.getProperty(DatabaseProperties.FIND_TIRES_BY_ID_REQUEST);
        findAll = databaseProperties.getProperty(DatabaseProperties.FIND_ALL_TIRES_REQUEST);
        findOther = databaseProperties.getProperty(DatabaseProperties.FIND_OTHER_TIRES_REQUEST);
    }

    public Integer addTires(Tires tires) {
        Integer generatedId = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if (checkTires(tires)) {
            try {
                connection = connectionPool.getConnection();
                preparedStatement = (PreparedStatement) connection.prepareStatement(addRequest, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, tires.getId());
                preparedStatement.setInt(2, tires.getTiresWidth().getId());
                preparedStatement.setInt(3, tires.getTiresDiameter().getId());
                preparedStatement.setInt(4, tires.getTiresContour().getId());
                preparedStatement.setInt(5, tires.getTiresSeason().getId());
                preparedStatement.setInt(6, tires.getTiresMake().getId());
                preparedStatement.execute();
                generatedId=tires.getId();
                /*resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }*/
            } catch (SQLException ex) {
                generatedId = null;
            } finally {
                closeAll(resultSet, preparedStatement, connection);
            }
        }
        return generatedId;
    }

    public Tires findTiresById(int id) {
        Tires tires = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findTiresById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tires = createTires(resultSet);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tires;
    }

    public List<Tires> findAllTires() {
        List<Tires> tiresList = new ArrayList<Tires>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tires tires = createTires(resultSet);
                tiresList.add(tires);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresList;
    }

    public List<Tires> find(Tires tires) {
        List<Tires> tiresList = new ArrayList<Tires>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            String request = createRequest(tires);
            preparedStatement = (PreparedStatement) connection.prepareStatement(findOther + " " + request);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tires tiresTemp = createTires(resultSet);
                tiresList.add(tiresTemp);
            }
        } catch (SQLException ex) {
        } finally {
            closeAll(resultSet, preparedStatement, connection);
        }
        return tiresList;
    }

    public static String createRequest(Tires tires) {
        StringBuilder requestBuilder = new StringBuilder(200);
        if (tires != null) {
            if (tires.getTiresWidth() != null && tires.getTiresWidth().getId() != null) {
                requestBuilder.append(WIDTH_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(tires.getTiresWidth().getId());
                requestBuilder.append("' ");
            }
            if (tires.getTiresContour() != null && tires.getTiresContour().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(CONTOUR_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(tires.getTiresContour().getId());
                requestBuilder.append("' ");
            }
            if (tires.getTiresDiameter() != null && tires.getTiresDiameter().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(DIAMETER_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(tires.getTiresDiameter().getId());
                requestBuilder.append("' ");
            }
            if (tires.getTiresSeason() != null && tires.getTiresSeason().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(SEASON_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(tires.getTiresSeason().getId());
                requestBuilder.append("' ");
            }
            if (tires.getTiresMake() != null && tires.getTiresMake().getId() != null) {
                if (!requestBuilder.toString().isEmpty()) {
                    requestBuilder.append(" AND ");
                }
                requestBuilder.append(MAKE_COLUMN);
                requestBuilder.append("='");
                requestBuilder.append(tires.getTiresMake().getId());
                requestBuilder.append("' ");
            }
        }
        return requestBuilder.toString();
    }

    private boolean checkTires(Tires tires) {
        boolean checked = true;

        return checked;

    }

    public static Tires createTires(ResultSet resultSet) throws SQLException {
        Tires tires = new Tires();
        TiresWidth tiresWidth = TiresWidthDAO.createTiresWidth(resultSet);
        TiresDiameter tiresDiameter = TiresDiameterDAO.createEntity(resultSet);
        TiresContour tiresContour = TiresContourDAO.createEntity(resultSet);
        TiresSeason tiresSeason = TiresSeasonDAO.createEntity(resultSet);
        TiresMake tiresMake = TiresMakeDAO.createEntity(resultSet);
        Integer id = resultSet.getInt(ID_COLUMN);
        tires.setId(id);
        tires.setTiresContour(tiresContour);
        tires.setTiresDiameter(tiresDiameter);
        tires.setTiresSeason(tiresSeason);
        tires.setTiresWidth(tiresWidth);
        tires.setTiresMake(tiresMake);
        return tires;
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
