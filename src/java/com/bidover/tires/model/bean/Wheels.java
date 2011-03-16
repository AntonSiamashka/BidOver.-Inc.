/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.model.bean;

/**
 *
 * @author Jedai
 */
public class Wheels {

    private Integer id = null;
    private WheelsWidth wheelsWidth = null;
    private WheelsDiameter wheelsDiameter = null;
    private WheelsBCD wheelsBCD = null;
    private WheelsED wheelsED = null;

    public Wheels() {
    }

    public Wheels(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WheelsBCD getWheelsBCD() {
        return wheelsBCD;
    }

    public void setWheelsBCD(WheelsBCD wheelsBCD) {
        this.wheelsBCD = wheelsBCD;
    }

    public WheelsDiameter getWheelsDiameter() {
        return wheelsDiameter;
    }

    public void setWheelsDiameter(WheelsDiameter wheelsDiameter) {
        this.wheelsDiameter = wheelsDiameter;
    }

    public WheelsED getWheelsED() {
        return wheelsED;
    }

    public void setWheelsED(WheelsED wheelsED) {
        this.wheelsED = wheelsED;
    }

    public WheelsWidth getWheelsWidth() {
        return wheelsWidth;
    }

    public void setWheelsWidth(WheelsWidth wheelsWidth) {
        this.wheelsWidth = wheelsWidth;
    }



    @Override
    public String toString() {
        return "Wheels id=" + id;
    }
}
