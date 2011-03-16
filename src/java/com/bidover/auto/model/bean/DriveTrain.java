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

public class DriveTrain implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;

    public DriveTrain() {
    }

    public DriveTrain(Integer id) {
        this.id = id;
    }

    public DriveTrain(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(object instanceof DriveTrain)) {
            return false;
        }
        DriveTrain other = (DriveTrain) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.newpackage.DriveTrain[id=" + id + "]";
    }

    public String toXMLString() {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<drive-train>");
        xml.append("<id>");
        xml.append(this.id);
        xml.append("</id>");
        xml.append("<title>");
        xml.append(this.title);
        xml.append("</title>");
        xml.append("</drive-train>");
        return xml.toString();
    }

}
