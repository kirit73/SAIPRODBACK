package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;

import java.util.Date;
import java.util.List;

public class GRNReq extends ProcessBaseReq{

    private String processId;
    private String type;
    private String grnDate;
    private String grnNo;
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
    private String acceptanceNoteNo;
    private String returnVoucher;
    private String challanNo;
    private String supplierCode;
    private String supplierName;
    private String noteType;
    private List<ItemDetailsForm> items;
    private String userId;
    private String conditionOfGoods;
    private String note;
    @JsonIgnore
    private Date grnDateDt;
    @JsonIgnore
    private Date dateOfDeliveryDt;

    @JsonIgnore
    private Date noaDateDt;

    @JsonIgnore
    private Long orgId;
    @JsonIgnore
    private String correctionProcessFlag = "N";

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

    public String getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(String grnDate) {
        this.grnDate = grnDate;
    }

    public String getGrnNo() {
        return grnNo;
    }

    public void setGrnNo(String grnNo) {
        this.grnNo = grnNo;
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

    public String getAcceptanceNoteNo() {
        return acceptanceNoteNo;
    }

    public void setAcceptanceNoteNo(String acceptanceNoteNo) {
        this.acceptanceNoteNo = acceptanceNoteNo;
    }

    public String getReturnVoucher() {
        return returnVoucher;
    }

    public void setReturnVoucher(String returnVoucher) {
        this.returnVoucher = returnVoucher;
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

    public String getConditionOfGoods() {
        return conditionOfGoods;
    }

    public void setConditionOfGoods(String conditionOfGoods) {
        this.conditionOfGoods = conditionOfGoods;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getGrnDateDt() {
        return grnDateDt;
    }

    public void setGrnDateDt(Date grnDateDt) {
        this.grnDateDt = grnDateDt;
    }

    public Date getDateOfDeliveryDt() {
        return dateOfDeliveryDt;
    }

    public void setDateOfDeliveryDt(Date dateOfDeliveryDt) {
        this.dateOfDeliveryDt = dateOfDeliveryDt;
    }

    public void setNoaDateDt(Date noaDateDt) {
        this.noaDateDt = noaDateDt;
    }

    public Date getNoaDateDt() {
        return noaDateDt;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setCorrectionProcessFlag(String correctionProcessFlag) {
        this.correctionProcessFlag = correctionProcessFlag;
    }

    public String getCorrectionProcessFlag() {
        return correctionProcessFlag;
    }
}
