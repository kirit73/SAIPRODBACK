package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class ProcessBaseReq {
    private String genDate;
    private String genName;
    private String issueDate;
    private String issueName;
    private String approvedDate;
    private String approvedName;

    @JsonIgnore
    private Date genDateDt;
    @JsonIgnore
    private Date issueDateDt;
    @JsonIgnore
    private Date approvedDateDt;

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedName() {
        return approvedName;
    }

    public void setApprovedName(String approvedName) {
        this.approvedName = approvedName;
    }

    public Date getGenDateDt() {
        return genDateDt;
    }

    public void setGenDateDt(Date genDateDt) {
        this.genDateDt = genDateDt;
    }

    public Date getIssueDateDt() {
        return issueDateDt;
    }

    public void setIssueDateDt(Date issueDateDt) {
        this.issueDateDt = issueDateDt;
    }

    public Date getApprovedDateDt() {
        return approvedDateDt;
    }

    public void setApprovedDateDt(Date approvedDateDt) {
        this.approvedDateDt = approvedDateDt;
    }
}
