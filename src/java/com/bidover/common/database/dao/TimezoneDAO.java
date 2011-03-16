/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.model.bean.Timezone;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;

/**
 *
 * @author Jedai
 */
public class TimezoneDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public TimezoneDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public Timezone find(Integer id) {
        Timezone timezone = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.timezones WHERE id='" + id + "'");
            ResultSet resultSet = peparedStatement.executeQuery();
            if (resultSet.next()) {
                timezone = createTimezone(resultSet);
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return timezone;
    }

    public List<Timezone> findAll() {
        List<Timezone> timezones = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.timezones");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    timezones = new ArrayList<Timezone>();
                }
                timezones.add(createTimezone(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return timezones;

    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    private Timezone createTimezone(ResultSet resultSet) throws SQLException {
        Timezone timezone = new Timezone();
        String id = resultSet.getString("id");
        String offset = resultSet.getString("offset");
        String timezoneTxt = resultSet.getString("timezone");
        String location = resultSet.getString("location");
        timezone.setId(Integer.valueOf(id));
        timezone.setLocation(location);
        timezone.setOffset(Short.parseShort(offset));
        timezone.setTimezone(timezoneTxt);
        return timezone;
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
