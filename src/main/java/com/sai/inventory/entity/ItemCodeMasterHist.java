package com.sai.inventory.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "item_master_hist")
public class ItemCodeMasterHist extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_MASTER_HIST_ID", nullable = false)
    private Long id;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "ITEM_MASTER_ID")
    private Long itemMasterId;
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
    @Column(name = "PRE_QUANTITY")
    private Double preQuantity;
    @Column(name = "POST_QUANTITY")
    private Double postQuantity;
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

    @Column(name = "PROCESS_STAGE")
    private String processStage;

    @Column(name = "TOTAL_PRE_QTY")
    private Double totalPreQuantity;
    @Column(name = "TOTAL_POST_QTY")
    private Double totalPostQuantity;

    @Column(name = "INIT_QTY")
    private Double initQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

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

    public Double getPreQuantity() {
        return preQuantity;
    }

    public void setPreQuantity(Double preQuantity) {
        this.preQuantity = preQuantity;
    }

    public Double getPostQuantity() {
        return postQuantity;
    }

    public void setPostQuantity(Double postQuantity) {
        this.postQuantity = postQuantity;
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

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public Double getTotalPreQuantity() {
        return totalPreQuantity;
    }

    public void setTotalPreQuantity(Double totalPreQuantity) {
        this.totalPreQuantity = totalPreQuantity;
    }

    public Double getTotalPostQuantity() {
        return totalPostQuantity;
    }

    public void setTotalPostQuantity(Double totalPostQuantity) {
        this.totalPostQuantity = totalPostQuantity;
    }

    public Double getInitQuantity() {
        return initQuantity;
    }

    public void setInitQuantity(Double initQty) {
        this.initQuantity = initQty;
    }
}
