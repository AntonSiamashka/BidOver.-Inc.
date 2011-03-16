/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.InteriorType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class InteriorTypeDAO {

    private List<InteriorType> interiorTypes = new ArrayList<InteriorType>();

    public InteriorTypeDAO() {
        InteriorType interiorType;
        interiorType = new InteriorType();
        interiorType.setId(0);
        interiorType.setTitle("Cloth");
        interiorTypes.add(interiorType);
        interiorType = new InteriorType();
        interiorType.setId(1);
        interiorType.setTitle("Leather");
        interiorTypes.add(interiorType);
        interiorType = new InteriorType();
        interiorType.setId(2);
        interiorType.setTitle("Vinyl");
        interiorTypes.add(interiorType);

    }

    public InteriorType find(InteriorType interiorType) {
        return interiorTypes.get(interiorType.getId());
    }

    public List<InteriorType> findAll() {
        return interiorTypes;
    }
}
