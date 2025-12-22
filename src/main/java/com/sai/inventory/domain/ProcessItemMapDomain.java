package com.sai.inventory.domain;

import com.sai.inventory.entity.BaseEntity;

public class ProcessItemMapDomain extends BaseEntity {
    private Long id;


    private Long itemId;

    private Long sNo;
    private String itemCode;
    private String itemDesc;
    private String uom;
    private Double quantity;
    private Double inspectedQuantity;
    private Double acceptedQuantity;
    private Double rejectedQuantity;
    private int requiredDays;
    private String remarks;
    private Long processId;
    private String processType;
    private String processStage;
    private String conditionOfGoods;
    private String budgetHeadProcurement;
    private String locatorId;
    private Double unitPrice;
    private String uomDesc;
    private String locatorDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsNo() {
        return sNo;
    }

    public void setsNo(Long sNo) {
        this.sNo = sNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getQuantity() {
        return quantity;
    }

    public int getRequiredDays() {
        return requiredDays;
    }

    public void setRequiredDays(int requiredDays) {
        this.requiredDays = requiredDays;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public String getConditionOfGoods() {
        return conditionOfGoods;
    }

    public void setConditionOfGoods(String conditionOfGoods) {
        this.conditionOfGoods = conditionOfGoods;
    }
    public String getBudgetHeadProcurement() {
        return budgetHeadProcurement;
    }

    public void setBudgetHeadProcurement(String budgetHeadProcurement) {
        this.budgetHeadProcurement = budgetHeadProcurement;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(String locatorId) {
        this.locatorId = locatorId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getInspectedQuantity() {
        return inspectedQuantity;
    }

    public void setInspectedQuantity(Double inspectedQuantity) {
        this.inspectedQuantity = inspectedQuantity;
    }

    public Double getAcceptedQuantity() {
        return acceptedQuantity;
    }

    public void setAcceptedQuantity(Double acceptedQuantity) {
        this.acceptedQuantity = acceptedQuantity;
    }

    public Double getRejectedQuantity() {
        return rejectedQuantity;
    }

    public void setRejectedQuantity(Double rejectedQuantity) {
        this.rejectedQuantity = rejectedQuantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public String getUomDesc() {
        return uomDesc;
    }

    public void setLocatorDesc(String locatorDesc) {
        this.locatorDesc = locatorDesc;
    }

    public String getLocatorDesc() {
        return locatorDesc;
    }
}
