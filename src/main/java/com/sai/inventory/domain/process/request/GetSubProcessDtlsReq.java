package com.sai.inventory.domain.process.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GetSubProcessDtlsReq {

    private Long processId;
    private String processStage;

    private boolean rejectProcess = false;
    private String noteType;  // "ACCEPTANCE" or "REJECTION"

    @JsonIgnore
    private Long orgId;


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

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public boolean isRejectProcess() {
        return rejectProcess;
    }

    public void setRejectProcess(boolean rejectProcess) {
        this.rejectProcess = rejectProcess;
    }
    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
}
