package com.sai.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_master")
public class OrganizationMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORGANIZATION_ID", nullable = false)
    private Long id;
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Column(name = "PARENT_ORGANIZATION_ID")
    private Long parentOrgId;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "LOCATION_ADDR")
    private String locationAddr;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GSTIN")
    private String gstin;
    @Column(name = "STATUS")
    private String status;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Long parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocationAddr() {
        return locationAddr;
    }

    public void setLocationAddr(String locationAddr) {
        this.locationAddr = locationAddr;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
