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
public class Damage implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer hood;
    private Integer roof;
    private Integer windshield;
    private Integer lfDoor;
    private Integer lrDoor;
    private Integer rfDoor;
    private Integer rrDoor;
    private Integer lQtrPanel;
    private Integer rQtrPanel;
    private Integer frontBumper;
    private Integer rearBumper;
    private Integer lfFender;
    private Integer rfFender;
    private Integer deckLid;
    private Integer seats;
    private Integer overallVehicle;
    private String hoodStr;
    private String roofStr;
    private String windshieldStr;
    private String lfDoorStr;
    private String lrDoorStr;
    private String rfDoorStr;
    private String rrDoorStr;
    private String lQtrPanelStr;
    private String rQtrPanelStr;
    private String frontBumperStr;
    private String rearBumperStr;
    private String lfFenderStr;
    private String rfFenderStr;
    private String deckLidStr;
    private String seatsStr;
    private String overallVehicleStr;

    public Damage() {
    }

    public Damage(Integer id) {
        this.id = id;
    }

    public Damage(Integer id, Integer hood, Integer roof, Integer windshield, Integer lfDoor, Integer lrDoor, Integer rfDoor, Integer rrDoor, Integer lQtrPanel, Integer rQtrPanel, Integer frontBumper, Integer rearBumper, Integer lfFender, Integer rfFender, Integer deckLid, Integer seats, Integer overallVehicle) {
        this.id = id;

        this.hood = hood;
        //this.hoodStr = intToStr(hood);

        this.roof = roof;
        //this.roofStr = intToStr(roof);


        this.windshield = windshield;
        //this.windshieldStr = intToStr(windshield);


        this.lfDoor = lfDoor;
        //this.lfDoorStr = intToStr(lfDoor);

        this.lrDoor = lrDoor;
        //this.lrDoorStr = intToStr(lrDoor);

        this.rfDoor = rfDoor;
        //this.rfDoorStr = intToStr(rfDoor);

        this.rrDoor = rrDoor;
        //this.rrDoorStr = intToStr(rrDoor);

        this.lQtrPanel = lQtrPanel;
        //this.lQtrPanelStr = intToStr(lQtrPanel);

        this.rQtrPanel = rQtrPanel;
        //this.rQtrPanelStr = intToStr(rQtrPanel);

        this.frontBumper = frontBumper;
        //this.frontBumperStr = intToStr(frontBumper);

        this.rearBumper = rearBumper;
        //this.rearBumperStr = intToStr(rearBumper);

        this.lfFender = lfFender;
        //this.lfFenderStr = intToStr(lfFender);

        this.rfFender = rfFender;
        //this.rfFenderStr = intToStr(rfFender);

        this.deckLid = deckLid;
        //this.deckLidStr = intToStr(deckLid);

        this.seats = seats;
        //this.seatsStr = intToStr(seats);

        this.overallVehicle = overallVehicle;
        //this.overallVehicleStr = intToStr(overallVehicle);
    }

