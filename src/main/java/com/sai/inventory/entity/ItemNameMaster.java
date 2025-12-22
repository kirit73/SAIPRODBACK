package com.sai.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "items_name_mst")
public class ItemNameMaster {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_row_id")
    Long id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_name_code")
    private String itemNameCode;
    @Column(name = "descipline_code")
    private String desciplineCode;
    @Column(name = "category_cd")
    private String categoryCode;
    @Column(name = "sub_category_code")
    private String subCategoryCode;
    @Column(name = "type_code")
    private String typeCode;
    @JsonIgnore
    @Column(name = "create_user")
    private String createUser;
    @JsonIgnore
    @Column(name = "create_dt")
    private Date createDt;
    @Column(name = "org_id")
    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameCode() {
        return itemNameCode;
    }

    public void setItemNameCode(String itemNameCode) {
        this.itemNameCode = itemNameCode;
    }

    public String getDesciplineCode() {
        return desciplineCode;
    }

    public void setDesciplineCode(String desciplineCode) {
        this.desciplineCode = desciplineCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }
}
