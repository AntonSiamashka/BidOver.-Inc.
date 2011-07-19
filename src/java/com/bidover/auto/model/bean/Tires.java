/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.model.bean;

import java.io.Serializable;


/*
 *
 * @author Jedai
 */
public class Tires extends NamedBean {

    public Tires() {
    }

    public Tires(Integer id) {
        setId(id);
    }

    public Tires(Integer id, String title) {
        setId(id);
        setTitle(title);
    }
}
