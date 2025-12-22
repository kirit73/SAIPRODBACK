package com.sai.inventory.domain.master.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SaveDiscReq {
    @JsonIgnore
    private Long orgId;
    @JsonIgnore
    private String createUser;
    private String category;
    private String subCategory;
    private String type;
    private String disciplines;
    private String disciplinesName;

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    public String getDisciplinesName() {
        return disciplinesName;
    }

    public void setDisciplinesName(String disciplinesName) {
        this.disciplinesName = disciplinesName;
    }
}
