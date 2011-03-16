/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.model.bean;

import com.bidover.common.model.bean.Lot;
import java.io.Serializable;

/**
 *
 * @author Jedai
 */
public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String vin;
    private Integer id;
    private Integer odometer;
    private Integer year;
    private Door doors;
    private Engine engine;
    private Transmission transmission;
    private Tires idTires;
    private BodyStyle idBodyStyle;
    private DriveTrain idDriveTrain;
    private ExteriorColor idExteriorColor;
    private Fuel idFuel;
    private InteriorColor idInteriorColor;
    private Options idOptions;
    private TopType idTopType;
    private Wheels idWheels;
    private InteriorType interiorType;
    private Trim trim;
    private Lot lot;
    private Damage damage;
    private Characteristics characteristics;


    public Auto() {
    }

    public Auto(Integer id) {
        this.id = id;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public Door getDoors() {
        return doors;
    }

    public void setDoors(Door doors) {
        this.doors = doors;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BodyStyle getIdBodyStyle() {
        return idBodyStyle;
    }

    public void setIdBodyStyle(BodyStyle idBodyStyle) {
        this.idBodyStyle = idBodyStyle;
    }

    public DriveTrain getIdDriveTrain() {
        return idDriveTrain;
    }

    public void setIdDriveTrain(DriveTrain idDriveTrain) {
        this.idDriveTrain = idDriveTrain;
    }

    public ExteriorColor getIdExteriorColor() {
        return idExteriorColor;
    }

    public void setIdExteriorColor(ExteriorColor idExteriorColor) {
        this.idExteriorColor = idExteriorColor;
    }

    public Fuel getIdFuel() {
        return idFuel;
    }

    public void setIdFuel(Fuel idFuel) {
        this.idFuel = idFuel;
    }

    public InteriorColor getIdInteriorColor() {
        return idInteriorColor;
    }

    public void setIdInteriorColor(InteriorColor idInteriorColor) {
        this.idInteriorColor = idInteriorColor;
    }

    public Options getIdOptions() {
        return idOptions;
    }

    public void setIdOptions(Options idOptions) {
        this.idOptions = idOptions;
    }

    public Tires getIdTires() {
        return idTires;
    }

    public void setIdTires(Tires idTires) {
        this.idTires = idTires;
    }

    public TopType getIdTopType() {
        return idTopType;
    }

    public void setIdTopType(TopType idTopType) {
        this.idTopType = idTopType;
    }

    public Wheels getIdWheels() {
        return idWheels;
    }

    public void setIdWheels(Wheels idWheels) {
        this.idWheels = idWheels;
    }

    public InteriorType getInteriorType() {
        return interiorType;
    }

    public void setInteriorType(InteriorType interiorType) {
        this.interiorType = interiorType;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auto)) {
            return false;
        }
        Auto other = (Auto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.newpackage.Auto[id=" + id + "]";
    }
}
