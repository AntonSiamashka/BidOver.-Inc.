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
public class InteriorColor extends NamedBean {

    public InteriorColor() {
    }

    public InteriorColor(Integer id) {
        setId(id);
    }

    public InteriorColor(Integer id, String title) {
        setId(id);
        setTitle(title);
    }
}
