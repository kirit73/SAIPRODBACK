package com.sai.inventory.domain.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestByIdReq {

    @JsonProperty("locationId")
    Long requestId;

    String userId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
