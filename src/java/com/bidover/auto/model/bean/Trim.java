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
public class Trim extends NamedBean {

    public Trim() {
    }

    public Trim(Integer id) {
        setId(id);
    }

    public Trim(Integer id, String title) {
        setId(id);
        setTitle(title);
    }

}
