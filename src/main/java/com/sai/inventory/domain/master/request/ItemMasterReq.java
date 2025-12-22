package com.sai.inventory.domain.master.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

public class ItemMasterReq {
    private Long itemMasterId;
    private String itemMasterCd;
    private String itemMasterDesc;
    private Long uomId;
    private String category;
    private String subCategory;
    private String type;
    private String disciplines;
    private String brandId;
    private String colorId;
    private String usageCategory;
    private Double quantity;
    private Long locationId;
    private Long locatorId;
    private BigDecimal price;
    private Long vendorId;
    private Long minStockLevel;
    private Long maxStockLevel;

    private String itemName;
    private String size;
    private String endDate;
    @JsonIgnore
    private Date endDateInDate;
    private String createUserId;

    private String reOrderPoint;

    @JsonIgnore
    private Long orgId;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getUsageCategory() {
        return usageCategory;
    }

    public void setUsageCategory(String usageCategory) {
        this.usageCategory = usageCategory;
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

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Long minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Long getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(Long maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public Date getEndDateInDate() {
        return endDateInDate;
    }

    public void setEndDateInDate(Date endDateInDate) {
        this.endDateInDate = endDateInDate;
    }

    public Long getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(Long itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getReOrderPoint() {
        return reOrderPoint;
    }

    public void setReOrderPoint(String reOrderPoint) {
        this.reOrderPoint = reOrderPoint;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
