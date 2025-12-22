package com.sai.inventory.domain.master.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OHQRequest {

    private String itemCode;
    private String itemDesc;
    private String userId;

   // @JsonIgnore
    private Long orgId;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