//    public String intToStr(Integer i) {
//        if (i != null) {
//            switch (i) {
//                case 1:
//                    return "Chipped";
//                case 2:
//                    return "Scratched";
//                case 3:
//                    return "Scratch Heavy";
//                case 4:
//                    return "Mult Scratches/Light";
//                case 5:
//                    return " Worn";
//                case 6:
//                    return "Torn";
//                case 7:
//                    return "Stained";
//                case 8:
//                    return "Dent";
//                case 9:
//                    return "Dent/No Paint Dmg";
//                case 10:
//                    return "Dent/Paint Dmg";
//                case 11:
//                    return "Dirty";
//                case 12:
//                    return "After Market";
//                default:
//                    return "Not specified";
//            }
//        }
//        return "Not specified";
//    }

    public String getDeckLidStr() {
        return deckLidStr;
    }

    public void setDeckLidStr(String deckLidStr) {
        this.deckLidStr = deckLidStr;
    }

    public String getFrontBumperStr() {
        return frontBumperStr;
    }

    public void setFrontBumperStr(String frontBumperStr) {
        this.frontBumperStr = frontBumperStr;
    }

    public String getHoodStr() {
        return hoodStr;
    }

    public void setHoodStr(String hoodStr) {
        this.hoodStr = hoodStr;
    }

    public Integer getlQtrPanel() {
        return lQtrPanel;
    }

    public void setlQtrPanel(Integer lQtrPanel) {
        this.lQtrPanel = lQtrPanel;
    }

    public String getlQtrPanelStr() {
        return lQtrPanelStr;
    }

    public void setlQtrPanelStr(String lQtrPanelStr) {
        this.lQtrPanelStr = lQtrPanelStr;
    }

    public String getLfDoorStr() {
        return lfDoorStr;
    }

    public void setLfDoorStr(String lfDoorStr) {
        this.lfDoorStr = lfDoorStr;
    }

    public String getLfFenderStr() {
        return lfFenderStr;
    }

    public void setLfFenderStr(String lfFenderStr) {
        this.lfFenderStr = lfFenderStr;
    }

    public String getLrDoorStr() {
        return lrDoorStr;
    }

    public void setLrDoorStr(String lrDoorStr) {
        this.lrDoorStr = lrDoorStr;
    }

    public String getOverallVehicleStr() {
        return overallVehicleStr;
    }

    public void setOverallVehicleStr(String overallVehicleStr) {
        this.overallVehicleStr = overallVehicleStr;
    }

    public Integer getrQtrPanel() {
        return rQtrPanel;
    }

    public void setrQtrPanel(Integer rQtrPanel) {
        this.rQtrPanel = rQtrPanel;
        //this.rQtrPanelStr = intToStr(rQtrPanel);
    }

    public String getrQtrPanelStr() {
        return rQtrPanelStr;
    }

    public void setrQtrPanelStr(String rQtrPanelStr) {
        this.rQtrPanelStr = rQtrPanelStr;
    }

    public String getRearBumperStr() {
        return rearBumperStr;
    }

    public void setRearBumperStr(String rearBumperStr) {
        this.rearBumperStr = rearBumperStr;
    }

    public String getRfDoorStr() {
        return rfDoorStr;
    }

    public void setRfDoorStr(String rfDoorStr) {
        this.rfDoorStr = rfDoorStr;
    }

    public String getRfFenderStr() {
        return rfFenderStr;
    }

    public void setRfFenderStr(String rfFenderStr) {
        this.rfFenderStr = rfFenderStr;
    }

    public String getRoofStr() {
        return roofStr;
    }

    public void setRoofStr(String roofStr) {
        this.roofStr = roofStr;
    }

    public String getRrDoorStr() {
        return rrDoorStr;
    }

    public void setRrDoorStr(String rrDoorStr) {
        this.rrDoorStr = rrDoorStr;
    }

    public String getSeatsStr() {
        return seatsStr;
    }

    public void setSeatsStr(String seatsStr) {
        this.seatsStr = seatsStr;
    }

    public String getWindshieldStr() {
        return windshieldStr;
    }

    public void setWindshieldStr(String windshieldStr) {
        this.windshieldStr = windshieldStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHood() {
        return hood;
    }

    public void setHood(Integer hood) {
        //this.hoodStr = intToStr(hood);
        this.hood = hood;
    }

    public Integer getRoof() {
        return roof;
    }

    public void setRoof(Integer roof) {
        //this.roofStr = intToStr(roof);
        this.roof = roof;
    }

    public Integer getWindshield() {
        return windshield;
    }

    public void setWindshield(Integer windshield) {
        //this.windshieldStr = intToStr(windshield);
        this.windshield = windshield;
    }

    public Integer getLfDoor() {
        return lfDoor;
    }

    public void setLfDoor(Integer lfDoor) {
        //this.lfDoorStr = intToStr(lfDoor);
        this.lfDoor = lfDoor;
    }

    public Integer getLrDoor() {
        return lrDoor;
    }

    public void setLrDoor(Integer lrDoor) {
        //this.lrDoorStr = intToStr(lrDoor);
        this.lrDoor = lrDoor;
    }

    public Integer getRfDoor() {
        return rfDoor;
    }

    public void setRfDoor(Integer rfDoor) {
        //this.rfDoorStr = intToStr(rfDoor);
        this.rfDoor = rfDoor;
    }

    public Integer getRrDoor() {
        return rrDoor;
    }

    public void setRrDoor(Integer rrDoor) {
        //this.rrDoorStr = intToStr(rrDoor);
        this.rrDoor = rrDoor;
    }

    public Integer getLQtrPanel() {
        return lQtrPanel;
    }

    public void setLQtrPanel(Integer lQtrPanel) {
        //this.lQtrPanelStr = intToStr(lQtrPanel);
        this.lQtrPanel = lQtrPanel;
    }

    public Integer getRQtrPanel() {
        return rQtrPanel;
    }

    public void setRQtrPanel(Integer rQtrPanel) {
        //this.rQtrPanelStr = intToStr(rQtrPanel);
        this.rQtrPanel = rQtrPanel;
    }

    public Integer getFrontBumper() {
        return frontBumper;
    }

    public void setFrontBumper(Integer frontBumper) {
        //this.frontBumperStr = intToStr(frontBumper);
        this.frontBumper = frontBumper;
    }

    public Integer getRearBumper() {
        return rearBumper;
    }

    public void setRearBumper(Integer rearBumper) {
        //this.rearBumperStr = intToStr(rearBumper);
        this.rearBumper = rearBumper;
    }

    public Integer getLfFender() {
        return lfFender;
    }

    public void setLfFender(Integer lfFender) {
        //this.lfFenderStr = intToStr(lfFender);
        this.lfFender = lfFender;
    }

    public Integer getRfFender() {
        return rfFender;
    }

    public void setRfFender(Integer rfFender) {
        //this.rfFenderStr = intToStr(rfFender);
        this.rfFender = rfFender;
    }

    public Integer getDeckLid() {
        return deckLid;
    }

    public void setDeckLid(Integer deckLid) {
        //this.deckLidStr = intToStr(deckLid);
        this.deckLid = deckLid;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        //this.seatsStr = intToStr(seats);
        this.seats = seats;
    }

    public Integer getOverallVehicle() {
        return overallVehicle;
    }

    public void setOverallVehicle(Integer overallVehicle) {
        //this.overallVehicleStr = intToStr(overallVehicle);
        this.overallVehicle = overallVehicle;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Damage[id=" + id + "]";
    }
}
