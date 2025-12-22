package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.List;

public class GatePassReq extends ProcessBaseReq {

    private String processId;
    private String type;
    private String gatePassDate;
    private String gatePassNo;
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
    private String noaNo;
    private String noaDate;
    private String dateOfDelivery;
    private String modeOfDelivery;
    private String challanNo;
    private String supplierCode;
    private String supplierName;
    private String noteType;
    private String rejectionNoteNo;
    private List<ItemDetailsForm> items;
    private String userId;

    private String termsCondition;
    private String note;

    private String processType;

    @JsonIgnore
    private Date ogpDateDt;
    @JsonIgnore
    private Date noaDateDt;
    @JsonIgnore
    private Date dateOfDeliveryDt;

    @JsonIgnore
    private Date gatePassDateDt;

    @JsonIgnore
    private String gatePassType;

    @JsonIgnore
    private String currentStageInDB;

    @JsonIgnore
    private String nextStageUpdate;

    @JsonIgnore
    private Long orgId;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGatePassDate() {
        return gatePassDate;
    }

    public void setGatePassDate(String gatePassDate) {
        this.gatePassDate = gatePassDate;
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

    public String getGatePassNo() {
        return gatePassNo;
    }

    public void setGatePassNo(String gatePassNo) {
        this.gatePassNo = gatePassNo;
    }

    public String getNoaNo() {
        return noaNo;
    }

    public void setNoaNo(String noaNo) {
        this.noaNo = noaNo;
    }

    public String getNoaDate() {
        return noaDate;
    }

    public void setNoaDate(String noaDate) {
        this.noaDate = noaDate;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getModeOfDelivery() {
        return modeOfDelivery;
    }

    public void setModeOfDelivery(String modeOfDelivery) {
        this.modeOfDelivery = modeOfDelivery;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public String getRejectionNoteNo() {
        return rejectionNoteNo;
    }

    public void setRejectionNoteNo(String rejectionNoteNo) {
        this.rejectionNoteNo = rejectionNoteNo;
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

    public Date getOgpDateDt() {
        return ogpDateDt;
    }

    public void setOgpDateDt(Date ogpDateDt) {
        this.ogpDateDt = ogpDateDt;
    }

    public Date getNoaDateDt() {
        return noaDateDt;
    }

    public void setNoaDateDt(Date noaDateDt) {
        this.noaDateDt = noaDateDt;
    }

    public Date getDateOfDeliveryDt() {
        return dateOfDeliveryDt;
    }

    public void setDateOfDeliveryDt(Date dateOfDeliveryDt) {
        this.dateOfDeliveryDt = dateOfDeliveryDt;
    }

    public Date getGatePassDateDt() {
        return gatePassDateDt;
    }

    public void setGatePassDateDt(Date gatePassDateDt) {
        this.gatePassDateDt = gatePassDateDt;
    }

    public String getGatePassType() {
        return gatePassType;
    }

    public void setGatePassType(String gatePassType) {
        this.gatePassType = gatePassType;
    }

    public String getCurrentStageInDB() {
        return currentStageInDB;
    }

    public void setCurrentStageInDB(String currentStageInDB) {
        this.currentStageInDB = currentStageInDB;
    }

    public String getNextStageUpdate() {
        return nextStageUpdate;
    }

    public void setNextStageUpdate(String nextStageUpdate) {
        this.nextStageUpdate = nextStageUpdate;
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

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
