/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.database.dao;

import com.bidover.auto.model.bean.Engine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class EngineDAO {

    private List<Engine> engines = new ArrayList<Engine>();

    public EngineDAO() {
        Engine engine;
        for (int i = 0; i < 8; i++) {
            engine = new Engine();
            engine.setId(i);
            int cl = i + 1;
            engine.setTitle(cl + "cl");
            engines.add(engine);
        }
    }

    public Engine find(Engine engine) {
        if(engine == null || engine.getId() == null) {
            return null;
        }
        return engines.get(engine.getId());
    }
    public List<Engine> findAll() {
        return engines;
    }
}
