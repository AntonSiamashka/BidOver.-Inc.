/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Characteristics;
import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.auto.model.bean.Make;
import com.bidover.auto.model.bean.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bidover.common.logger.Logger;
import java.util.Calendar;

/**
 *
 * @author Jedai
 */
public class CharacteristicsDAO {

    private final static int MAX_YEAR = 3000;
    private Connection connection;
    private PreparedStatement peparedStatement;
    private ConnectionPool connectionPool;

    public CharacteristicsDAO() {
        connectionPool = ConnectionPool.getConnectionPool();
    }

    public List<String> findAllMakesNames() {
        List<String> models = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT manufacturer FROM bidover_db.characteristics WHERE characteristics.level='0'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    models = new ArrayList<String>();
                }
                models.add(resultSet.getString("characteristics.manufacturer"));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return models;
    }

    public List<String> findModelsNamesByMakeTitle(String makeTitle) {
        List<String> models = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT model FROM bidover_db.characteristics WHERE characteristics.manufacturer = '" + makeTitle + "' AND characteristics.level='1'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    models = new ArrayList<String>();
                }
                models.add(resultSet.getString("characteristics.model"));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return models;
    }

    public List<String> findByModelTitle(String modelTitle) {
        List<String> modifications = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT modification FROM bidover_db.characteristics WHERE characteristics.model = '" + modelTitle + "' AND characteristics.level='2'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    modifications = new ArrayList<String>();
                }
                modifications.add(resultSet.getString("characteristics.modification"));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return modifications;
    }

    public boolean add(Characteristics characteristics) {
        boolean addFlag = false;
        /////////ПРОВЕРКА НА ЗАПОЛНЕНИЕ ПОЛЕЙ?
        //if (model != null && model.getTitle() != null && model.getMake() != null) {
        try {
            executeRequest("INSERT INTO bidover_db.characteristics(manufacturer, model, modification, tyres, date_from, date_to) VALUES('" + characteristics.getMake() + "','" + characteristics.getModification() + "','" + characteristics.getModification() + "','" + characteristics.getTires() + "','" + characteristics.getBegYear() + "','" + characteristics.getEndYear() + "')");
            addFlag = true;
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        //}
        return addFlag;
    }

    public boolean increment(Integer characteristicsId,String manufacturer,String model) {
        boolean addFlag = true;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE bidover_db.characteristics SET qnt=qnt+1 WHERE id=? OR (manufacturer=?  AND level=0) OR (manufacturer=?  AND model=? AND level=1)");
            preparedStatement.setInt(1, characteristicsId);
            preparedStatement.setString(2, manufacturer);
            preparedStatement.setString(3, manufacturer);
            preparedStatement.setString(4, model);
            preparedStatement.execute();
        } catch (SQLException ex) {
            addFlag=false;
            Logger.log(ex);
        } finally {
            closeAll(null, preparedStatement, connection);
        }
        return addFlag;
    }

    public boolean decrement(Integer characteristicsId,String manufacturer,String model) {
        boolean addFlag = true;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE bidover_db.characteristics SET qnt=qnt-1 WHERE id=? OR (manufacturer=?  AND level=0) OR (manufacturer=?  AND model=? AND level=1)");
            preparedStatement.setInt(1, characteristicsId);
            preparedStatement.setString(2, manufacturer);
            preparedStatement.setString(3, manufacturer);
            preparedStatement.setString(4, model);
            preparedStatement.execute();
        } catch (SQLException ex) {
            addFlag=false;
            Logger.log(ex);
        } finally {
            closeAll(null, preparedStatement, connection);
        }
        return addFlag;
    }

    public Characteristics findById(Integer id) {
        Characteristics characteristics = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.characteristics WHERE characteristics.id='" + id + "'");
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    characteristics = createCharacteristics(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return characteristics;
    }

    public Characteristics findByMarkModelModufication(Characteristics characteristics) {
        Characteristics rezChar = null;
        String dbRequest = createDBRequest(characteristics);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.characteristics WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                if (resultSet.next()) {
                    rezChar = createCharacteristics(resultSet);
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return rezChar;
    }

    public List<Characteristics> findAllByModelTitle(String modelTitle) {
        List<Characteristics> characteristics = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.characteristics WHERE characteristics.model='" + modelTitle + "' AND characteristics.level='1'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    characteristics = new ArrayList<Characteristics>();
                }
                characteristics.add(createCharacteristics(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return characteristics;
    }

    public List<Characteristics> findAllByModificationTitle(String modificationTitle) {
        List<Characteristics> characteristics = null;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.characteristics WHERE characteristics.modification='" + modificationTitle + "' AND characteristics.level='2'");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    characteristics = new ArrayList<Characteristics>();
                }
                characteristics.add(createCharacteristics(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
        return characteristics;
    }

    private String createDBRequest(Characteristics characteristics) {
        String dbRequest = "";
        if (characteristics != null) {
            if (characteristics.getMake() != null) {
                dbRequest += "characteristics.manufacturer='" + characteristics.getMake() + "' ";
            }
            if (characteristics.getModel() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.model='" + characteristics.getModel() + "' ";
            }
            if (characteristics.getModification() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "characteristics.modification='" + characteristics.getModification() + "' ";
            }
        }
        return dbRequest;
    }

    public Characteristics createCharacteristics(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("characteristics.id");
        String make = resultSet.getString("characteristics.manufacturer");
        String model = resultSet.getString("characteristics.model");
        String modification = resultSet.getString("characteristics.modification");
        String date_from = resultSet.getString("characteristics.date_from");
        String date_to = resultSet.getString("characteristics.date_to");
        String tyres = resultSet.getString("characteristics.tyres");
        Characteristics characteristics = new Characteristics();
        characteristics.setId(Integer.parseInt(id));
        characteristics.setMake(make);
        characteristics.setModel(model);
        characteristics.setModification(modification);
        characteristics.setBegYear(Integer.parseInt(date_from));
        Integer dateTo = Integer.parseInt(date_to);
        if(dateTo==MAX_YEAR){
            dateTo = Calendar.getInstance().get(Calendar.YEAR);
        }
        characteristics.setEndYear(dateTo);
        characteristics.setTires(tyres);
        return characteristics;
    }

    private void executeRequest(String request) throws SQLException {
        connection = connectionPool.getConnection();
        peparedStatement = (PreparedStatement) connection.prepareStatement(request);
        peparedStatement.execute();
        peparedStatement.close();
        connectionPool.releaseConnection(connection);
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

    private void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
            }
        }
        if (connection != null) {
            try {
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
            }
        }
    }
}
