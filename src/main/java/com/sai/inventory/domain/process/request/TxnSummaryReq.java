package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class TxnSummaryReq {
    private String startDate;
    private String endDate;
    private String itemCode;

    private String txnType;
    @JsonIgnore
    private Date startDateDt;
    @JsonIgnore
    private Date endDateDt;

    //@JsonIgnore
    private Long orgId;

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

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
