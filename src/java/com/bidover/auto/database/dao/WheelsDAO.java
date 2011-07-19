/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.Wheels;

/**
 *
 * @author Jedai
 */
public class WheelsDAO extends NamedBeanDAO<Wheels> {

    private final static String BASE = "wheels";

    public WheelsDAO() {
        super(BASE);
    }

    public Wheels createNewInstance(){
        return new Wheels();
    }
    
}
