package com.sai.inventory.domain.master.response;

public class LocationMasterResp {

    public LocationMasterResp(Long locationMasterId) {
        this.locationMasterId = locationMasterId;
    }

    private Long locationMasterId;

    public Long getLocationMasterId() {
        return locationMasterId;
    }

    public void setLocationMasterId(Long locationMasterId) {
        this.locationMasterId = locationMasterId;
    }
}
