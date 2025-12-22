package com.sai.inventory.domain.process.response;

import com.sai.inventory.domain.ProcessItemMapDomain;

import java.util.List;

public class GetSubProcessDtlsRes {

    private Long processId;
    private String processStage;
    private ProcessBaseResponse processData;
    private List<ProcessItemMapDomain> itemList;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public ProcessBaseResponse getProcessData() {
        return processData;
    }

    public void setProcessData(ProcessBaseResponse processData) {
        this.processData = processData;
    }

    public List<ProcessItemMapDomain> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProcessItemMapDomain> itemList) {
        this.itemList = itemList;
    }
}
