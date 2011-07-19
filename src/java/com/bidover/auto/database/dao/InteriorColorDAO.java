/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.InteriorColor;

/**
 *
 * @author Jedai
 */
public class InteriorColorDAO extends NamedBeanDAO<InteriorColor> {

    private final static String BASE = "interior_color";

    public InteriorColorDAO() {
        super(BASE);
    }

    public InteriorColor createNewInstance(){
        return new InteriorColor();
    }
}
