package com.sai.inventory.domain.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeptRequestByIdReq {

    @JsonProperty("departmentId")
    Long departmentId;

    String userId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


