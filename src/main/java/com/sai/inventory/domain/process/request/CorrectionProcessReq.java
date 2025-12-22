package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CorrectionProcessReq {

    private IssueNoteFormReq issueNoteDtls;
    private GRNReq grnDtls;
    private String userId;
    @JsonIgnore
    private Long orgId;

    public IssueNoteFormReq getIssueNoteDtls() {
        return issueNoteDtls;
    }

    public void setIssueNoteDtls(IssueNoteFormReq issueNoteDtls) {
        this.issueNoteDtls = issueNoteDtls;
    }

    public GRNReq getGrnDtls() {
        return grnDtls;
    }

    public void setGrnDtls(GRNReq grnDtls) {
        this.grnDtls = grnDtls;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
