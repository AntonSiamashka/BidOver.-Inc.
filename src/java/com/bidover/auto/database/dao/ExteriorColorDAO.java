/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.ExteriorColor;

/**
 *
 * @author Jedai
 */
public class ExteriorColorDAO extends NamedBeanDAO<ExteriorColor> {

    private final static String BASE = "exterior_color";

    public ExteriorColorDAO() {
        super(BASE);
    }

    public ExteriorColor createNewInstance(){
        return new ExteriorColor();
    }
}
