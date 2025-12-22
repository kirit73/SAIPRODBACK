package com.sai.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "process_details")
public class ProcessDtls extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private Long id;
    @Column(name = "process_type")
    private String processType;

    @Column(name = "process_stage")
    private String processStage;
    @Column(name = "status")
    private String status;

    @Column(name = "ORG_ID")
    private Long orgId;

    @JsonIgnore
    @Column(name = "CORRECTION_PROCESS_FLAG")
    private String correctionProcessFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getCorrectionProcessFlag() {
        return correctionProcessFlag;
    }

    public void setCorrectionProcessFlag(String correctionProcessFlag) {
        this.correctionProcessFlag = correctionProcessFlag;
    }
}
