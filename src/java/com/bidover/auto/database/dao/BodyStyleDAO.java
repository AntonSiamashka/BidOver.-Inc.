/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.BodyStyle;

/**
 *
 * @author Jedai
 */
public class BodyStyleDAO extends NamedBeanDAO<BodyStyle> {

    private final static String BASE = "body_style";
    
    public BodyStyleDAO(){
        super(BASE);
    }

    public BodyStyle createNewInstance(){
        return new BodyStyle();
    }
    
}
