/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bidover.common.model.bean;

import java.io.Serializable;
/**
 *
 * @author Jedai
 */
public class Timezone implements Serializable {
    private static final long serialVersionUID = 1L;
    private Short offset;
    private String timezone;
    private String location;
    private Integer id;

    public Timezone() {
    }

    public Timezone(Integer id) {
        this.id = id;
    }

    public Timezone(Integer id, Short offset, String timezone, String location) {
        this.id = id;
        this.offset = offset;
        this.timezone = timezone;
        this.location = location;
    }

    public Short getOffset() {
        return offset;
    }

    public void setOffset(Short offset) {
        this.offset = offset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Timezone)) {
            return false;
        }
        Timezone other = (Timezone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Timezones[id=" + id + "]";
    }

}
