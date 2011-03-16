/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Door;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class DoorDAO {

    private List<Door> doors = new ArrayList<Door>();

    public DoorDAO() {
        Door door;
        for (int i = 0; i < 4; i++) {
            door = new Door();
            door.setId(i);
            int t = i + 2;
            door.setTitle(String.valueOf(t));
            doors.add(door);
        }
    }

    public Door find(Door door) {
        return doors.get(door.getId());
    }
    public List<Door> findAll() {
        return doors;
    }
}
