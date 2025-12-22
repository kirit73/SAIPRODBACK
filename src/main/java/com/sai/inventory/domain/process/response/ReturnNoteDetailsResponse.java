package com.sai.inventory.domain.process.response;

public class ReturnNoteDetailsResponse extends ProcessBaseResponse {

    private Long id;

    private Long processId;

    private String returnNoteNo;


    private String returnNoteDt;

    private String issueNoteNo;

    private String issueNoteDt;

    private String regionalCenterCd;

    private String regionalCenterName;

    private String address;

    private String zipcode;

    private String consumerName;

    private String contactNo;

    private String termsCondition;

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

    public String getReturnNoteDt() {
        return returnNoteDt;
    }

    public void setReturnNoteDt(String returnNoteDt) {
        this.returnNoteDt = returnNoteDt;
    }

    public String getIssueNoteNo() {
        return issueNoteNo;
    }

    public void setIssueNoteNo(String issueNoteNo) {
        this.issueNoteNo = issueNoteNo;
    }

    public String getIssueNoteDt() {
        return issueNoteDt;
    }

    public void setIssueNoteDt(String issueNoteDt) {
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
