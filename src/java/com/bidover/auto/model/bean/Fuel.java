/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.model.bean;

import java.io.Serializable;

/**
 *
 * @author Jedai
 */
public class Fuel extends NamedBean {

    public Fuel() {
    }

    public Fuel(Integer id) {
        setId(id);
    }

    public Fuel(Integer id, String title) {
        setId(id);
        setTitle(title);
    }
}
