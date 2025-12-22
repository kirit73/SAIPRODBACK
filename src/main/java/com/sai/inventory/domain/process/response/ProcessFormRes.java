package com.sai.inventory.domain.process.response;

public class ProcessFormRes {

    private String processId;
    private String processType;

    private Long subProcessId;

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public Long getSubProcessId() {
        return subProcessId;
    }

    public void setSubProcessId(Long subProcessId) {
        this.subProcessId = subProcessId;
    }
}
