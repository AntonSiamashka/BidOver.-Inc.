/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.model.bean;


/**
 *
 * @author Jedai
 */
public class DamageCondition extends NamedBean {

    public DamageCondition() {
    }

    public DamageCondition(Integer id) {
        setId(id);
    }

    public DamageCondition(Integer id, String title) {
        setId(id);
        setTitle(title);
    }

}
