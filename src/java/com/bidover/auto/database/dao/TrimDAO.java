/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.database.dao;
import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.Trim;

/**
 *
 * @author Jedai
 */
public class TrimDAO extends NamedBeanDAO<Trim> {

    private final static String BASE = "trim";

    public TrimDAO() {
        super(BASE);
    }

    public Trim createNewInstance(){
        return new Trim();
    }
}
