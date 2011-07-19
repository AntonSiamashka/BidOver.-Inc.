/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.CountryAssembly;

/**
 *
 * @author Jedai
 */
public class CountryAssemblyDAO extends NamedBeanDAO<CountryAssembly> {
    
    private final static String BASE = "assembley";
    
    public CountryAssemblyDAO(){
        super(BASE);
    }
    
    public CountryAssembly createNewInstance(){
        return new CountryAssembly();
    }
}
