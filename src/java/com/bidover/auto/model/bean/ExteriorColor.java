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
public class ExteriorColor extends NamedBean {
   
    public ExteriorColor() {
    }

    public ExteriorColor(Integer id) {
        setId(id);
    }

    public ExteriorColor(Integer id, String title) {
        setId(id);
        setTitle(title);
    }
}
