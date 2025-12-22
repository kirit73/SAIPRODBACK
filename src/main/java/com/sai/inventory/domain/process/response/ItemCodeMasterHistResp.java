package com.sai.inventory.domain.process.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.Date;

@Entity
public class ItemCodeMasterHistResp {
    @Id
    @JsonIgnore
    @Column(name = "ITEM_MASTER_HIST_ID")
    private Long id;
    @Column(name = "process_id")
    private Long processId;
    @Column(name = "ITEM_MASTER_CD")
    private String itemMasterCd;
    @Column(name = "ITEM_MASTER_DESC")
    private String itemMasterDesc;
    @Column(name = "PRE_QUANTITY")
    private Double preQuantity;
    @Column(name = "POST_QUANTITY")
    private Double postQuantity;
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Column(name = "LOCATOR_ID")
    private Long locatorId;
    @Column(name = "PROCESS_STAGE")
    private String processStage;

    @JsonIgnore
    @Column(name = "TOTAL_PRE_QTY")
    private Double totalPreQuantity;

    @JsonIgnore
    @Column(name = "TOTAL_POST_QTY")
    private Double totalPostQuantity;

    @Column(name = "TXN_DT")
    private String txnDate;

    @JsonIgnore
    @Column(name = "ORG_CREATE_USER")
    private String createUser;
    @JsonIgnore
    @Column(name = "ORG_CREATE_DT")
    private Date createDateDt;

    @JsonIgnore
    @Column(name = "INIT_QTY")
    private Double initQty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getItemMasterCd() {
        return itemMasterCd;
    }

    public void setItemMasterCd(String itemMasterCd) {
        this.itemMasterCd = itemMasterCd;
    }

    public String getItemMasterDesc() {
        return itemMasterDesc;
    }

    public void setItemMasterDesc(String itemMasterDesc) {
        this.itemMasterDesc = itemMasterDesc;
    }

    public Double getPreQuantity() {
        return preQuantity;
    }

    public void setPreQuantity(Double preQuantity) {
        this.preQuantity = preQuantity;
    }

    public Double getPostQuantity() {
        return postQuantity;
    }

    public void setPostQuantity(Double postQuantity) {
        this.postQuantity = postQuantity;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public Double getTotalPreQuantity() {
        return totalPreQuantity;
    }

    public void setTotalPreQuantity(Double totalPreQuantity) {
        this.totalPreQuantity = totalPreQuantity;
    }

    public Double getTotalPostQuantity() {
        return totalPostQuantity;
    }

    public void setTotalPostQuantity(Double totalPostQuantity) {
        this.totalPostQuantity = totalPostQuantity;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDateDt() {
        return createDateDt;
    }

    public void setCreateDateDt(Date createDateDt) {
        this.createDateDt = createDateDt;
    }

    public Double getInitQty() {
        return initQty;
    }

    public void setInitQty(Double initQty) {
        this.initQty = initQty;
    }
}
