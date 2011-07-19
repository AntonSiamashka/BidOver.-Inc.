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
public class NamedBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer id = null;
    private String title = null;

    public NamedBean() {
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
        final NamedBean other = (NamedBean) obj;
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
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }
}
