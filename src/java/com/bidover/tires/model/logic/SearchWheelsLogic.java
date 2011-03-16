/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.model.logic;

import com.bidover.tires.database.dao.WheelsBCDDAO;
import com.bidover.tires.database.dao.WheelsDAO;

import com.bidover.tires.database.dao.WheelsDiameterDAO;
import com.bidover.tires.database.dao.WheelsEDDAO;
import com.bidover.tires.database.dao.WheelsWidthDAO;
import com.bidover.tires.model.bean.Wheels;

import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsDiameter;
import com.bidover.tires.model.bean.WheelsED;
import com.bidover.tires.model.bean.WheelsWidth;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class SearchWheelsLogic {

    private WheelsWidthDAO wheelsWidthDAO = new WheelsWidthDAO();
    private WheelsDiameterDAO wheelsDiameterDAO = new WheelsDiameterDAO();
    private WheelsBCDDAO wheelsBCDDAO = new WheelsBCDDAO();
    private WheelsEDDAO wheelsEDDAO = new WheelsEDDAO();
    private WheelsDAO wheelsDAO = new WheelsDAO();

    public List<Wheels> find(Wheels wheels) {
        return wheelsDAO.find(wheels);
    }

    public List<Wheels> findAll() {
        return wheelsDAO.findAllWheels();
    }

    public Wheels findById(int id) {
        return wheelsDAO.findWheelsById(id);
    }

    public List<WheelsWidth> findAllWidth() {
        List<WheelsWidth> wheelsWidthList = wheelsWidthDAO.findAll();
        return wheelsWidthList;
    }

    public List<WheelsDiameter> findAllDiameter() {
        List<WheelsDiameter> wheelsDiameterList = wheelsDiameterDAO.findAll();
        return wheelsDiameterList;
    }

    public List<WheelsBCD> findAllBCD() {
        List<WheelsBCD> wheelsBCDList = wheelsBCDDAO.findAll();
        return wheelsBCDList;
    }

    public List<WheelsED> findAllED() {
        List<WheelsED> wheelsEDList = wheelsEDDAO.findAll();
        return wheelsEDList;
    }
}
