
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Country;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;

public class CountryDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public CountryDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public Country find(String iso_cc2) {
        Country country = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.cc_iso_en WHERE ISO_CC2='" + iso_cc2 + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                country = createCountry(resultSet);
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return country;
    }

    public List<Country> findAll() {
        List<Country> countries = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.cc_iso_en");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    countries = new ArrayList<Country>();
                }
                countries.add(createCountry(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return countries;

    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    private Country createCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();

        String country_name = resultSet.getString("COUNTRY");
        String iso_cc2 = resultSet.getString("ISO_CC2");

        country.setCountry(country_name);
        country.setISO_CC2(iso_cc2);
        return country;
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
