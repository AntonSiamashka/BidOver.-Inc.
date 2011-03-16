package com.bidover.common.model.bean;

import com.bidover.common.model.bean.Condition;
import java.io.Serializable;
//import java.util.Date;

public class Lot implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Byte status;
    private Long distributionTime;
    private Long expirationTime;
    private User sellerId;
    private User buyerId;
    private Condition condition;
    private String location;
    private String currency;
    private String formattedTimeLeft;
    private String formattedExpirationTime;
    private Double fixedPrice;
    private Double floorPrice;
    private Byte forbidBidding;
    private Byte fixedPriceOnly;
    private Double startingBid;
    private Double currentBid;
    private Double outBid;
    private Double shippingCost;
    private String paymentMethod;
    private String paymentInstructions;
    private String freeShipping;
    private String paidShipping;
    private Integer handlingTime;
    private Integer itemsQnt;
    private String description;
    private int bidHistoryRecords;
    private long millisTimeLeft;
    private boolean isBiddingClosed;
    private boolean isBiddingPermitted;
    private boolean isLastHour;
    private boolean isFloorPriceReached;
    private boolean isCountDownStart;

//    private Date lotAddDateFormated;
//    private Date lotSaleDateFormated;
//    private Date salesDurationFormated;
    public Lot() {
    }

    public Lot(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Long distributionTime) {
        this.distributionTime = distributionTime;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getFormattedExpirationTime() {
        return formattedExpirationTime;
    }

    public void setFormattedExpirationTime(String formattedExpirationTime) {
        this.formattedExpirationTime = formattedExpirationTime;
    }

    public String getFormattedTimeLeft() {
        return formattedTimeLeft;
    }

    public void setFormattedTimeLeft(String formattedTimeLeft) {
        this.formattedTimeLeft = formattedTimeLeft;
    }

    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User sellerId) {
        this.sellerId = sellerId;
    }

    public User getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(User buyerId) {
        this.buyerId = buyerId;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte getForbidBidding() {
        return forbidBidding;
    }

    public void setForbidBidding(Byte forbidBidding) {
        this.forbidBidding = forbidBidding;
    }

    public Double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(Double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public Byte getFixedPriceOnly() {
        return fixedPriceOnly;
    }

    public void setFixedPriceOnly(Byte fixedPriceOnly) {
        this.fixedPriceOnly = fixedPriceOnly;
    }

    public Double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Double floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Double getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(Double startingBid) {
        this.startingBid = startingBid;
    }

    public Double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Double currentBid) {
        this.currentBid = currentBid;
    }

    public Double getOutBid() {
        return outBid;
    }

    public void setOutBid(Double outBid) {
        this.outBid = outBid;
    }

    public boolean getIsFloorPriceReached() {
        return isFloorPriceReached;
    }

    public void setIsFloorPriceReached(boolean isFloorPriceReached) {
        this.isFloorPriceReached = isFloorPriceReached;
    }

    public int getBidHistoryRecords() {
        return bidHistoryRecords;
    }

    public void setBidHistoryRecords(int biddingRecords) {
        this.bidHistoryRecords = biddingRecords;
    }

    public Integer getItemsQnt() {
        return itemsQnt;
    }

    public void setItemsQnt(Integer itemsQnt) {
        this.itemsQnt = itemsQnt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentInstructions() {
        return paymentInstructions;
    }

    public void setPaymentInstructions(String paymentInstructions) {
        this.paymentInstructions = paymentInstructions;
    }

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getPaidShipping() {
        return paidShipping;
    }

    public void setPaidShipping(String paidShipping) {
        this.paidShipping = paidShipping;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(Integer handlingTime) {
        this.handlingTime = handlingTime;
    }

    public long getMillisTimeLeft() {
        return millisTimeLeft;
    }

    public void setMillisTimeLeft(long millisTimeLeft) {
        this.millisTimeLeft = millisTimeLeft;
    }

    public boolean getIsBiddingClosed() {
        return isBiddingClosed;
    }

    public void setIsBiddingClosed(boolean isBiddingClosed) {
        this.isBiddingClosed = isBiddingClosed;
    }

    public boolean getIsLastHour() {
        return isLastHour;
    }

    public void setIsLastHour(boolean isLastHour) {
        this.isLastHour = isLastHour;
    }

    public boolean getIsCountDownStart() {
        return isCountDownStart;
    }

    public void setIsCountDownStart(boolean isCountDownStart) {
        this.isCountDownStart = isCountDownStart;
    }

    public boolean getIsBiddingPermitted() {
        return isBiddingPermitted;
    }

    public void setIsBiddingPermitted(boolean isBiddingPermitted) {
        this.isBiddingPermitted = isBiddingPermitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    /*

    public Date getLotAddDateFormated() {
    if (lotAddDate != null) {
    lotAddDateFormated = new Date(lotAddDate);
    }
    return lotAddDateFormated;
    }

    public Date getLotSaleDateFormated() {
    if (lotSaleDate != null) {
    lotSaleDateFormated = new Date(lotSaleDate);
    }
    return lotSaleDateFormated;
    }

    public Date getSalesDurationFormated() {
    if (salesDuration != null) {
    salesDurationFormated = new Date(salesDuration);
    }
    return salesDurationFormated;
    }

    public Long getSalesDuration() {
    return salesDuration;
    }

    public void setSalesDuration(Long salesDuration) {
    this.salesDuration = salesDuration;
    }

    public Long getLotSaleDate() {
    return lotSaleDate;
    }

    public void setLotSaleDate(Long lotSaleDate) {
    this.lotSaleDate = lotSaleDate;
    }

     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lot)) {
            return false;
        }
        Lot other = (Lot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bidoverdb.bean.Lot[id=" + id + "]";
    }
}
