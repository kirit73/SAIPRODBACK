package com.sai.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class GeneralParameter extends BaseEntity {

    @EmbeddedId
    private GeneralParameterId id;

    @Column(name = "PARAM_DESC")
    private String paramDesc;
    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORG_ID")
    private Long orgId;

    public GeneralParameterId getId() {
        return id;
    }

    public void setId(GeneralParameterId id) {
        this.id = id;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
