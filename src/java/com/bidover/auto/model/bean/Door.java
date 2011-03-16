/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.model.bean;

/**
 *
 * @author Jedai
 */
public class Door {

    private Integer id = null;
    private String title = null;

    public Door() {
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Door other = (Door) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }

    public String toXMLString() {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<door>");
        xml.append("<id>");
        xml.append(this.id);
        xml.append("</id>");
        xml.append("<title>");
        xml.append(this.title);
        xml.append("</title>");
        xml.append("</door>");
        return xml.toString();
    }
}
