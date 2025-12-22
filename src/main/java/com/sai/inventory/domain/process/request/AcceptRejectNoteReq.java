package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.List;

public class AcceptRejectNoteReq extends ProcessBaseReq{

    private Long processId;
    private String type;
    @JsonIgnore
    private String typeOfNote;
    private String inspectionRptNo;
    private String acptRejNoteNo;
    private String acptRejNodeDT;
    private String dateOfDelivery;
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
    private String note;
    private String conditionOfGoods;
    private List<ItemDetailsForm> items;
    private String userId;
    private String supplierName;
    private String supplierCd;
    private String address;
    private String noaDate;
    @JsonIgnore
    private Date dateOfDeliveryDt;
    @JsonIgnore
    private Date noaDateDt;

    @JsonIgnore
    private Date acptRejNodeDTDate;

    @JsonIgnore
    private Long orgId;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeOfNote() {
        return typeOfNote;
    }

    public void setTypeOfNote(String typeOfNote) {
        this.typeOfNote = typeOfNote;
    }

    public String getInspectionRptNo() {
        return inspectionRptNo;
    }

    public void setInspectionRptNo(String inspectionRptNo) {
        this.inspectionRptNo = inspectionRptNo;
    }

    public String getAcptRejNoteNo() {
        return acptRejNoteNo;
    }

    public void setAcptRejNoteNo(String acptRejNoteNo) {
        this.acptRejNoteNo = acptRejNoteNo;
    }

    public String getAcptRejNodeDT() {
        return acptRejNodeDT;
    }

    public void setAcptRejNodeDT(String acptRejNodeDT) {
        this.acptRejNodeDT = acptRejNodeDT;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getConditionOfGoods() {
        return conditionOfGoods;
    }

    public void setConditionOfGoods(String conditionOfGoods) {
        this.conditionOfGoods = conditionOfGoods;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCd() {
        return supplierCd;
    }

    public void setSupplierCd(String supplierCd) {
        this.supplierCd = supplierCd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoaDate() {
        return noaDate;
    }

    public void setNoaDate(String noaDate) {
        this.noaDate = noaDate;
    }

    public Date getDateOfDeliveryDt() {
        return dateOfDeliveryDt;
    }

    public void setDateOfDeliveryDt(Date dateOfDeliveryDt) {
        this.dateOfDeliveryDt = dateOfDeliveryDt;
    }

    public Date getNoaDateDt() {
        return noaDateDt;
    }

    public void setNoaDateDt(Date noaDateDt) {
        this.noaDateDt = noaDateDt;
    }

    public Date getAcptRejNodeDTDate() {
        return acptRejNodeDTDate;
    }

    public void setAcptRejNodeDTDate(Date acptRejNodeDTDate) {
        this.acptRejNodeDTDate = acptRejNodeDTDate;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
