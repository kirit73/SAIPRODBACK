package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "issue_note_dtls")
public class IssueNoteDetails extends ProcessBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_note_id")
    private Long id;
    @Column(name = "process_id")
    private Long processId;
    @Column(name = "demand_note_no")
    private String demandNoteNo;

    @Column(name = "issue_note_no")
    private String issueNoteNo;

    @Column(name = "issue_note_date")
    private Date issueNoteDt;
    @Column(name = "type")
    private String type;
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
    @Column(name = "terms_condition")
    private String termsCondition;
    @Column(name = "note")
    private String note;
    @Column(name = "DEMAND_NOTE_DT")
    private Date demandNoteDt;

    @Column(name = "inter_rd_demand_note_no")
    private String interRdDemandNote;

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

    public Date getIssueNoteDt() {
        return issueNoteDt;
    }

    public void setIssueNoteDt(Date issueNoteDt) {
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

    public Date getDemandNoteDt() {
        return demandNoteDt;
    }

    public void setDemandNoteDt(Date demandNoteDt) {
        this.demandNoteDt = demandNoteDt;
    }

    public String getInterRdDemandNote() {
        return interRdDemandNote;
    }

    public void setInterRdDemandNote(String interRdDemandNote) {
        this.interRdDemandNote = interRdDemandNote;
    }
}
