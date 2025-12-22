package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.request.ItemDetailsForm;

import java.util.Date;
import java.util.List;

public class InspectionReportReq extends ProcessBaseReq{

    private Long processId;
    private String type;
    private String typeOfInspection;
    private String inspectionRptNo;
    @JsonIgnore
    private Date inspectionRptDt;
    private String inspectionRptDate;
    private String invoiceNo;
    private String inwardGatePass;
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
    @JsonIgnore
    private Date dateOfDelivery;
    private String dateOfDeliveryDate;
    @JsonIgnore
    private Date dateOfInspection;
    private String dateOfInspectionDate;
    private String note;
    private String conditionOfGoods;
    private String userId;

    private List<ItemDetailsForm> items;

    @JsonIgnore
    private Long orgId;

    public List<ItemDetailsForm> getItems() {
        return items;
    }

    public void setItems(List<ItemDetailsForm> items) {
        this.items = items;
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

    public String getTypeOfInspection() {
        return typeOfInspection;
    }

    public void setTypeOfInspection(String typeOfInspection) {
        this.typeOfInspection = typeOfInspection;
    }

    public String getInspectionRptNo() {
        return inspectionRptNo;
    }

    public void setInspectionRptNo(String inspectionRptNo) {
        this.inspectionRptNo = inspectionRptNo;
    }

    public Date getInspectionRptDt() {
        return inspectionRptDt;
    }

    public void setInspectionRptDt(Date inspectionRptDt) {
        this.inspectionRptDt = inspectionRptDt;
    }

    public String getInspectionRptDate() {
        return inspectionRptDate;
    }

    public void setInspectionRptDate(String inspectionRptDate) {
        this.inspectionRptDate = inspectionRptDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInwardGatePass() {
        return inwardGatePass;
    }

    public void setInwardGatePass(String inwardGatePass) {
        this.inwardGatePass = inwardGatePass;
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

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getDateOfDeliveryDate() {
        return dateOfDeliveryDate;
    }

    public void setDateOfDeliveryDate(String dateOfDeliveryDate) {
        this.dateOfDeliveryDate = dateOfDeliveryDate;
    }

    public Date getDateOfInspection() {
        return dateOfInspection;
    }

    public void setDateOfInspection(Date dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
    }

    public String getDateOfInspectionDate() {
        return dateOfInspectionDate;
    }

    public void setDateOfInspectionDate(String dateOfInspectionDate) {
        this.dateOfInspectionDate = dateOfInspectionDate;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
