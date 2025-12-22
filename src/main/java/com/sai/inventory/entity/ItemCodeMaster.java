package com.sai.inventory.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ITEM_MASTER")
public class ItemCodeMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_MASTER_ID", nullable = false)
    private Long id;
    @Column(name = "ITEM_MASTER_CD")
    private String itemMasterCd;
    @Column(name = "ITEM_MASTER_DESC")
    private String itemMasterDesc;
    @Column(name = "UOM_ID")
    private Long uomId;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "SUBCATEGORY")
    private String subCategory;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DISCIPLINES")
    private String disciplines;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "SIZE")
    private String size;
    @Column(name = "BRAND_ID")
    private String brandId;
    @Column(name = "COLOR_ID")
    private String colorId;
    @Column(name = "USAGE_CATEGORY")
    private String usageCategory;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Column(name = "LOCATOR_ID")
    private Long locatorId;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "VENDOR_ID")
    private Long vendorId;
    @Column(name = "MIN_STOCK_LEVEL")
    private Long minStockLevel;
    @Column(name = "MAX_STOCK_LEVEL")
    private Long maxStockLevel;
    @Column(name = "STATUS")
    private String status;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "RE_ORDER_POINT")
    private String reOrderPoint;

    @Column(name = "INIT_QTY")
    private Double initQuantity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCodeMaster that = (ItemCodeMaster) o;
        return itemMasterCd.equals(that.itemMasterCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemMasterCd);
    }

    public Double getInitQuantity() {
        return initQuantity;
    }

    public void setInitQuantity(Double initQuantity) {
        this.initQuantity = initQuantity;
    }
}
