package com.sai.inventory.controller;

import com.sai.inventory.domain.process.request.StockLedgerRrq;
import com.sai.inventory.domain.process.request.TxnDetailsReq;
import com.sai.inventory.domain.process.request.TxnSummaryReq;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.service.TxnsService;
import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import com.sai.inventory.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sai.inventory.domain.process.request.StockLedgerBatchReq;
import com.sai.inventory.domain.process.request.StockLedgerSubCategoryReq;

@RestController
@RequestMapping("txns")
public class TransactionsController {

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    private TxnsService txnsService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/getTxnSummary")
    public ResponseEntity getTxnSummary(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody TxnSummaryReq req) {
        try {
            logger.info("getTxnSummary called");
            System.out.println("CALLED RE CALLED");
            if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
                logger.info("getTxnSummary super admin");
                if (CommonUtils.isBlank(req.getOrgId())) {
                    throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
                }
            } else {
                logger.info("getTxnSummary not a super admin");
                req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
            }
            logger.info("getTxnSummary success");
            return ResponseBuilder.getSuccessResponse(txnsService.getTxnSummary(req));
        } catch (Exception e) {
            logger.error("getTxnSummary exception : " + e.getMessage());
            return ResponseBuilder.getErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/getTxnDtls")
    public ResponseEntity getTxnsDtls(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody TxnDetailsReq req) {
        if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }
        return ResponseBuilder.getSuccessResponse(txnsService.getTxnsDtlsV2(req));
    }

    @PostMapping("/getStockLedger")
    public ResponseEntity getStockLedger(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody StockLedgerRrq req) {
        if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }
        return ResponseBuilder.getSuccessResponse(txnsService.getStockLedger(req));
    }

    @PostMapping("/getTxnSummaryForAllOrg")
    public ResponseEntity getTxnSummaryForAllOrg(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody TxnSummaryReq req) {
        /*if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            if (CommonUtils.isBlank(req.getOrgId())) {
                //throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }
        } else {
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }*/
        return ResponseBuilder.getSuccessResponse(txnsService.getTxnSummaryForAllOrg(req));
    }
    @PostMapping("/getStockLedgerBatch")
    public ResponseEntity getStockLedgerBatch(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, 
                                              @RequestBody StockLedgerBatchReq req) {
        try {
            logger.info("getStockLedgerBatch called with {} items", req.getItemCodes() != null ? req.getItemCodes().size() : 0);
            
            if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
                if (CommonUtils.isBlank(req.getOrgId())) {
                    throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
                }
            } else {
                req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
            }
            
            return ResponseBuilder.getSuccessResponse(txnsService.getStockLedgerBatch(req));
        } catch (Exception e) {
            logger.error("getStockLedgerBatch exception: " + e.getMessage(), e);
            return ResponseBuilder.getErrorResponse(e.getMessage());
        }
    }

    /**
     * NEW ENDPOINT: Get stock ledger by subcategory
     * Fetches all items in a subcategory with their transactions in one request
     */
    @PostMapping("/getStockLedgerBySubCategory")
    public ResponseEntity getStockLedgerBySubCategory(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                       @RequestBody StockLedgerSubCategoryReq req) {
        try {
            logger.info("getStockLedgerBySubCategory called for subcategory: {}", req.getSubCategory());
            
            if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
                if (CommonUtils.isBlank(req.getOrgId())) {
                    throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
                }
            } else {
                req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
            }
            
            return ResponseBuilder.getSuccessResponse(txnsService.getStockLedgerBySubCategory(req));
        } catch (Exception e) {
            logger.error("getStockLedgerBySubCategory exception: " + e.getMessage(), e);
            return ResponseBuilder.getErrorResponse(e.getMessage());
        }
    }
}