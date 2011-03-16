/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.BodyStyle;
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
public class BodyStyleDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public BodyStyleDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

     public boolean add(BodyStyle body) {
        boolean addFlag = false;
        if (body != null && body.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.body_style(title) VALUES('" + body.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<BodyStyle> find(BodyStyle body) {
        List<BodyStyle> bodys = null;
        String dbRequest = createDBRequest(body);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.body_style WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        bodys = new ArrayList<BodyStyle>();
                    }
                    bodys.add(createBody(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return bodys;
    }

    public List<BodyStyle> findAll() {
        List<BodyStyle> bodys = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.body_style");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    bodys = new ArrayList<BodyStyle>();
                }
                bodys.add(createBody(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return bodys;

    }

    public boolean update(BodyStyle curBody, BodyStyle newBody) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curBody);
        if (newBody != null && newBody.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.body_style SET title='" + newBody.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(BodyStyle body) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");

            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public BodyStyle createBody(ResultSet resultSet) throws SQLException {
        BodyStyle body = new BodyStyle();
        String id = resultSet.getString("body_style.id");
        String title = resultSet.getString("body_style.title");
        body.setId(Integer.valueOf(id));
        body.setTitle(title);
        return body;
    }

    private String createDBRequest(BodyStyle body) {
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
    }
}
