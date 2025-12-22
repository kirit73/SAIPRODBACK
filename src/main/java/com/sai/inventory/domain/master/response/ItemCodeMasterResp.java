package com.sai.inventory.domain.master.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.entity.UOMMaster;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ItemCodeMasterResp {

    private Long id;
    private String itemMasterCd;
    private String itemMasterDesc;
    private Long uomId;
    private String category;
    private String categoryDesc;
    private String subCategory;
    private String subCategoryDesc;
    private String type;
    private String typeDesc;
    private String disciplines;
    private String disciplinesDesc;
    private String itemName;
    private String size;
    private String sizeDesc;
    private String brandId;
    private String brandDesc;
    private String colorId;
    private String colorDesc;
    private String usageCategory;
    private String usageCategoryDesc;
    @JsonIgnore
    private Double quantity;
    @JsonIgnore
    private Long locationId;

    @JsonIgnore
    private Long locatorId;

    private UOMMaster uomDtls;
    private BigDecimal price;
    private Long vendorId;
    private Long minStockLevel;
    private Long maxStockLevel;
    private String status;
    private Date endDate;
    private String reOrderPoint;
    private String endDateString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReOrderPoint() {
        return reOrderPoint;
    }

    public void setReOrderPoint(String reOrderPoint) {
        this.reOrderPoint = reOrderPoint;
    }

    public UOMMaster getUomDtls() {
        return uomDtls;
    }

    public void setUomDtls(UOMMaster uomDtls) {
        this.uomDtls = uomDtls;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getSubCategoryDesc() {
        return subCategoryDesc;
    }

    public void setSubCategoryDesc(String subCategoryDesc) {
        this.subCategoryDesc = subCategoryDesc;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getDisciplinesDesc() {
        return disciplinesDesc;
    }

    public void setDisciplinesDesc(String disciplinesDesc) {
        this.disciplinesDesc = disciplinesDesc;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getUsageCategoryDesc() {
        return usageCategoryDesc;
    }

    public void setUsageCategoryDesc(String usageCategoryDesc) {
        this.usageCategoryDesc = usageCategoryDesc;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCodeMasterResp that = (ItemCodeMasterResp) o;
        return itemMasterCd.equals(that.itemMasterCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemMasterCd);
    }
}
