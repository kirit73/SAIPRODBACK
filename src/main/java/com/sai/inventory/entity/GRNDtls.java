package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "grn_dtls")
public class GRNDtls extends ProcessBaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_id")
    private Long id;

    @Column(name = "process_id")
    private Long processId;
    @Column(name = "type")
    private String type;
    @Column(name = "grn_date")
    private Date grnDate;
    @Column(name = "grn_No")
    private String grnNo;
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
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "noa_No")
    private String noaNo;
    @Column(name = "noa_Date")
    private Date noaDate;
    @Column(name = "date_of_Delivery")
    private Date dateOfDelivery;
    @Column(name = "acceptance_note_no")
    private String acceptanceNoteNo;
    @Column(name = "return_voucher")
    private String returnVoucher;
    @Column(name = "challan_No")
    private String challanNo;
    @Column(name = "supplier_Code")
    private String supplierCode;
    @Column(name = "supplier_Name")
    private String supplierName;
    @Column(name = "note_Type")
    private String noteType;
    @Column(name = "condition_of_goods")
    private String conditionOfGoods;
    @Column(name = "note")
    private String note;

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

    public Date getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(Date grnDate) {
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

    public Date getNoaDate() {
        return noaDate;
    }

    public void setNoaDate(Date noaDate) {
        this.noaDate = noaDate;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
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
}
