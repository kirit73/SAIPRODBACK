package com.sai.inventory.domain.master.response;

public class CommonIdResponse {

    public CommonIdResponse(){}

    public CommonIdResponse(Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    private Long id;
    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
