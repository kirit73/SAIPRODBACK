package com.sai.inventory.domain.master.request;

import java.math.BigDecimal;

public class ItemDetailsForm {

    private int srNo;
    private String itemCode;

    private Long itemId;
    private String itemDesc;
    private String uom;
    private double quantity;
    private int noOfDays;
    private String remarks;
    private String conditionOfGoods;
    private String budgetHeadProcurement;
    private String locatorId;
    private double inspectedQuantity;
    private double acceptedQuantity;
    private double rejectedQuantity;
    private BigDecimal unitPrice;

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public double getInspectedQuantity() {
        return inspectedQuantity;
    }

    public void setInspectedQuantity(double inspectedQuantity) {
        this.inspectedQuantity = inspectedQuantity;
    }

    public double getAcceptedQuantity() {
        return acceptedQuantity;
    }

    public void setAcceptedQuantity(double acceptedQuantity) {
        this.acceptedQuantity = acceptedQuantity;
    }

    public double getRejectedQuantity() {
        return rejectedQuantity;
    }

    public void setRejectedQuantity(double rejectedQuantity) {
        this.rejectedQuantity = rejectedQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
