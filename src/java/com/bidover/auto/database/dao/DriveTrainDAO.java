/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;


import com.bidover.common.database.dao.NamedBeanDAO;
import com.bidover.auto.model.bean.DriveTrain;

/**
 *
 * @author Jedai
 */
public class DriveTrainDAO extends NamedBeanDAO<DriveTrain> {

    private final static String BASE = "drive_train";

    public DriveTrainDAO() {
        super(BASE);
    }

    public DriveTrain createNewInstance(){
        return new DriveTrain();
    }
}
