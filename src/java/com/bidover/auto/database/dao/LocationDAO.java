package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Location;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;

public class LocationDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public LocationDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public Location find(String location_code) {
        Location location = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.location_US WHERE lc='" + location_code + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                location = createLocation(resultSet);
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return location;
    }

    public List<Location> findAllATDs(String country_code) {
        List<Location> locations = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.location_"+country_code+" GROUP BY sc");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    locations = new ArrayList<Location>();
                }
                locations.add(createLocation(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return locations;

    }

    public List<Location> findAllLocations(String country_code, String atd_code) {
        List<Location> locations = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.location_"+country_code+" WHERE sc='"+atd_code+"'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    locations = new ArrayList<Location>();
                }
                locations.add(createLocation(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return locations;

    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    private Location createLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        String country_code = resultSet.getString("cc");
        String atd_code = resultSet.getString("sc");
        String location_code = resultSet.getString("lc");
        String country = resultSet.getString("country");
        String atd = resultSet.getString("state");
        String city = resultSet.getString("city");
        String main_city = resultSet.getString("mc");

        location.setCountryCode(country_code);
        location.setATDCode(atd_code);
        location.setLocationCode(location_code);
        location.setCountry(country);
        location.setATD(atd);
        location.setLocation(city);
        location.setMainCity(Byte.valueOf(main_city));

        return location;
    }

    /*    private String createDBRequest(BodyStyle body) {
    String dbRequest = "";
    if (body != null) {
    if (body.getId() != null) {
    dbRequest += "id='" + body.getId() + "' ";
    }
    if (body.getTitle() != null) {
    if (!dbRequest.isEmpty()) {
    dbRequest += " AND ";
    }
    dbRequest += "title='" + body.getTitle() + "' ";
    }
    }
    return dbRequest;
    }*/
}
