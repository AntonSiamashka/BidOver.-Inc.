/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.auto.model.bean;

/**
 *
 * @author Jedai
 */

public class DriveTrain extends NamedBean {

    public DriveTrain() {
    }

    public DriveTrain(Integer id) {
        setId(id);
    }

    public DriveTrain(Integer id, String title) {
        setId(id);
        setTitle(title);
    }

}
