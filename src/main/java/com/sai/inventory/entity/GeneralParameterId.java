package com.sai.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GeneralParameterId implements Serializable {

    @Column(name = "PARAM_VAL")
    private String paramVal;
    @Column(name = "PARAM_TYPE")
    private String paramType;

    public GeneralParameterId(){}

    public GeneralParameterId(String paramVal, String paramType) {
        this.paramType = paramType;
        this.paramVal = paramVal;
    }

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralParameterId that = (GeneralParameterId) o;
        return paramVal.equals(that.paramVal) && paramType.equals(that.paramType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paramVal, paramType);
    }
}
