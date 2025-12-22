package com.sai.inventory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "uom_master")
public class UOMMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UOM_ID", nullable = false)
    private Long id;

    @Column(name = "UOM_CODE")
    private String uomCode;
    @Column(name = "UOM_NAME")
    private String uomName;
    @Column(name = "UOM_DESC")
    private String uomDesc;
    @Column(name = "CLASS_NAME")
    private String className;
    @Column(name = "BASE_UOM")
    private String baseUom;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
