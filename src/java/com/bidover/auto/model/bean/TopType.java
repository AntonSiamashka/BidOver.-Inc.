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
public class TopType extends NamedBean {

    public TopType() {
    }

    public TopType(Integer id) {
        setId(id);
    }

    public TopType(Integer id, String title) {
        setId(id);
        setTitle(title);
    }

}
