package com.sai.inventory.controller;

import com.sai.inventory.domain.process.request.*;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.service.ProcessService;
import com.sai.inventory.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/saveIssueNote")
    public ResponseEntity saveIssueNote(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody IssueNoteFormReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        req.setCorrectionProcessFlag("N");
        return ResponseBuilder.getSuccessResponse(processService.saveIssueNote(req));
    }

    @GetMapping("/getAllProcess")
    public ResponseEntity getALlProcess(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(processService.getAllProcess(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth))));
    }

    @PostMapping("/saveOutwardGatePass")
    public ResponseEntity saveOutwardGatePass(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GatePassReq req) {
        processService.validateUserType(auth);
        req.setGatePassType(GatePassTypeEnum.OUT.name());
        req.setCurrentStageInDB(ProcessStageEnum.ISN.name());
        req.setNextStageUpdate(ProcessStageEnum.OGP.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveGatePassDtls(req));
    }

    @PostMapping("/saveInwardGatePass")
    public ResponseEntity saveInwardGatePass(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GatePassReq req) {
        processService.validateUserType(auth);
        req.setGatePassType(GatePassTypeEnum.IN.name());
        req.setCurrentStageInDB(ProcessStageEnum.OGP.name());
        req.setNextStageUpdate(ProcessStageEnum.IGP.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveGatePassDtls(req));
    }

    @PostMapping("/saveReturnNote")
    public ResponseEntity saveReturnNote(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody ReturnNoteFormReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveReturnNote(req));
    }

    @PostMapping("/saveGRN")
    public ResponseEntity saveGRN(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GRNReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveGRN(req));
    }

    @PostMapping("/getSubProcessDtls")
    public ResponseEntity getSubProcessDtls(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GetSubProcessDtlsReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.getSubProcessDtls(req, true, false));
    }

    @PostMapping("/saveAcceptanceNote")
    public ResponseEntity saveAcceptanceNote(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody AcceptRejectNoteReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        req.setTypeOfNote(NoteTypeEnum.ACCEPTANCE.name());
        return ResponseBuilder.getSuccessResponse(processService.saveAcceptRejectNoteReq(req));
    }

    @PostMapping("/saveRejectionNote")
    public ResponseEntity saveRejectionNote(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody AcceptRejectNoteReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        req.setTypeOfNote(NoteTypeEnum.REJECTION.name());
        return ResponseBuilder.getSuccessResponse(processService.saveAcceptRejectNoteReq(req));
    }

    @PostMapping("/saveInspectionReport")
    public ResponseEntity saveInspectionReport(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody InspectionReportReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveInspectionReportReq(req));
    }

    @PostMapping("/saveNewInspectionReport")
    public ResponseEntity saveNewInspectionReport(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody InspectionReportReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.saveInspectionNewReportReq(req));
    }

    @PostMapping("/doCorrectionProcess")
    public ResponseEntity doCorrectionProcess(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CorrectionProcessReq req) {
        processService.validateUserType(auth);
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(processService.doCorrectionProcess(req));
    }
}
