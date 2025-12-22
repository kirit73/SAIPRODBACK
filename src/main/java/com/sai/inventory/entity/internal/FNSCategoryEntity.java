package com.sai.inventory.entity.internal;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class FNSCategoryEntity {
    @EmbeddedId
    private FNSCategoryEntityPK id;
    @Column(name = "ITEM_MASTER_DESC")
    private String itemMasterDesc;
    @Column(name = "SUBCATEGORY")
    private String subCategory;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "Create_Dt")
    private Date createDt;
    @Column(name = "SUBCATEGORY_DESC")
    private String subCategoryDesc;

    @Column(name = "ORG_ID")
    private Long orgId;

    public FNSCategoryEntityPK getId() {
        return id;
    }

    public void setId(FNSCategoryEntityPK id) {
        this.id = id;
    }

    public String getItemMasterDesc() {
        return itemMasterDesc;
    }

    public void setItemMasterDesc(String itemMasterDesc) {
        this.itemMasterDesc = itemMasterDesc;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getSubCategoryDesc() {
        return subCategoryDesc;
    }

    public void setSubCategoryDesc(String subCategoryDesc) {
        this.subCategoryDesc = subCategoryDesc;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
