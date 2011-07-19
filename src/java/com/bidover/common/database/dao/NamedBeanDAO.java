/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.auto.model.bean.NamedBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bidover.common.logger.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public abstract class NamedBeanDAO<NB extends NamedBean> extends BaseDAO {

    private final String BASE;
    
    public NamedBeanDAO(String base) {
        super();
        BASE = base;
    }

     public boolean add(NB bean) {
        boolean addFlag = false;
        if (bean != null && bean.getTitle() != null) {
            try {
                executeRequest("INSERT INTO bidover_db."+BASE+"(title) VALUES('" + bean.getTitle() + "')");
                addFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return addFlag;
    }
     
    public List<NB> findById(Integer id) {
        List<NB> beans = null;
        if (id != null) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db."+BASE+" WHERE id=?");
                peparedStatement.setInt(1, id);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        beans = new ArrayList<NB>();
                    }
                    beans.add(createBean(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return beans;
    } 
     

    public List<NB> find(NB bean) {
        List<NB> beans = null;
        String dbRequest = createDBRequest(bean);
        if (!dbRequest.isEmpty()) {
            try {
                connection = connectionPool.getConnection();
                peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db."+BASE+" WHERE " + dbRequest);
                ResultSet resultSet = peparedStatement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.isFirst()) {
                        beans = new ArrayList<NB>();
                    }
                    beans.add(createBean(resultSet));
                }
                peparedStatement.close();
                connectionPool.releaseConnection(connection);
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return beans;
    }

    public List<NB> findAll() {
        List<NB> beans = null;

        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db."+BASE);
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    beans = new ArrayList<NB>();
                }
                beans.add(createBean(resultSet));
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }

        return beans;

    }

    public boolean update(NB curBody, NB newBody) {
        boolean updFlag = false;
        String dbRequest = createDBRequest(curBody);
        if (newBody != null && newBody.getTitle() != null && !dbRequest.isEmpty()) {
            try {
                executeRequest("UPDATE bidover_db."+BASE+" SET title='" + newBody.getTitle() + "' WHERE " + dbRequest);
                updFlag = true;
            } catch (SQLException ex) {
                Logger.log(ex);
            }
        }
        return updFlag;
    }

    public void delete(NB bean) {
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("");

            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            Logger.log(ex);
        }
    }

    public NB createBean(ResultSet resultSet) throws SQLException {
        NB bean = createNewInstance();
        return populateBean(bean, resultSet);
    }
    
    public abstract NB createNewInstance();
    
    public NB populateBean(NB bean, ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(BASE+".id");
        String title = resultSet.getString(BASE+".title");
        bean.setId(Integer.getInteger(id));
        bean.setTitle(title);
        return bean;
    }
    
    private String createDBRequest(NB bean) {
        String dbRequest = "";
        if (bean != null) {
            if (bean.getId() != null) {
                dbRequest += "id='" + bean.getId() + "' ";
            }
            if (bean.getTitle() != null) {
                if (!dbRequest.isEmpty()) {
                    dbRequest += " AND ";
                }
                dbRequest += "title='" + bean.getTitle() + "' ";
            }
        }
        return dbRequest;
    }
}
