/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Model;

import com.bidover.auto.model.bean.Modification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.bidover.common.logger.Logger;
import com.bidover.common.database.connectionpool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Jedai
 */
public class ModificationDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public ModificationDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Modification modification) {
        boolean addFlag = false;

        /////////ПРОВЕРКА НА ЗАПОЛНЕНИЕ ПОЛЕЙ?
        //if (model != null && model.getTitle() != null && model.getMake() != null) {
        try {
            executeRequest("INSERT INTO bidover_db.modification(title, model_id,year_begin,year_end) VALUES('" + modification.getTitle() + "','" + modification.getModel().getId() + "','" + modification.getBeginYear() + "','" + modification.getEndYear() + "')");
            addFlag = true;
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        //}
        return addFlag;
    }

    public boolean increment(Integer id) {
        boolean addFlag = false;
        if (id != null) {
            try {
                Modification modification = findById(id);
                int count = modification.getCount();
                count++;
                executeRequest("UPDATE bidover_db.modification SET modification.count='" + count + "' WHERE modification.id='" + id + "'");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public boolean decrement(Integer id) {
        boolean addFlag = false;
        if (id != null) {
            try {
                Modification modification = findById(id);
                int count = modification.getCount();
                count--;
                executeRequest("UPDATE bidover_db.modification SET modification.count='" + count + "' WHERE modification.id='" + id + "'");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public Modification findById(Integer id) {
        Modification modification = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.modification WHERE modification.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    modification = createModification(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return modification;
    }

    public List<Modification> find(Modification modification) {
        List<Modification> modifications = null;
        String dbRequest = createDBRequest(modification);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.modification WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        modifications = new ArrayList<Modification>();
                    }
                    modifications.add(createModification(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return modifications;
    }

    public Modification createModification(ResultSet resultSet) throws SQLException {
        Modification modification = new Modification();
        Model model = new Model();
        String id = resultSet.getString("modification.id");
        String title = resultSet.getString("modification.title");
        String model_id = resultSet.getString("modification.model_id");
        int count = resultSet.getInt("modification.count");
        int yearBegin = resultSet.getInt("modification.year_begin");
        int yearEnd = resultSet.getInt("modification.year_end");
        modification.setCount(count);
        model.setId(Integer.getInteger(model_id));
        modification.setTitle(title);
        modification.setBeginYear(yearBegin);
        modification.setEndYear(yearEnd);
        return modification;
    }

    private String createDBRequest(Modification modification) {
        String dbRequest = "";
        if (modification != null) {
            if (modification.getId() != null) {
                dbRequest += "modification.id='" + modification.getId() + "' ";
            }
            if (modification.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "modification.title='" + modification.getTitle() + "' ";
            }
            if (modification.getCount() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "modification.title='" + modification.getCount() + "' ";
            }
            if (modification.getModel() != null) {
                if (modification.getModel().getId() != null) {
                    if (!dbRequest.isEmpty()) {
                        dbRequest += " AND ";
                    }
                    dbRequest += "modification.model_id='" + modification.getModel().getId() + "' ";
                }
            }
            if (modification.getModel() != null) {
                if (modification.getBeginYear() != null) {
                    if (!dbRequest.isEmpty()) {
                        dbRequest += " AND ";
                    }
                    dbRequest += "modification.year_begin='" + modification.getBeginYear() + "' ";
                }
            }
            if (modification.getModel() != null) {
                if (modification.getEndYear() != null) {
                    if (!dbRequest.isEmpty()) {
                        dbRequest += " AND ";
                    }
                    dbRequest += "modification.year_end='" + modification.getEndYear() + "' ";
                }
            }
        }
        return dbRequest;
    }

    public List<Modification> findByModelTitle(String modelTitle) {
        List<Modification> modifications = null;
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.modification INNER JOIN bidover_db.model ON modification.model_id = model.id WHERE model.title = '"+modelTitle+"'");
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        modifications = new ArrayList<Modification>();
                    }
                    modifications.add(createModification(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }

        return modifications;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }
}
