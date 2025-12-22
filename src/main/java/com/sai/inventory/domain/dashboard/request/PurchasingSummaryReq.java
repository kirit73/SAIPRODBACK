package com.sai.inventory.domain.dashboard.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PurchasingSummaryReq {

    private String startDate;
    private String endDate;
    private String itemCode;
    @JsonIgnore
    private Date startDateDt;
    @JsonIgnore
    private Date endDateDt;
    private Long orgId;
    private String categoryCode;
    private String subCategoryCode;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Date getStartDateDt() {
        return startDateDt;
    }

    public void setStartDateDt(Date startDateDt) {
        this.startDateDt = startDateDt;
    }

    public Date getEndDateDt() {
        return endDateDt;
    }

    public void setEndDateDt(Date endDateDt) {
        this.endDateDt = endDateDt;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }
}
