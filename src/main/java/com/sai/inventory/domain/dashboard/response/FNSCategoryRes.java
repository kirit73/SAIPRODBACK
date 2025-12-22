package com.sai.inventory.domain.dashboard.response;

public class FNSCategoryRes {

    private String itemCode;
    private String itemDescription;
    private String subCategory;
    private String fnsCategory;
    private String lastTxnDate;
    private String subCategoryDesc;
    private Long orgId;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getFnsCategory() {
        return fnsCategory;
    }

    public void setFnsCategory(String fnsCategory) {
        this.fnsCategory = fnsCategory;
    }

    public void setLastTxnDate(String lastTxnDate) {
        this.lastTxnDate = lastTxnDate;
    }

    public String getLastTxnDate() {
        return lastTxnDate;
    }

    public void setSubCategoryDesc(String subCategoryDesc) {
        this.subCategoryDesc = subCategoryDesc;
    }

    public String getSubCategoryDesc() {
        return subCategoryDesc;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
