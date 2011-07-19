/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.common.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jedai
 */
public class BaseDAO {

    protected Connection connection;
    protected PreparedStatement peparedStatement;
    protected ConnectionPool connectionPool;

    public BaseDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    protected void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    protected void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (connection != null) {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
