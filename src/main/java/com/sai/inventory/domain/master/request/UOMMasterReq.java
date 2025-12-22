package com.sai.inventory.domain.master.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class UOMMasterReq {

    private Long uomId;
    private String uomCode;
    private String uomName;
    private String uomDesc;
    private String className;
    private String baseUom;
    private String endDate;
    private String userId;

    @JsonIgnore
    private Date endDateInDate;

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getUomDesc() {
        return uomDesc;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBaseUom() {
        return baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getEndDateInDate() {
        return endDateInDate;
    }

    public void setEndDateInDate(Date endDateInDate) {
        this.endDateInDate = endDateInDate;
    }
}
