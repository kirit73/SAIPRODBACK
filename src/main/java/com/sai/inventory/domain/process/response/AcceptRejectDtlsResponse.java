package com.sai.inventory.domain.process.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sai.inventory.entity.ProcessBaseEntity;
import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.CommonUtils;
import jakarta.persistence.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptRejectDtlsResponse extends ProcessBaseResponse {

    private Long id;
    private Long processId;
    private String type;
    private String typeOfNote;
    private String inspectionRptNo;
    private String acptRejNoteNo;

    private String acptRejNoteDT;
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
    private String supplierName;
    private String supplierCd;
    private String address;
    private String contactNo;
    private String note;
    private Date noaDate;

    private String noaDateFormat;

    private String noa;
    private String conditionOfGoods;
    private String modeOfDelivery;
    private String challanNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAcptRejNoteDT() {
        return acptRejNoteDT;
    }

    public void setAcptRejNoteDT(String acptRejNoteDT) {
        this.acptRejNoteDT = acptRejNoteDT;
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

    public Date getNoaDate() {
        return noaDate;
    }

    public void setNoaDate(Date noaDate) {
        this.noaDate = noaDate;
    }

    public String getNoa() {
        return noa;
    }

    public void setNoa(String noa) {
        this.noa = noa;
    }

    public String getConditionOfGoods() {
        return conditionOfGoods;
    }

    public void setConditionOfGoods(String conditionOfGoods) {
        this.conditionOfGoods = conditionOfGoods;
    }

    public String getNoaDateFormat() {
        return noaDateFormat;
    }

    public void setNoaDateFormat(String noaDateFormat) {
        this.noaDateFormat = noaDateFormat;
    }

    public void setModeOfDelivery(String modeOfDelivery) {
        this.modeOfDelivery = modeOfDelivery;
    }

    public String getModeOfDelivery() {
        return modeOfDelivery;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public String getChallanNo() {
        return challanNo;
    }
}
