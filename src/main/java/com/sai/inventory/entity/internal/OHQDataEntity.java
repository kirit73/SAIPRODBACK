package com.sai.inventory.entity.internal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class OHQDataEntity {

    @Id
    @Column(name = "ITEM_MASTER_ID")
    private Long itemMasterId;
    @Column(name = "ITEM_MASTER_CD")
    private String itemMasterCd;
    @Column(name = "ITEM_MASTER_DESC")
    private String itemMasterDesc;
    @Column(name = "UOM_ID")
    private Long uomId;
    @Column(name = "LOCATOR_MASTER_ID")
    private Long locatorId;
    @Column(name = "UOM_NAME")
    private String uomName;
    @Column(name = "LOCATOR_DESC")
    private String locatorDesc;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "LOCATION_NAME")
    private String locationName;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "SUBCATEGORY")
    private BigDecimal subCategory;

    @Column(name = "SUBCATEGORY_DESC")
    private String subCategoryDesc;

    public Long getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(Long itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    public String getItemMasterCd() {
        return itemMasterCd;
    }

    public void setItemMasterCd(String itemMasterCd) {
        this.itemMasterCd = itemMasterCd;
    }

    public String getItemMasterDesc() {
        return itemMasterDesc;
    }

    public void setItemMasterDesc(String itemMasterDesc) {
        this.itemMasterDesc = itemMasterDesc;
    }

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getLocatorDesc() {
        return locatorDesc;
    }

    public void setLocatorDesc(String locatorDesc) {
        this.locatorDesc = locatorDesc;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(BigDecimal subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategoryDesc() {
        return subCategoryDesc;
    }

    public void setSubCategoryDesc(String subCategoryDesc) {
        this.subCategoryDesc = subCategoryDesc;
    }
}
