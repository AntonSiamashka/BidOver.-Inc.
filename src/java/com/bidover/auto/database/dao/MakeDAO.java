/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Make;
import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.auto.model.bean.Characteristics;
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
public class MakeDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public MakeDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Make make) {
        boolean addFlag = false;
        if (make != null && make.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db.make(title) VALUES('" + make.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public boolean increment(Integer id) {
        boolean addFlag = false;
        if (id != null) {
            try {
                Make make = findById(id);
                int count = make.getCount();
                count++;
                executeRequest("UPDATE bidover_db.make SET make.count='" + count + "' WHERE make.id='" + id + "'");
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
                Make make = findById(id);
                int count = make.getCount();
                count--;
                executeRequest("UPDATE bidover_db.make SET make.count='" + count + "' WHERE make.id='" + id + "'");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public Make findById(Integer id) {
        Make make = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.make WHERE make.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    make = createMake(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return make;
    }

    public List<Make> find(Make make) {
        List<Make> makes = null;
        String dbRequest = createDBRequest(make);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.make WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        makes = new ArrayList<Make>();
                    }
                    makes.add(createMake(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return makes;
    }

    public List<Make> findAll() {
        List<Make> makes = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.make");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    makes = new ArrayList<Make>();
                }
                makes.add(createMake(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return makes;

    }

    public boolean update(Make curMake, Make newMake) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curMake);
        if (newMake != null && newMake.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db.make SET title='" + newMake.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(Make make) {
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

    public Make createMake(ResultSet resultSet) throws SQLException {
        Make make = new Make();
        String id = resultSet.getString("make.id");
        String title = resultSet.getString("make.title");
        int count = resultSet.getInt("make.count");
        make.setId(Integer.getInteger(id));
        make.setTitle(title);
        make.setCount(count);
        return make;
    }

    public Characteristics createCharacteristics(ResultSet resultSet) throws SQLException {
        Characteristics characteristics = new Characteristics();
        ///
        String id = resultSet.getString("make.id");
        String title = resultSet.getString("make.title");
        int count = resultSet.getInt("make.count");
        ///
        characteristics.setId(count);
        characteristics.setMake(title);
        characteristics.setModel(id);
        characteristics.setModification(title);
        characteristics.getBegYear();
        characteristics.setEndYear(count);
        characteristics.setTires(title);
        characteristics.getWheels();
        characteristics.setCount(count);

        return characteristics;
    }

    private String createDBRequest(Make make) {
        String dbRequest = "";
        if (make != null) {
            if (make.getId() != null) {
                dbRequest += "make.id='" + make.getId() + "' ";
            }
            if (make.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "make.title='" + make.getTitle() + "' ";
            }
            if (make.getCount() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "make.count='" + make.getCount() + "' ";
            }
        }
        return dbRequest;
    }
}
