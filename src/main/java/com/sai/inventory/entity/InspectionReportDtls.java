package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inspection_rpt_dtls")
public class InspectionReportDtls extends ProcessBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inspection_rpt_id")
    private Long id;
    @Column(name = "process_id")
    private Long processId;
    @Column(name = "type")
    private String type;
    @Column(name = "type_of_inspection")
    private String typeOfInspection;
    @Column(name = "inspection_rpt_no")
    private String inspectionRptNo;

    @Column(name = "inspection_rpt_Dt")
    private Date inspectionRptDt;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "inward_gate_pass")
    private String inwardGatePass;
    @Column(name = "ce_regional_center_cd")
    private String ceRegionalCenterCd;
    @Column(name = "ce_regional_center_name")
    private String ceRegionalCenterName;
    @Column(name = "ce_address")
    private String ceAddress;
    @Column(name = "ce_zipcode")
    private String ceZipcode;
    @Column(name = "cr_regional_center_cd")
    private String crRegionalCenterCd;
    @Column(name = "cr_regional_center_name")
    private String crRegionalCenterName;
    @Column(name = "cr_address")
    private String crAddress;
    @Column(name = "cr_zipcode")
    private String crZipcode;
    @Column(name = "consumer_name")
    private String consumerName;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "supplier_cd")
    private String supplierCd;
    @Column(name = "address")
    private String address;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "date_of_Delivery")
    private Date dateOfDelivery;

    @Column(name = "date_of_inspection")
    private Date dateOfInspection;
    @Column(name = "note")
    private String note;
    @Column(name = "condition_of_goods")
    private String conditionOfGoods;

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

    public Date getDateOfInspection() {
        return dateOfInspection;
    }

    public void setDateOfInspection(Date dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
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
}
