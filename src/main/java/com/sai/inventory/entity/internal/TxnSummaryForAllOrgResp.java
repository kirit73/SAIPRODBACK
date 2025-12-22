package com.sai.inventory.entity.internal;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TxnSummaryForAllOrgResp {

    private Long orgId;
    private String orgName;
    private List<TxnSummaryResp> respList;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<TxnSummaryResp> getRespList() {
        return respList;
    }

    public void setRespList(List<TxnSummaryResp> respList) {
        this.respList = respList;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
