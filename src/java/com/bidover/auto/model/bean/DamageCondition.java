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
public class DamageCondition implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String condition;

    public DamageCondition() {
    }

    public DamageCondition(Integer id) {
        this.id = id;
    }

    public DamageCondition(Integer id, String condition) {
        this.id = id;
        this.condition = condition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
        if (!(object instanceof DamageCondition)) {
            return false;
        }
        DamageCondition other = (DamageCondition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.DamageCondition[id=" + id + "]";
    }

    public String toXMLString() {
        StringBuilder xml = new StringBuilder(100);
        xml.append("<damage-condition>");
        xml.append("<id>");
        xml.append(this.id);
        xml.append("</id>");
        xml.append("<title>");
        xml.append(this.condition);
        xml.append("</title>");
        xml.append("</damage-condition>");
        return xml.toString();
    }
}
