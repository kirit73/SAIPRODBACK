package com.sai.inventory.service;

import com.sai.inventory.domain.process.request.*;
import com.sai.inventory.domain.process.response.GetSubProcessDtlsRes;

public interface ProcessService {
    Object saveIssueNote(IssueNoteFormReq req);

    Object getAllProcess(Long aLong);

    Object saveGatePassDtls(GatePassReq req);

    Object saveReturnNote(ReturnNoteFormReq req);

    Object saveGRN(GRNReq req);

    GetSubProcessDtlsRes getSubProcessDtls(GetSubProcessDtlsReq req, boolean checkProcessId, boolean isTxnDtls);

    Object saveAcceptRejectNoteReq(AcceptRejectNoteReq req);

    Object saveInspectionReportReq(InspectionReportReq req);

    Object saveInspectionNewReportReq(InspectionReportReq req);

    void validateUserType(String auth);

    Object doCorrectionProcess(CorrectionProcessReq req);
}
