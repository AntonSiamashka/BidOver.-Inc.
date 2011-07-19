/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Transmission;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class TransmissionDAO {

    private List<Transmission> transmissions = new ArrayList<Transmission>();

    public TransmissionDAO() {
        Transmission transmission;
        transmission = new Transmission();
        transmission.setId(0);
        transmission.setTitle("automatic");
        transmissions.add(transmission);
        transmission = new Transmission();
        transmission.setId(1);
        transmission.setTitle("manual");
        transmissions.add(transmission);
    }

    public Transmission find(Transmission transmission) {
        if(transmission == null || transmission.getId() == null) {
            return null;
        }
        return transmissions.get(transmission.getId());
    }

    public List<Transmission> findAll() {
        return transmissions;
    }
}
