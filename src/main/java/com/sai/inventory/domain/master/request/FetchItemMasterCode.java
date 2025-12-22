package com.sai.inventory.domain.master.request;

public class FetchItemMasterCode {

    private String itemCode;
    private String userId;

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
}
