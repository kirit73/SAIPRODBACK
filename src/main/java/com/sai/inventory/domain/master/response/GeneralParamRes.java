package com.sai.inventory.domain.master.response;

public class GeneralParamRes {

    private String paramType;
    private String paramVal;
    private String paramDesc;
    private String paramStatus;

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(String paramStatus) {
        this.paramStatus = paramStatus;
    }
}
