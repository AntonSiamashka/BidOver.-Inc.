/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.model.bean;

/**
 *
 * @author Jedai
 */
public class Tires {

    private Integer id = null;
    private TiresWidth tiresWidth = null;
    private TiresDiameter tiresDiameter = null;
    private TiresContour tiresContour = null;
    private TiresSeason tiresSeason = null;
    private TiresMake tiresMake = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TiresContour getTiresContour() {
        return tiresContour;
    }

    public void setTiresContour(TiresContour tiresContour) {
        this.tiresContour = tiresContour;
    }

    public TiresDiameter getTiresDiameter() {
        return tiresDiameter;
    }

    public void setTiresDiameter(TiresDiameter tiresDiameter) {
        this.tiresDiameter = tiresDiameter;
    }

    public TiresSeason getTiresSeason() {
        return tiresSeason;
    }

    public void setTiresSeason(TiresSeason tiresSeason) {
        this.tiresSeason = tiresSeason;
    }

    public TiresWidth getTiresWidth() {
        return tiresWidth;
    }

    public void setTiresWidth(TiresWidth tiresWidth) {
        this.tiresWidth = tiresWidth;
    }

    public TiresMake getTiresMake() {
        return tiresMake;
    }

    public void setTiresMake(TiresMake tiresMake) {
        this.tiresMake = tiresMake;
    }

    @Override
    public String toString() {
        return "Tires id=" + id;
    }
}
