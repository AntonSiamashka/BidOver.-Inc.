/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.DamageCondition;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


/**
 *
 * @author Jedai
 */
public class DamageConditionDAO extends NamedBeanDAO<DamageCondition> {

    private final static String BASE = "damage_condition";
    
    public DamageConditionDAO() {
        super(BASE);
    }

    public DamageCondition createNewInstance() {
        return new DamageCondition();
    }
	
    public Map<Integer, String> findAllMap() {
        Map<Integer, String> damageConditions = null;
        DamageCondition damageCondition;
        try {
            connection = connectionPool.getConnection();
            peparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM bidover_db.damage_condition");
            ResultSet resultSet = peparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    damageConditions = new HashMap<Integer, String>();
                } 
                damageCondition = createBean(resultSet);
                damageConditions.put(damageCondition.getId(), damageCondition.getTitle());
            }
            peparedStatement.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(OptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return damageConditions;
    }


}
