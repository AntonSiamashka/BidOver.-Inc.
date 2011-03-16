
package com.bidover.auto.model.bean;

import java.io.Serializable;

public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    private String currency_code;
    private String place_used;
    private Double rates;

    public Currency() {
    }

    public Currency(String currency_code) {
        this.currency_code = currency_code;
    }

    public Currency(String currency_code, String place_used, Double rates) {
        this.currency_code = currency_code;
        this.place_used = place_used;
        this.rates = rates;
    }

    public String getCurrencyCode() {
        return currency_code;
    }

    public void setCurrencyCode(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getPlaceUsed() {
        return place_used;
    }

    public void setPlaceUsed(String place_used) {
        this.place_used = place_used;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currency_code != null ? currency_code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.currency_code == null && other.currency_code != null) || (this.currency_code != null && !this.currency_code.equals(other.currency_code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Currency[currency_code=" + currency_code + "]";
    }

}
