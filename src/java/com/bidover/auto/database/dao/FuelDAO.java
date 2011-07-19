/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.Fuel;

/**
 *
 * @author Jedai
 */
public class FuelDAO extends NamedBeanDAO<Fuel> {

    private final static String BASE = "fuel";

    public FuelDAO() {
        super(BASE);
    }

    public Fuel createNewInstance(){
        return new Fuel();
    }
        
}
