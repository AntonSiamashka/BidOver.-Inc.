
package com.bidover.auto.database.dao;


import com.bidover.common.model.bean.Condition;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;

public class ConditionDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public ConditionDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Condition condition) {
        boolean addFlag = false;
        if (condition != null && condition.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.condition(title) VALUES('" + condition.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public List<Condition> find(Condition condition) {
        List<Condition> conditions = null;
        String dbRequest = createDBRequest(condition);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.condition WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        conditions = new ArrayList<Condition>();
                    }
                    conditions.add(createCondition(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return conditions;
    }

    public List<Condition> findAll() {
        List<Condition> conditions = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.condition");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    conditions = new ArrayList<Condition>();
                }
                conditions.add(createCondition(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return conditions;

    }

    public boolean update(Condition curCondition, Condition newCondition) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curCondition);
        if (newCondition != null && newCondition.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.condition SET title='" + newCondition.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(Condition condition) {
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

    public Condition createCondition(ResultSet resultSet) throws SQLException {
        Condition condition = new Condition();
        String id = resultSet.getString("condition.id");
        String title = resultSet.getString("condition.title");
        condition.setId(Integer.valueOf(id));
        condition.setTitle(title);
        return condition;
    }

    private String createDBRequest(Condition condition) {
        String dbRequest = "";
        if (condition != null) {
            if (condition.getId() != null) {
                dbRequest += "condition.id='" + condition.getId() + "' ";
            }
            if (condition.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "condition.title='" + condition.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
