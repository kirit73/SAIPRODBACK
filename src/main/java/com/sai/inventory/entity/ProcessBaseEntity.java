package com.sai.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class ProcessBaseEntity extends BaseEntity {

    @Column(name = "GEN_DT")
    private Date genDate;
    @Column(name = "GEN_NAME")
    private String genName;
    @Column(name = "ISSUE_DT")
    private Date issueDate;
    @Column(name = "ISSUE_NAME")
    private String issueName;
    @Column(name = "APRVD_DT")
    private Date approvedDate;
    @Column(name = "APRVD_NAME")
    private String approvedName;

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedName() {
        return approvedName;
    }

    public void setApprovedName(String approvedName) {
        this.approvedName = approvedName;
    }
}
