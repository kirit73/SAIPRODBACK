package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;

import java.util.Date;
import java.util.List;

public class ReturnNoteFormReq extends ProcessBaseReq {

    private String returnNoteNo;
    private String returnNoteDt;
    private String processId;
    private String issueNoteNo;
    private String issueNoteDt;
    private String type;
    private String regionalCenterCd;
    private String regionalCenterName;
    private String address;
    private String zipcode;
    private String consumerName;
    private String contactNo;
    private String termsCondition;
    private String note;
    private List<ItemDetailsForm> items;
    private String userId;
    @JsonIgnore
    private Date returnNoteDtDate;
    @JsonIgnore
    private Date issueNoteDtDate;

    @JsonIgnore
    private Long orgId;

    public String getReturnNoteNo() {
        return returnNoteNo;
    }

    public void setReturnNoteNo(String returnNoteNo) {
        this.returnNoteNo = returnNoteNo;
    }

    public String getReturnNoteDt() {
        return returnNoteDt;
    }

    public void setReturnNoteDt(String returnNoteDt) {
        this.returnNoteDt = returnNoteDt;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
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

    public String getRegionalCenterCd() {
        return regionalCenterCd;
    }

    public void setRegionalCenterCd(String regionalCenterCd) {
        this.regionalCenterCd = regionalCenterCd;
    }

    public String getRegionalCenterName() {
        return regionalCenterName;
    }

    public void setRegionalCenterName(String regionalCenterName) {
        this.regionalCenterName = regionalCenterName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public Date getReturnNoteDtDate() {
        return returnNoteDtDate;
    }

    public void setReturnNoteDtDate(Date returnNoteDtDate) {
        this.returnNoteDtDate = returnNoteDtDate;
    }

    public Date getIssueNoteDtDate() {
        return issueNoteDtDate;
    }

    public void setIssueNoteDtDate(Date issueNoteDtDate) {
        this.issueNoteDtDate = issueNoteDtDate;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
