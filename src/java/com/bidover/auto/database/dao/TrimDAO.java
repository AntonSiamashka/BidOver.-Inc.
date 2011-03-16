/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.auto.model.bean.Trim;
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
public class TrimDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public TrimDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Trim trim) {
        boolean addFlag = false;
        if (trim != null && trim.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.trim(title) VALUES('" + trim.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<Trim> find(Trim trim) {
        List<Trim> trims = null;
        String dbRequest = createDBRequest(trim);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.trim WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        trims = new ArrayList<Trim>();
                    }
                    trims.add(createTrim(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return trims;
    }

    public List<Trim> findAll() {
        List<Trim> trims = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.trim");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    trims = new ArrayList<Trim>();
                }
                trims.add(createTrim(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return trims;

    }


    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public Trim createTrim(ResultSet resultSet) throws SQLException {
        Trim trim = new Trim();
        String id = resultSet.getString("trim.id");
        String title = resultSet.getString("trim.title");
        trim.setId(Integer.valueOf(id));
        trim.setTitle(title);
        return trim;
    }

    private String createDBRequest(Trim trim) {
        String dbRequest = "";
        if (trim != null) {
            if (trim.getId() != null) {
                dbRequest += "id='" + trim.getId() + "' ";
            }
            if (trim.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + trim.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
