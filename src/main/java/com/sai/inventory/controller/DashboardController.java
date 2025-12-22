package com.sai.inventory.controller;

import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.dashboard.request.GetFNSCategoryReq;
import com.sai.inventory.domain.dashboard.request.IssueNoteDataReq;
import com.sai.inventory.domain.dashboard.request.PurchasingSummaryReq;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.service.DashboardService;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import com.sai.inventory.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/getFNSCategory")
    public ResponseEntity getFNSCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GetFNSCategoryReq req) {
       /* if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }*/
        return ResponseBuilder.getSuccessResponse(dashboardService.getFNSCategory(req));
    }

    @PostMapping("/getDashboardIssueNoteData")
    public ResponseEntity getIssueNoteData(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody IssueNoteDataReq req) {
       /* if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }*/
        return ResponseBuilder.getSuccessResponse(dashboardService.getDashboardIssueNoteData(req));
    }

    @PostMapping("/getTotalValueOfAllItem")
    public ResponseEntity getTotalValueOfAllItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrgIdRequest req) {
    /*    if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }*/
        return ResponseBuilder.getSuccessResponse(dashboardService.getTotalValueOfAllItem(req));
    }


    @PostMapping("/getPurchaseSummary")
    public ResponseEntity getPurchaseSummary(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody PurchasingSummaryReq req) {
       /* if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }*/
        return ResponseBuilder.getSuccessResponse(dashboardService.getPurchaseSummary(req));
    }

}
