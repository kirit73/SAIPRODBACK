package com.sai.inventory.domain.master.request;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class LocatorMasterReq {
    private Long locatorMasterId;
    private String locatorCd;
    private String locatorDesc;
    private String location;
    private String capacity;
    private String type;
    private String ownership;
    private String status;
    private String userId;

    @JsonIgnore
    private Long orgId;

    public Long getLocatorMasterId() {
        return locatorMasterId;
    }

    public void setLocatorMasterId(Long locatorMasterId) {
        this.locatorMasterId = locatorMasterId;
    }

    public String getLocatorCd() {
        return locatorCd;
    }

    public void setLocatorCd(String locatorCd) {
        this.locatorCd = locatorCd;
    }

    public String getLocatorDesc() {
        return locatorDesc;
    }

    public void setLocatorDesc(String locatorDesc) {
        this.locatorDesc = locatorDesc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
