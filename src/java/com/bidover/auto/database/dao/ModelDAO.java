/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Make;
import com.bidover.auto.model.bean.Model;
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
public class ModelDAO {

    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public ModelDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public boolean add(Model model) {
        boolean addFlag = false;
        /////////ПРОВЕРКА НА ЗАПОЛНЕНИЕ ПОЛЕЙ?
        //if (model != null && model.getTitle() != null && model.getMake() != null) {
        try {
            executeRequest("INSERT INTO bidover_db.model(title, make_id) VALUES('" + model.getTitle() + "','" + model.getMake().getId() + "')");
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
                Model model = findById(id);
                int count = model.getCount();
                count++;
                executeRequest("UPDATE bidover_db.model SET model.count='" + count + "' WHERE model.id='" + id + "'");
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
                Model model = findById(id);
                int count = model.getCount();
                count--;
                executeRequest("UPDATE bidover_db.model SET model.count='" + count + "' WHERE model.id='" + id + "'");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }

    public Model findById(Integer id) {
        Model model = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.model WHERE model.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    model = createModel(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return model;
    }

    public List<Model> find(Model model) {
        List<Model> models = null;
        String dbRequest = createDBRequest(model);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.model WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        models = new ArrayList<Model>();
                    }
                    models.add(createModel(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return models;
    }


     public List<Model> findByMakeTitle(String makeTitle) {
        List<Model> models = null;

            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.model INNER JOIN bidover_db.make ON model.make_id = make.id WHERE make.title = '"+makeTitle+"'");
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        models = new ArrayList<Model>();
                    }
                    models.add(createModel(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }

        return models;
    }

    public Model createModel(ResultSet resultSet) throws SQLException {
        Model model = new Model();
        Make make = new Make();
        String id = resultSet.getString("model.id");
        String title = resultSet.getString("model.title");
        String make_id = resultSet.getString("model.make_id");
        int count = resultSet.getInt("model.count");


        String begYear = resultSet.getString("model.begin_year");
        begYear = "1990";


        String endYear = resultSet.getString("model.end_year");
        endYear = "2010";


        make.setId(Integer.getInteger(make_id));
        ///////////////////
        MakeDAO makeDAO = new MakeDAO();
        List<Make> makes = makeDAO.find(make);
        make = makes.get(0);
        ///////////////////
        model.setMake(make);
        model.setId(Integer.getInteger(id));
        model.setTitle(title);
        model.setBegYear(Integer.getInteger(begYear));
        model.setEndYear(Integer.getInteger(endYear));
        model.setCount(count);
        return model;
    }

    private String createDBRequest(Model model) {
        String dbRequest = "";
        if (model != null) {
            if (model.getId() != null) {
                dbRequest += "model.id='" + model.getId() + "' ";
            }
            if (model.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "model.title='" + model.getTitle() + "' ";
            }

            if (model.getCount() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "model.count='" + model.getCount() + "' ";
            }

            if (model.getMake() != null) {
                if (model.getMake().getId() != null) {
                    if (!dbRequest.isEmpty()) {
                        dbRequest += " AND ";
                    }
                    dbRequest += "make_id='" + model.getMake().getId() + "' ";
                }
            }
        }
        return dbRequest;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
    }
}
