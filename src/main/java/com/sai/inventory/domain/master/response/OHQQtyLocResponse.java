package com.sai.inventory.domain.master.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OHQQtyLocResponse {


    private Long locatorId;
    private Double quantity;
    private String locatorDesc;
    private Long itemId;

    private Long locationId;
    private String locationName;
    private BigDecimal price;
    private BigDecimal totalValues;
    private BigDecimal subcategory;
    private String subcategoryDesc;

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getLocatorDesc() {
        return locatorDesc;
    }

    public void setLocatorDesc(String locatorDesc) {
        this.locatorDesc = locatorDesc;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTotalValues(BigDecimal totalValues) {
        this.totalValues = totalValues;
    }

    public BigDecimal getTotalValues() {
        return totalValues;
    }

    public void setSubcategory(BigDecimal subcategory) {
        this.subcategory = subcategory;
    }

    public BigDecimal getSubcategory() {
        return subcategory;
    }

    public void setSubcategoryDesc(String subcategoryDesc) {
        this.subcategoryDesc = subcategoryDesc;
    }

    public String getSubcategoryDesc() {
        return subcategoryDesc;
    }
}
