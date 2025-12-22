package com.sai.inventory.domain.login.response;

import com.sai.inventory.entity.LocationMaster;
import com.sai.inventory.entity.OrganizationMaster;
import com.sai.inventory.entity.UsersMaster;

public class LoginResponse {

    private UsersMasterResp userDetails;
    private OrganizationMaster organizationDetails;

    private LocationMaster locationDetails;

    private String token;

    public UsersMasterResp getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UsersMasterResp userDetails) {
        this.userDetails = userDetails;
    }

    public OrganizationMaster getOrganizationDetails() {
        return organizationDetails;
    }

    public void setOrganizationDetails(OrganizationMaster organizationDetails) {
        this.organizationDetails = organizationDetails;
    }

    public LocationMaster getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationMaster locationDetails) {
        this.locationDetails = locationDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
