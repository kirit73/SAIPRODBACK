package com.sai.inventory.domain.process.response;

public class AllTxnDtlsRes {

    private Long processId;

    private AllTxnDtlsInternalRes inspectionRptData;
    private AllTxnDtlsInternalRes rejectData;
    private AllTxnDtlsInternalRes acceptData;
    private AllTxnDtlsInternalRes GRNData;
    private AllTxnDtlsInternalRes RNData;
    private AllTxnDtlsInternalRes IGPData;
    private AllTxnDtlsInternalRes OGPData;
    private AllTxnDtlsInternalRes ISNData;
    private AllTxnDtlsInternalRes inspectionNewRptData;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public AllTxnDtlsInternalRes getInspectionRptData() {
        return inspectionRptData;
    }

    public void setInspectionRptData(AllTxnDtlsInternalRes inspectionRptData) {
        this.inspectionRptData = inspectionRptData;
    }

    public AllTxnDtlsInternalRes getRejectData() {
        return rejectData;
    }

    public void setRejectData(AllTxnDtlsInternalRes rejectData) {
        this.rejectData = rejectData;
    }

    public AllTxnDtlsInternalRes getAcceptData() {
        return acceptData;
    }

    public void setAcceptData(AllTxnDtlsInternalRes acceptData) {
        this.acceptData = acceptData;
    }

    public AllTxnDtlsInternalRes getGRNData() {
        return GRNData;
    }

    public void setGRNData(AllTxnDtlsInternalRes GRNData) {
        this.GRNData = GRNData;
    }

    public AllTxnDtlsInternalRes getRNData() {
        return RNData;
    }

    public void setRNData(AllTxnDtlsInternalRes RNData) {
        this.RNData = RNData;
    }

    public AllTxnDtlsInternalRes getIGPData() {
        return IGPData;
    }

    public void setIGPData(AllTxnDtlsInternalRes IGPData) {
        this.IGPData = IGPData;
    }

    public AllTxnDtlsInternalRes getOGPData() {
        return OGPData;
    }

    public void setOGPData(AllTxnDtlsInternalRes OGPData) {
        this.OGPData = OGPData;
    }

    public AllTxnDtlsInternalRes getISNData() {
        return ISNData;
    }

    public void setISNData(AllTxnDtlsInternalRes ISNData) {
        this.ISNData = ISNData;
    }

    public void setInspectionNewRptData(AllTxnDtlsInternalRes inspectionNewRptData) {
        this.inspectionNewRptData = inspectionNewRptData;
    }

    public AllTxnDtlsInternalRes getInspectionNewRptData() {
        return inspectionNewRptData;
    }
}
