package com.sai.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.CommonUtils;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "acpt_rej_note_dtls")
public class AcceptRejectDtls extends ProcessBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acpt_rej_note_id")
    private Long id;
    @Column(name = "process_id")
    private Long processId;
    @Column(name = "type")
    private String type;
    @Column(name = "type_of_note")
    private String typeOfNote;
    @Column(name = "inspection_rpt_no")
    private String inspectionRptNo;
    @Column(name = "acpt_rej_note_no")
    private String acptRejNoteNo;
    @Column(name = "acpt_rej_note_DT")
    @JsonIgnore
    private Date acptRejNoteDT;
    @Column(name = "date_of_Delivery")
    @JsonIgnore
    private Date dateOfDelivery;
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
    @Column(name = "note")
    private String note;
    @Column(name = "noa_dt")
    private Date noaDate;

    @Column(name = "noa")
    private String noa;
    @Column(name = "condition_of_goods")
    private String conditionOfGoods;

    @Transient
    @JsonProperty("acptRejNoteDT")
    private String acptRejNoteDTNew;

    @Transient
    @JsonProperty("dateOfDelivery")
    private Date dateOfDeliveryNew;

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

    public Date getAcptRejNoteDT() {
        return acptRejNoteDT;
    }

    public void setAcptRejNoteDT(Date acptRejNoteDT) {
        this.acptRejNoteDT = acptRejNoteDT;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
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

    public String getAcptRejNoteDTNew() {
        return CommonUtils.getDate(getAcptRejNoteDT(), ApplicationConstant.DATE_FORMAT);
    }

    public void setAcptRejNoteDTNew(String acptRejNoteDTNew) {
        this.acptRejNoteDTNew = acptRejNoteDTNew;
    }

    public Date getDateOfDeliveryNew() {
        return dateOfDeliveryNew;
    }

    public void setDateOfDeliveryNew(Date dateOfDeliveryNew) {
        this.dateOfDeliveryNew = dateOfDeliveryNew;
    }
}
