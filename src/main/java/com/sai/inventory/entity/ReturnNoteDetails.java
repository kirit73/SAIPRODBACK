package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "return_note_dtls")
public class ReturnNoteDetails extends ProcessBaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_note_id")
    private Long id;
    @Column(name = "process_id")
    private Long processId;
    @Column(name = "return_note_no")
    private String returnNoteNo;
    @Column(name = "return_NOTE_Date")
    private Date returnNoteDt;
    @Column(name = "issue_note_no")
    private String issueNoteNo;
    @Column(name = "issue_note_date")
    private Date issueNoteDt;
    @Column(name = "regional_center_cd")
    private String regionalCenterCd;
    @Column(name = "regional_center_name")
    private String regionalCenterName;
    @Column(name = "address")
    private String address;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "consumer_name")
    private String consumerName;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "terms_condition")
    private String termsCondition;
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

    public String getReturnNoteNo() {
        return returnNoteNo;
    }

    public void setReturnNoteNo(String returnNoteNo) {
        this.returnNoteNo = returnNoteNo;
    }

    public Date getReturnNoteDt() {
        return returnNoteDt;
    }

    public void setReturnNoteDt(Date returnNoteDt) {
        this.returnNoteDt = returnNoteDt;
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
}
