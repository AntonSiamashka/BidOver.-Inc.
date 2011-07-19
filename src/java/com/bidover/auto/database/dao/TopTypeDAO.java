/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.TopType;

/**
 *
 * @author Jedai
 */
public class TopTypeDAO extends NamedBeanDAO<TopType> {

    private final static String BASE = "top_type";

    public TopTypeDAO() {
        super(BASE);
    }

    public TopType createNewInstance(){
        return new TopType();
    }
    
}
