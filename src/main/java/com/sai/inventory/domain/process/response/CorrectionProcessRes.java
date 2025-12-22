package com.sai.inventory.domain.process.response;

public class CorrectionProcessRes {


    private IssueNoteFormRes issueNoteResponse;
    private ProcessFormRes grnResponse;

    public void setIssueNoteResponse(IssueNoteFormRes issueNoteResponse) {
        this.issueNoteResponse = issueNoteResponse;
    }

    public IssueNoteFormRes getIssueNoteResponse() {
        return issueNoteResponse;
    }

    public ProcessFormRes getGrnResponse() {
        return grnResponse;
    }

    public void setGrnResponse(ProcessFormRes grnResponse) {
        this.grnResponse = grnResponse;
    }
}
