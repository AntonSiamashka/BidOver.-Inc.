/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.model.logic;

import com.bidover.tires.database.dao.TiresContourDAO;
import com.bidover.tires.database.dao.TiresDAO;
import com.bidover.tires.database.dao.TiresDiameterDAO;
import com.bidover.tires.database.dao.TiresMakeDAO;
import com.bidover.tires.database.dao.TiresSeasonDAO;
import com.bidover.tires.database.dao.TiresWidthDAO;
import com.bidover.tires.model.bean.Tires;
import com.bidover.tires.model.bean.TiresContour;
import com.bidover.tires.model.bean.TiresDiameter;
import com.bidover.tires.model.bean.TiresMake;
import com.bidover.tires.model.bean.TiresSeason;
import com.bidover.tires.model.bean.TiresWidth;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class AddingTiresLogic {

    private TiresDAO tiresDAO = new TiresDAO();
    private TiresWidthDAO tiresWidthDAO = new TiresWidthDAO();
    private TiresDiameterDAO tiresDiameterDAO = new TiresDiameterDAO();
    private TiresContourDAO tiresContourDAO = new TiresContourDAO();
    private TiresSeasonDAO tiresSeasonDAO = new TiresSeasonDAO();
    private TiresMakeDAO tiresMakeDAO = new TiresMakeDAO();

    public Integer add(Tires tires) {
        return tiresDAO.addTires(tires);
    }

    public List<TiresWidth> findAllTiresWidth() {
        List<TiresWidth> tiresWidthList = tiresWidthDAO.findAll();
        return tiresWidthList;
    }

    public List<TiresDiameter> findAllTiresDiameter() {
        List<TiresDiameter> tiresDiameterList = tiresDiameterDAO.findAll();
        return tiresDiameterList;
    }
    public List<TiresContour> findAllTiresContour() {
        List<TiresContour> tiresContourhList = tiresContourDAO.findAll();
        return tiresContourhList;
    }
    public List<TiresSeason> findAllTiresSeason() {
        List<TiresSeason> tiresSeasonList = tiresSeasonDAO.findAll();
        return tiresSeasonList;
    }
    public List<TiresMake> findAllTiresMake() {
        List<TiresMake> tiresMakeList = tiresMakeDAO.findAll();
        return tiresMakeList;
    }


}
