/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.DamageCondition;
import com.bidover.auto.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Jedai
 */
public class DamageConditionDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public DamageConditionDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(DamageCondition damageCondition) {
        boolean addFlag = false;
        if (damageCondition != null && damageCondition.getCondition() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.damage_condition(condition) VALUES('" + damageCondition.getCondition() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return addFlag;
    }

    public List<DamageCondition> find(DamageCondition damageCondition) {
        List<DamageCondition> damageConditions = null;
        String dbRequest = createDBRequest(damageCondition);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.damage_condition WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        damageConditions = new ArrayList<DamageCondition>();
                    }
                    damageConditions.add(createDamageCondition(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return damageConditions;
    }

    public List<DamageCondition> findAll() {
        List<DamageCondition> damageConditions = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.damage_condition");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    damageConditions = new ArrayList<DamageCondition>();
                }
                damageConditions.add(createDamageCondition(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return damageConditions;

    }

    public boolean update(DamageCondition curDamageCondition, DamageCondition newDamageCondition) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curDamageCondition);
        if (newDamageCondition != null && newDamageCondition.getCondition() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.damage_condition SET condition='" + newDamageCondition.getCondition() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return updFlag;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }

    public DamageCondition createDamageCondition(ResultSet resultSet) throws SQLException {
        DamageCondition damageCondition = new DamageCondition();
        String id = resultSet.getString("damage_condition.id");
        String condition = resultSet.getString("damage_condition.condition");
        damageCondition.setId(Integer.valueOf(id));
        damageCondition.setCondition(condition);
        return damageCondition;
    }

    private String createDBRequest(DamageCondition damageCondition) {
        String dbRequest = "";
        if (damageCondition != null) {
            if (damageCondition.getId() != null) {
                dbRequest += "id='" + damageCondition.getId() + "' ";
            }
            if (damageCondition.getCondition() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "condition='" + damageCondition.getCondition() + "' ";
            }
        }
        return dbRequest;
    }
}
