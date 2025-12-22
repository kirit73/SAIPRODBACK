package com.sai.inventory.domain.dashboard.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class IssueNoteDataReq {

    private Long orgId;
    private String type;
    private String startDate;
    private String endDate;

    @JsonIgnore
    private Date startDateDt;
    @JsonIgnore
    private Date endDateDt;

    private boolean pendingReturn = false;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public void setStartDateDt(Date startDateDt) {
        this.startDateDt = startDateDt;
    }

    public Date getStartDateDt() {
        return startDateDt;
    }

    public void setEndDateDt(Date endDateDt) {
        this.endDateDt = endDateDt;
    }

    public Date getEndDateDt() {
        return endDateDt;
    }

    public boolean isPendingReturn() {
        return pendingReturn;
    }

    public void setPendingReturn(boolean pendingReturn) {
        this.pendingReturn = pendingReturn;
    }
}
