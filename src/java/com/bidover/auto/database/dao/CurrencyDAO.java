
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Currency;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;

public class CurrencyDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public CurrencyDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public Currency find(String currency_code) {
        Currency currency = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.currency_iso WHERE currency_code='" + currency_code + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                currency = createCurrency(resultSet);
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return currency;
    }

    public List<Currency> findAll() {
        List<Currency> currency = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.currency_iso");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    currency = new ArrayList<Currency>();
                }
                currency.add(createCurrency(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return currency;

    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    private Currency createCurrency(ResultSet resultSet) throws SQLException {
        Currency currency = new Currency();
        String currency_code = resultSet.getString("currency_code");
        String place_used = resultSet.getString("place_used");
        String rates = resultSet.getString("rates");
        currency.setCurrencyCode(currency_code);
        currency.setPlaceUsed(place_used);
        currency.setRates(Double.parseDouble(rates));
        return currency;
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
