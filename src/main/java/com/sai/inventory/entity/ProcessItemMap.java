package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "process_Item_map")
public class ProcessItemMap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_Item_map_id")
    private Long id;

    @Column(name = "item_ID")
    private Long itemId;
    @Column(name = "s_no")
    private Long sNo;
    @Column(name = "item_cd")
    private String itemCode;
    @Column(name = "item_desc")
    private String itemDesc;
    @Column(name = "uom")
    private String uom;
    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "inspected_quantity")
    private Double inspectedQuantity;

    @Column(name = "accepted_quantity")
    private Double acceptedQuantity;

    @Column(name = "rejected_quantity")
    private Double rejectedQuantity;
    @Column(name = "required_days")
    private int requiredDays;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "process_id")
    private Long processId;

    @Column(name = "process_type")
    private String processType;

    @Column(name = "process_stage")
    private String processStage;

    @Column(name = "condition_of_goods")
    private String conditionOfGoods;

    @Column(name = "budget_head_procurement")
    private String budgetHeadProcurement;

    @Column(name = "locator_id")
    private String locatorId;

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @Column(name = "unique_id")
    private Long uniqueId;

    @Transient
    private String uomDesc;
    @Transient
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

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUomDesc() {
        return uomDesc;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public String getLocatorDesc() {
        return locatorDesc;
    }

    public void setLocatorDesc(String locatorDesc) {
        this.locatorDesc = locatorDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessItemMap that = (ProcessItemMap) o;
        return Objects.equals(itemCode, that.itemCode) && Objects.equals(locatorId, that.locatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, locatorId);
    }
}
