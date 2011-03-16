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
public class TopType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;

    public TopType() {
    }

    public TopType(Integer id) {
        this.id = id;
    }

    public TopType(Integer id, String title) {
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
        if (!(object instanceof TopType)) {
            return false;
        }
        TopType other = (TopType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.newpackage.TopType[id=" + id + "]";
    }

    public String toXMLString() {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<top-type>");
        xml.append("<id>");
        xml.append(this.id);
        xml.append("</id>");
        xml.append("<title>");
        xml.append(this.title);
        xml.append("</title>");
        xml.append("</top-type>");
        return xml.toString();
    }

}
