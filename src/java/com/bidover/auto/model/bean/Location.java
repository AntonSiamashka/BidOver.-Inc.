
package com.bidover.auto.model.bean;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    private String country_code;
    private String atd_code;
    private String location_code;
    private String country;
    private String atd;
    private String location;
    private Byte main_city;

    public Location() {
    }

    public Location(String location_code) {
        this.location_code = location_code;
    }

    public Location(String country_code, String atd_code, String location_code, String country, String atd, String location, Byte main_city) {
        this.country_code = country_code;
        this.atd_code = atd_code;
        this.location_code = location_code;
        this.country = country;
        this.atd = atd;
        this.location = location;
        this.main_city = main_city;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public String getATDCode() {
        return atd_code;
    }

    public void setATDCode(String atd_code) {
        this.atd_code = atd_code;
    }

    public String getLocationCode() {
        return location_code;
    }

    public void setLocationCode(String location_code) {
        this.location_code = location_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getATD() {
        return atd;
    }

    public void setATD(String atd) {
        this.atd = atd;
    }       

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte getMainCity() {
        return main_city;
    }

    public void setMainCity(Byte main_city) {
        this.main_city = main_city;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (location_code != null ? location_code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.location_code == null && other.location_code != null) || (this.location_code != null && !this.location_code.equals(other.location_code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Locations[location_code=" + location_code + "]";
    }

}
