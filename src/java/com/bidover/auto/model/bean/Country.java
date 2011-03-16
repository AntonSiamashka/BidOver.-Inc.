
package com.bidover.auto.model.bean;

import java.io.Serializable;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Country;
    private String iso_cc2;

    public Country() {
    }

    public Country(String iso_cc2) {
        this.iso_cc2 = iso_cc2;
    }

    public Country(String Country, String iso_cc2) {
        this.iso_cc2 = iso_cc2;
        this.Country = Country;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getISO_CC2() {
        return iso_cc2;
    }

    public void setISO_CC2(String iso_cc2) {
        this.iso_cc2 = iso_cc2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iso_cc2 != null ? iso_cc2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.iso_cc2 == null && other.iso_cc2 != null) || (this.iso_cc2 != null && !this.iso_cc2.equals(other.iso_cc2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Countries[iso_cc2=" + iso_cc2 + "]";
    }

}