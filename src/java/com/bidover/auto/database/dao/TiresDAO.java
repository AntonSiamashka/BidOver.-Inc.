/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Tires;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class TiresDAO {

    private List<Tires> tiresList = new ArrayList<Tires>();

    public TiresDAO() {
        Tires tires;
        tires = new Tires();
        tires.setId(0);
        tires.setTitle("205/50");
        tiresList.add(tires);
        tires = new Tires();
        tires.setId(1);
        tires.setTitle("225/70");
        tiresList.add(tires);
    }

    public Tires find(Tires tires) {
        return tiresList.get(tires.getId());
    }

    public List<Tires> findAll() {
        return tiresList;
    }
}
