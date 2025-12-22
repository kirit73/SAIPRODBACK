package com.sai.inventory.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "locator_master")
public class LocatorMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATOR_MASTER_ID", nullable = false)
    private Long id;
    @Column(name = "LOCATOR_CD")
    private String locatorCd;
    @Column(name = "LOCATOR_DESC")
    private String locatorDesc;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "CAPACITY")
    private String capacity;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "OWNERSHIP")
    private String ownership;
    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
