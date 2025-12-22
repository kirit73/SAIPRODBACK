package com.sai.inventory.service.impl;

import java.util.stream.Collectors;

import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.dao.ProcessDao;
import com.sai.inventory.domain.ProcessItemMapDomain;
import com.sai.inventory.domain.process.request.*;
import com.sai.inventory.domain.process.response.*;
import com.sai.inventory.entity.*;
import com.sai.inventory.entity.internal.TxnSummaryForAllOrgResp;
import com.sai.inventory.entity.internal.TxnSummaryResp;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.*;
import com.sai.inventory.service.TxnsService;
import com.sai.inventory.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TxnsServiceImpl implements TxnsService {

    @Autowired
    private MasterDao masterDao;
    @Autowired
    private IssueNoteDtlsRepository demandDtlsRepository;
    @Autowired
    private ProcessDtlsRepository processDtlsRepository;
    @Autowired
    private ProcessItemMapRepository processItemMapRepository;
    @Autowired
    private ItemMasterRepository itemMasterRepository;
    @Autowired
    private GatePassDtlsRepository gatePassDtlsRepository;
    @Autowired
    private ReturnNoteDtlsRepository returnNoteDtlsRepository;
    @Autowired
    private AcptRejNoteDtlsRepository acptRejNoteDtlsRepository;
    @Autowired
    private GRNDtlsRepository grnDtlsRepository;
    @Autowired
    private InspectionReportDtlsRepository inspectionReportDtlsRepository;
    @Autowired
    private InspectionNewReportDtlsRepository inspectionNewReportDtlsRepository;
    @Autowired
    private ProcessDao processDao;
    @Autowired
    private ProcessServiceImpl processService;
    @Autowired
    private OrganizationMasterRepository organizationMasterRepository;
    @Autowired
    private UOMMasterRepository uOMMasterRepository;
    @Autowired
    private LocatorMasterRepository locatorMasterRepository;
    @Autowired
    private ItemMasterHistRepository itemMasterHistRepository;

    /**
 * NEW METHOD: Get stock ledger for multiple items in a batch
 * Reduces multiple API calls by processing all items at once
 */
@Override
public List<StockLedgerRes> getStockLedgerBatch(StockLedgerBatchReq req) {
    List<StockLedgerRes> results = new ArrayList<>();
    
    if (req.getItemCodes() == null || req.getItemCodes().isEmpty()) {
        return results;
    }

    // Process each item code individually using existing getStockLedger method
    for (String itemCode : req.getItemCodes()) {
        try {
            StockLedgerRrq singleReq = new StockLedgerRrq();
            singleReq.setItemCode(itemCode);
            singleReq.setFromDate(req.getFromDate());
            singleReq.setToDate(req.getToDate());
            singleReq.setOrgId(req.getOrgId());
            
            Object ledgerObj = getStockLedger(singleReq);
            
            // Cast to StockLedgerRes and add if there are transactions
            if (ledgerObj != null && ledgerObj instanceof StockLedgerRes) {
                StockLedgerRes ledger = (StockLedgerRes) ledgerObj;
                if (ledger.getTxns() != null && !ledger.getTxns().isEmpty()) {
                    results.add(ledger);
                }
            }
        } catch (Exception e) {
            // Log error but continue with other items
            System.err.println("Error fetching stock ledger for item: " + itemCode + " - " + e.getMessage());
        }
    }
    
    return results;
}

/**
 * NEW METHOD: Get stock ledger for all items in a subcategory
 * Fetches all items in one go instead of making individual calls
 */
@Override
public List<StockLedgerRes> getStockLedgerBySubCategory(StockLedgerSubCategoryReq req) {
    List<StockLedgerRes> results = new ArrayList<>();
    
    try {
        // Get all item codes for the subcategory
        List<String> itemCodes;
        
        if (req.getIncludeAll() != null && req.getIncludeAll()) {
            // Get all items for the organization
            if (req.getOrgId() != null) {
                itemCodes = itemMasterRepository.findAllItemCodesByOrgId(req.getOrgId());
            } else {
                itemCodes = itemMasterRepository.findAllItemCodes();
            }
        } else {
            // Get items for specific subcategory
            if (req.getOrgId() != null) {
                itemCodes = itemMasterRepository.findItemCodesBySubCategoryAndOrgId(
                    req.getSubCategory(), 
                    req.getOrgId()
                );
            } else {
                itemCodes = itemMasterRepository.findItemCodesBySubCategory(req.getSubCategory());
            }
        }
        
        if (itemCodes == null || itemCodes.isEmpty()) {
            return results;
        }
        
        // Use batch method to fetch all ledgers
        StockLedgerBatchReq batchReq = new StockLedgerBatchReq();
        batchReq.setItemCodes(itemCodes);
        batchReq.setFromDate(req.getFromDate());
        batchReq.setToDate(req.getToDate());
        batchReq.setOrgId(req.getOrgId());
        
        return getStockLedgerBatch(batchReq);
        
    } catch (Exception e) {
        throw new RuntimeException("Error fetching stock ledger by subcategory: " + e.getMessage(), e);
    }
}









    
    @Override
    public Object getTxnSummary(TxnSummaryReq req) {
        if (CommonUtils.isNotBlank(req.getStartDate()) && CommonUtils.isNotBlank(req.getEndDate())) {
            Date date1 = CommonUtils.getDate(req.getStartDate(), ApplicationConstant.DATE_FORMAT);
            Date date2 = CommonUtils.getDate(req.getEndDate(), ApplicationConstant.DATE_FORMAT);
            Date startDT = CommonUtils.changeDateToDayStart(date1);
            Date endDT = CommonUtils.changeDateToDayENd(date2);
            req.setStartDateDt(startDT);
            req.setEndDateDt(endDT);
        }

        List<ProcessDtls> txnSummary = masterDao.getTxnSummary(req);
        List<TxnSummaryResp> res = new ArrayList<>();
        if (txnSummary != null) {
            for (ProcessDtls r : txnSummary) {
                TxnSummaryResp resp = new TxnSummaryResp();
                BeanUtils.copyProperties(r, resp);
                resp.setTxnDate(CommonUtils.formatDate(r.getCreateDate()));
                resp.setGeneratedBy(r.getCreateUser());

                if ("PO".equalsIgnoreCase(req.getTxnType())) {
                    BigDecimal totalValue = BigDecimal.ZERO;
                    List<ProcessItemMap> items = processItemMapRepository.findAllByProcessIdAndProcessStage(r.getId(), r.getProcessStage());
                    if (items != null) {
                        for (ProcessItemMap i : items) {
                            BigDecimal quantity = i.getQuantity() == null ? BigDecimal.ZERO : new BigDecimal(i.getQuantity());
                            List<ItemCodeMaster> itemCodes = masterDao.getAllActiveItemByOrgId(i.getItemCode(), req.getOrgId());
                            if (itemCodes != null && !itemCodes.isEmpty()) {
                                ItemCodeMaster it = itemCodes.get(0);
                                BigDecimal price = it.getPrice() == null ? BigDecimal.ZERO : it.getPrice();
                                totalValue = totalValue.add((price.multiply(quantity)));

                            }
                        }
                    }
                    resp.setTotalValue(totalValue);
                }
                res.add(resp);
            }
        }
        return res;
    }

    @Override
    public Object getTxnsDtls(TxnDetailsReq req) {
        AllTxnDtlsRes response = new AllTxnDtlsRes();

        if (Objects.isNull(req.getProcessId())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, (ErrorResponseConstant.INVALID_PROCESS_ID_MSG));
        }

        Optional<ProcessDtls> byId = processDtlsRepository.findById(req.getProcessId());
        if (!byId.isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, ErrorResponseConstant.INVALID_PROCESS_ID_MSG);
        }
       /* if(byId.get().getOrgId().compareTo(req.getOrgId()) != 0){
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ORG_ID, ErrorResponseConstant.INVALID_PROCESS_ORG_ID_MSG);
        }*/

        List<IssueNoteDetails> allByProcessId = demandDtlsRepository.findAllByProcessId(req.getProcessId());
        if (!allByProcessId.isEmpty()) {
            IssueNoteDetails details = allByProcessId.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.ISN.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(details), allByProcessId8);
            response.setISNData(r);
        }

        List<GatePassDtls> allByProcessId1 = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.OUT.name());
        if (!allByProcessId1.isEmpty()) {
            GatePassDtls gatePassDtls = allByProcessId1.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.OGP.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(gatePassDtls), allByProcessId8);
            response.setOGPData(r);
        }

        List<GatePassDtls> allByProcessId2 = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
        if (!allByProcessId2.isEmpty()) {
            GatePassDtls gatePassDtls = allByProcessId2.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.IGP.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(gatePassDtls), allByProcessId8);
            response.setIGPData(r);
        }

        List<ReturnNoteDetails> allByProcessId3 = returnNoteDtlsRepository.findAllByProcessId(req.getProcessId());
        if (!allByProcessId3.isEmpty()) {
            ReturnNoteDetails dtls = allByProcessId3.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.RN.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(dtls), allByProcessId8);
            response.setRNData(r);
        }

        List<GRNDtls> allByProcessId4 = grnDtlsRepository.findAllByProcessId(req.getProcessId());
        if (!allByProcessId4.isEmpty()) {
            GRNDtls dtls = allByProcessId4.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.GRN.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(dtls), allByProcessId8);
            response.setGRNData(r);
        }

        List<AcceptRejectDtls> allByProcessId5 = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.ACCEPTANCE.name());
        if (!allByProcessId5.isEmpty()) {
            AcceptRejectDtls dtls = allByProcessId5.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.ACT.name());
            AcceptRejectDtlsResponse processBaseResponse = (AcceptRejectDtlsResponse) prepareResponse(dtls);
            List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
            if (gpList != null && !gpList.isEmpty()) {
                processBaseResponse.setNoa(gpList.get(0).getNoaNo());
            }
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(processBaseResponse, allByProcessId8);
            response.setAcceptData(r);
        }

        List<AcceptRejectDtls> allByProcessId6 = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.REJECTION.name());
        if (!allByProcessId6.isEmpty()) {
            AcceptRejectDtls dtls = allByProcessId6.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.REJ.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(dtls), allByProcessId8);
            response.setRejectData(r);
        }

        List<InspectionReportDtls> allByProcessId7 = inspectionReportDtlsRepository.findAllByProcessId(req.getProcessId());
        if (!allByProcessId7.isEmpty()) {
            InspectionReportDtls dtls = allByProcessId7.get(0);
            List<ProcessItemMap> allByProcessId8 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.IR.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(dtls), allByProcessId8);
            response.setInspectionRptData(r);
        }

        List<InspectionReportDtlsNew> allByProcessId8 = inspectionNewReportDtlsRepository.findAllByProcessId(req.getProcessId());
        if (!allByProcessId8.isEmpty()) {
            InspectionReportDtlsNew dtls = allByProcessId8.get(0);
            List<ProcessItemMap> allByProcessId9 = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), ProcessStageEnum.IRN.name());
            AllTxnDtlsInternalRes r = new AllTxnDtlsInternalRes(prepareResponse(dtls), allByProcessId9);
            response.setInspectionNewRptData(r);
        }

        response.setProcessId(req.getProcessId());
        return response;
    }

    @Override
    public Object getTxnDtlsByTxnType(TxnDtlsByTxnTypeReq req) {
        if (CommonUtils.isNotBlank(req.getStartDate()) && CommonUtils.isNotBlank(req.getEndDate())) {
            req.setStartDateDt(CommonUtils.getDate(req.getStartDate(), ApplicationConstant.DATE_FORMAT));
            req.setEndDateDt(CommonUtils.getDate(req.getEndDate(), ApplicationConstant.DATE_FORMAT));
        } else {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }
        if (!ProcessStageEnum.ISN.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.OGP.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.IGP.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.RN.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.GRN.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.ACT.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.REJ.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.IR.name().equalsIgnoreCase(req.getTxnType())
                && !ProcessStageEnum.IRN.name().equalsIgnoreCase(req.getTxnType())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_TXN_TYPE_CD, ErrorResponseConstant.INVALID_TXN_TYPE_MSG);
        }
        return null;
    }

    @Override
    public Object getStockLedger(StockLedgerRrq req) {
        StockLedgerRes stockLedgerRes = new StockLedgerRes();

        req.setFromDateDt(CommonUtils.changeDateToDayStart(CommonUtils.getDate(req.getFromDate(), ApplicationConstant.DATE_FORMAT)));
        req.setToDateDt(CommonUtils.changeDateToDayENd(CommonUtils.getDate(req.getToDate(), ApplicationConstant.DATE_FORMAT)));
        if (req.getFromDateDt() == null || req.getToDateDt() == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }

        if (req.getFromDateDt().after(req.getToDateDt())) {
            throw new ValidationException(ErrorResponseConstant.FROM_TO_DATE_INVALID_CD, ErrorResponseConstant.FROM_TO_DATE_INVALID_MSG);
        }

        List<ItemCodeMasterHistResp> stockLedger = processDao.getStockLedger(req);

        stockLedgerRes.setTxns(stockLedger);
        stockLedgerRes.setInitQuantity(null);
        stockLedgerRes.setFinalQuantity(null);

        if (stockLedger.size() == 1) {
            stockLedgerRes.setInitQuantity(stockLedger.get(0).getTotalPreQuantity());
            stockLedgerRes.setFinalQuantity(stockLedger.get(0).getTotalPostQuantity());
            stockLedgerRes.setCreateDate(CommonUtils.getDate(stockLedger.get(0).getCreateDateDt()));
            stockLedgerRes.setCreateUser(stockLedger.get(0).getCreateUser());
            stockLedgerRes.setFirstQuantity(stockLedger.get(0).getInitQty());
        } else if (stockLedger.size() > 1) {
            stockLedger = stockLedger.stream().sorted(Comparator.comparing(ItemCodeMasterHistResp::getId)).collect(Collectors.toList());
            stockLedgerRes.setInitQuantity(stockLedger.get(0).getTotalPreQuantity());
            stockLedgerRes.setFinalQuantity(stockLedger.get(stockLedger.size() - 1).getTotalPostQuantity());
            stockLedgerRes.setCreateDate(CommonUtils.getDate(stockLedger.get(stockLedger.size() - 1).getCreateDateDt()));
            stockLedgerRes.setCreateUser(stockLedger.get(stockLedger.size() - 1).getCreateUser());
            stockLedgerRes.setFirstQuantity(stockLedger.get(stockLedger.size() - 1).getInitQty());
        }

        return stockLedgerRes;
    }

    private ProcessBaseResponse prepareResponse(InspectionReportDtls entity) {
        InspectionReportDtlsResponse response = new InspectionReportDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setInspectionRptDt(CommonUtils.getDate(entity.getInspectionRptDt()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(AcceptRejectDtls entity) {
        AcceptRejectDtlsResponse response = new AcceptRejectDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setAcptRejNoteDT(CommonUtils.getDate(entity.getAcptRejNoteDT()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(GRNDtls entity) {
        GRNDtlsResponse response = new GRNDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setGrnDate(CommonUtils.getDate(entity.getGrnDate()));
        response.setNoaDate(CommonUtils.getDate(entity.getNoaDate()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(ReturnNoteDetails entity) {
        ReturnNoteDetailsResponse response = new ReturnNoteDetailsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setReturnNoteDt(CommonUtils.getDate(entity.getReturnNoteDt()));
        response.setIssueNoteDt(CommonUtils.getDate(entity.getIssueNoteDt()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(GatePassDtls entity) {
        GatePassDtlsResponse response = new GatePassDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setNoaDate(CommonUtils.getDate(entity.getNoaDate()));
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setGatePassDate(CommonUtils.getDate(entity.getGatePassDate()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(IssueNoteDetails entity) {
        IssueNoteDetailsResponse response = new IssueNoteDetailsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setIssueNoteDt(CommonUtils.getDate(entity.getIssueNoteDt()));
        return response;
    }

    private ProcessBaseResponse prepareResponse(InspectionReportDtlsNew entity) {
        InspectionReportDtlsResponse response = new InspectionReportDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setInspectionRptDt(CommonUtils.getDate(entity.getInspectionRptDt()));
        return response;
    }

    private void copyCommonDates(ProcessBaseEntity entity, ProcessBaseResponse response) {
        response.setApprovedDate(CommonUtils.getDate(entity.getApprovedDate()));
        response.setGenDate(CommonUtils.getDate(entity.getGenDate()));
        response.setIssueDate(CommonUtils.getDate(entity.getIssueDate()));
    }

    @Override
    public Object getTxnsDtlsV2(TxnDetailsReq req){
        AllTxnDtlsRes response = new AllTxnDtlsRes();
        Long processId = req.getProcessId();
        Long orgId = req.getOrgId();
        String processStage = req.getProcessStage();

        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("ISN")){
            GetSubProcessDtlsRes isn = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.ISN.name(), orgId), true, true);
            response.setISNData(prepareAllTxnDtlsInternalRes(isn, req.getItemCode()));
        }

        
        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("OGP")){
        GetSubProcessDtlsRes ogp = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.OGP.name(), orgId), false, true);
        response.setOGPData(prepareAllTxnDtlsInternalRes(ogp, req.getItemCode()));
        }

    
        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("IGP")){
        GetSubProcessDtlsRes igp = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.IGP.name(), orgId), false, true);
        response.setIGPData(prepareAllTxnDtlsInternalRes(igp, req.getItemCode()));
        }

    
        if(CommonUtils.isBlank(processStage) ||  processStage.equalsIgnoreCase("RN")){
        GetSubProcessDtlsRes rn = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.RN.name(), orgId), false, true);
        response.setRNData(prepareAllTxnDtlsInternalRes(rn, req.getItemCode()));
        }

    
        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("GRN")){
        GetSubProcessDtlsRes grn = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.GRN.name(), orgId), false, true);
        response.setGRNData(prepareAllTxnDtlsInternalRes(grn, req.getItemCode()));
        }

    
        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("ACT")){
        GetSubProcessDtlsRes act = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.ACT.name(), orgId), false, true);
        response.setAcceptData(prepareAllTxnDtlsInternalRes(act, req.getItemCode()));
        }

    
        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("REJ")){
        GetSubProcessDtlsRes rej = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.REJ.name(), orgId), false, true);
        response.setRejectData(prepareAllTxnDtlsInternalRes(rej, req.getItemCode()));
        }

        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("IR")){
        GetSubProcessDtlsRes ir = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.IR.name(), orgId), false, true);
        response.setInspectionRptData(prepareAllTxnDtlsInternalRes(ir, req.getItemCode()));
        }

        if(CommonUtils.isBlank(processStage) || processStage.equalsIgnoreCase("IRN")){
        GetSubProcessDtlsRes irn = processService.getSubProcessDtls(getReq(processId, ProcessStageEnum.IRN.name(), orgId), false, true);
        response.setInspectionNewRptData(prepareAllTxnDtlsInternalRes(irn, req.getItemCode()));
        }

    
        response.setProcessId(req.getProcessId());

        return response;
    }

    private AllTxnDtlsInternalRes prepareAllTxnDtlsInternalRes(GetSubProcessDtlsRes isn, String itemCode) {
        AllTxnDtlsInternalRes res = null;
        if (CommonUtils.isBlank(itemCode)) {
            List<ProcessItemMapDomain> finalList = addUOMDescAndLocatorDescinItems(isn.getItemList());
            res = new AllTxnDtlsInternalRes(isn.getProcessData(), finalList, true);
        } else {
            List<ProcessItemMapDomain> newItemList = new ArrayList<>();
            List<ProcessItemMapDomain> itemList = isn.getItemList();
            if (!itemList.isEmpty()) {
                for (ProcessItemMapDomain d : itemList) {
                    if (itemCode.equalsIgnoreCase(d.getItemCode())) {
                        newItemList.add(d);
                    }
                }
            }

            if(!newItemList.isEmpty()){
                List<ProcessItemMapDomain> finalList = addUOMDescAndLocatorDescinItems(newItemList);
                res = new AllTxnDtlsInternalRes(isn.getProcessData(), finalList, true);
            }
        }
        return res;
    }

    private List<ProcessItemMapDomain> addUOMDescAndLocatorDescinItems(List<ProcessItemMapDomain> itemList) {
        if (itemList != null) {
            for (ProcessItemMapDomain d : itemList) {
                String uom = d.getUom();
                if (uom != null) {
                    UOMMaster uomMaster = CachingUtil.fetchUOMMaster(uom);
                    if (uomMaster != null) {
                        d.setUomDesc(uomMaster.getUomDesc());
                    } else {
                        Optional<UOMMaster> byId = uOMMasterRepository.findById(CommonUtils.getLong(uom));
                        if (byId.isPresent()) {
                            d.setUomDesc(byId.get().getUomDesc());
                            CachingUtil.addUOMMaster(uom, byId.get());
                        }
                    }
                }
                String locatorId = d.getLocatorId();
                if (locatorId != null) {
                    LocatorMaster locatorMaster = CachingUtil.fetchLocatorMaster(locatorId);
                    if (locatorMaster != null) {
                        d.setLocatorDesc(locatorMaster.getLocatorDesc());
                    } else {
                        Optional<LocatorMaster> byId = locatorMasterRepository.findById(CommonUtils.getLong(locatorId));
                        if (byId.isPresent()) {
                            d.setLocatorDesc(byId.get().getLocatorDesc());
                            CachingUtil.addLocatorMaster(locatorId, byId.get());
                        }
                    }
                }
            }
        }
        return itemList;
    }

    private GetSubProcessDtlsReq getReq(Long processId, String processStage, Long orgId) {
        GetSubProcessDtlsReq req = new GetSubProcessDtlsReq();
        req.setProcessId(processId);
        req.setOrgId(orgId);
        req.setProcessStage(processStage);
        return req;
    }

    @Override
    public Object getTxnSummaryForAllOrg(TxnSummaryReq req) {
        if (CommonUtils.isNotBlank(req.getStartDate()) && CommonUtils.isNotBlank(req.getEndDate())) {
            Date date1 = CommonUtils.getDate(req.getStartDate(), ApplicationConstant.DATE_FORMAT);
            Date date2 = CommonUtils.getDate(req.getEndDate(), ApplicationConstant.DATE_FORMAT);
            Date startDT = CommonUtils.changeDateToDayStart(date1);
            Date endDT = CommonUtils.changeDateToDayENd(date2);
            req.setStartDateDt(startDT);
            req.setEndDateDt(endDT);
        }
        List<TxnSummaryForAllOrgResp> responseList = new ArrayList<>();
        List<OrganizationMaster> orgMasterList = organizationMasterRepository.findAllByStatus("A");
        if (orgMasterList != null && orgMasterList.size() > 0) {
            TxnSummaryForAllOrgResp allOrgResp = null;
            for (OrganizationMaster om : orgMasterList) {
                if (om.getId() == 35 || om.getId() == 36) {//Demo organisations
                    continue;
                }
                allOrgResp = new TxnSummaryForAllOrgResp();
                req.setOrgId(om.getId());
                List<ProcessDtls> txnSummary = masterDao.getTxnSummary(req);
                List<TxnSummaryResp> res = new ArrayList<>();
                if (txnSummary != null) {
                    for (ProcessDtls r : txnSummary) {
                        TxnSummaryResp resp = new TxnSummaryResp();
                        BeanUtils.copyProperties(r, resp);
                        resp.setTxnDate(CommonUtils.formatDate(r.getCreateDate()));
                        resp.setGeneratedBy(r.getCreateUser());

                        if ("PO".equalsIgnoreCase(req.getTxnType())) {
                            BigDecimal totalValue = BigDecimal.ZERO;
                            List<ProcessItemMap> items = processItemMapRepository.findAllByProcessIdAndProcessStage(r.getId(), r.getProcessStage());
                            if (items != null) {
                                for (ProcessItemMap i : items) {
                                    BigDecimal quantity = i.getQuantity() == null ? BigDecimal.ZERO : new BigDecimal(i.getQuantity());
                                    List<ItemCodeMaster> itemCodes = masterDao.getAllActiveItemByOrgId(i.getItemCode(), req.getOrgId());
                                    if (itemCodes != null && !itemCodes.isEmpty()) {
                                        ItemCodeMaster it = itemCodes.get(0);
                                        BigDecimal price = it.getPrice() == null ? BigDecimal.ZERO : it.getPrice();
                                        totalValue = totalValue.add((price.multiply(quantity)));

                                    }
                                }
                            }
                            resp.setTotalValue(totalValue);
                        }
                        res.add(resp);
                    }
                }
                if (!res.isEmpty()) {
                    allOrgResp.setOrgId(om.getId());
                    allOrgResp.setOrgName(om.getOrganizationName());
                }
                allOrgResp.setRespList(res);
                responseList.add(allOrgResp);
            }
        }
        return responseList;
    }
}
