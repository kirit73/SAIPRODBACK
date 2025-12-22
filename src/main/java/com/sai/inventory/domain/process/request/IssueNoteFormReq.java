package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;

import java.util.Date;
import java.util.List;

public class IssueNoteFormReq extends ProcessBaseReq {
    private String demandNoteNo;
    private String issueNoteNo;
    private String issueNoteDt;
    private String type;
    private String ceRegionalCenterCd;
    private String ceRegionalCenterName;
    private String ceAddress;
    private String ceZipcode;
    private String crRegionalCenterCd;
    private String crRegionalCenterName;
    private String crAddress;
    private String crZipcode;
    private String consumerName;
    private String contactNo;
    private String termsCondition;
    private String note;
    private String demandNoteDt;

    @JsonIgnore
    private String correctionProcessFlag = "N";

    @JsonIgnore
    private Date demandNoteDateDt;
    @JsonIgnore
    private Date issueNoteDateDt;
    private List<ItemDetailsForm> items;
    private String userId;
    private String processType;

    private String interRdDemandNote;

    @JsonIgnore
    private Long orgId;

    public String getDemandNoteNo() {
        return demandNoteNo;
    }

    public void setDemandNoteNo(String demandNoteNo) {
        this.demandNoteNo = demandNoteNo;
    }

    public String getIssueNoteNo() {
        return issueNoteNo;
    }

    public void setIssueNoteNo(String issueNoteNo) {
        this.issueNoteNo = issueNoteNo;
    }

    public String getIssueNoteDt() {
        return issueNoteDt;
    }

    public void setIssueNoteDt(String issueNoteDt) {
        this.issueNoteDt = issueNoteDt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCeRegionalCenterCd() {
        return ceRegionalCenterCd;
    }

    public void setCeRegionalCenterCd(String ceRegionalCenterCd) {
        this.ceRegionalCenterCd = ceRegionalCenterCd;
    }

    public String getCeRegionalCenterName() {
        return ceRegionalCenterName;
    }

    public void setCeRegionalCenterName(String ceRegionalCenterName) {
        this.ceRegionalCenterName = ceRegionalCenterName;
    }

    public String getCeAddress() {
        return ceAddress;
    }

    public void setCeAddress(String ceAddress) {
        this.ceAddress = ceAddress;
    }

    public String getCeZipcode() {
        return ceZipcode;
    }

    public void setCeZipcode(String ceZipcode) {
        this.ceZipcode = ceZipcode;
    }

    public String getCrRegionalCenterCd() {
        return crRegionalCenterCd;
    }

    public void setCrRegionalCenterCd(String crRegionalCenterCd) {
        this.crRegionalCenterCd = crRegionalCenterCd;
    }

    public String getCrRegionalCenterName() {
        return crRegionalCenterName;
    }

    public void setCrRegionalCenterName(String crRegionalCenterName) {
        this.crRegionalCenterName = crRegionalCenterName;
    }

    public String getCrAddress() {
        return crAddress;
    }

    public void setCrAddress(String crAddress) {
        this.crAddress = crAddress;
    }

    public String getCrZipcode() {
        return crZipcode;
    }

    public void setCrZipcode(String crZipcode) {
        this.crZipcode = crZipcode;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getTermsCondition() {
        return termsCondition;
    }

    public void setTermsCondition(String termsCondition) {
        this.termsCondition = termsCondition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDemandNoteDt() {
        return demandNoteDt;
    }

    public void setDemandNoteDt(String demandNoteDt) {
        this.demandNoteDt = demandNoteDt;
    }

    public Date getDemandNoteDateDt() {
        return demandNoteDateDt;
    }

    public void setDemandNoteDateDt(Date demandNoteDateDt) {
        this.demandNoteDateDt = demandNoteDateDt;
    }

    public Date getIssueNoteDateDt() {
        return issueNoteDateDt;
    }

    public void setIssueNoteDateDt(Date issueNoteDateDt) {
        this.issueNoteDateDt = issueNoteDateDt;
    }

    public List<ItemDetailsForm> getItems() {
        return items;
    }

    public void setItems(List<ItemDetailsForm> items) {
        this.items = items;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getInterRdDemandNote() {
        return interRdDemandNote;
    }

    public void setInterRdDemandNote(String interRdDemandNote) {
        this.interRdDemandNote = interRdDemandNote;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCorrectionProcessFlag() {
        return correctionProcessFlag;
    }

    public void setCorrectionProcessFlag(String correctionProcessFlag) {
        this.correctionProcessFlag = correctionProcessFlag;
    }
}
