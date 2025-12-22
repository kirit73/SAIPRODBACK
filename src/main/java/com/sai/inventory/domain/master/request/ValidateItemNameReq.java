package com.sai.inventory.domain.master.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ValidateItemNameReq {

    private String itemName;
    @JsonIgnore
    private Long orgId;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
