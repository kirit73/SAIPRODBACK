package com.sai.inventory.service.impl;

import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.dao.ProcessDao;
import com.sai.inventory.domain.ProcessItemMapDomain;
import com.sai.inventory.domain.master.request.ItemDetailsForm;
import com.sai.inventory.domain.process.request.*;
import com.sai.inventory.domain.process.response.*;
import com.sai.inventory.entity.*;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.*;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.service.MasterService;
import com.sai.inventory.service.ProcessService;
import com.sai.inventory.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

@Service
public class ProcessServiceImpl implements ProcessService {

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
    private MasterDao masterDao;
    @Autowired
    private LocatorMasterRepository locatorMasterRepository;
    @Autowired
    private MasterService masterService;
    @Autowired
    private ProcessDao processDao;

    @Autowired
    private ItemMasterHistRepository itemMasterHistRepository;
    @Autowired
    private JwtService jwtService;

    @Transactional
    @Override
    public Object saveIssueNote(IssueNoteFormReq req) {
        validateIssueNoteReq(req);
        IssueNoteFormRes res = new IssueNoteFormRes();
        ProcessDtls processDtls = prepareProcessDetails(req.getProcessType(), req.getUserId(), ProcessStageEnum.ISN.name(), req.getOrgId());
        processDtls.setCorrectionProcessFlag(req.getCorrectionProcessFlag());
        processDtls = processDtlsRepository.save(processDtls);

        IssueNoteDetails demandDetails = prepareIssueNoteDtls(req, processDtls);
        IssueNoteDetails save = demandDtlsRepository.save(demandDetails);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), ProcessStageEnum.ISN.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        List<ItemCodeMaster> updateMaster = new ArrayList<>();
        List<ItemCodeMasterHist> masterHists = new ArrayList<>();
        Date date = new Date();
        for (ItemDetailsForm frm : req.getItems()) {
            ItemCodeMaster itemCodeMaster = null;
            Optional<ItemCodeMaster> byId = itemMasterRepository.findAllByItemMasterCdAndLocatorIdAndStatus(frm.getItemCode(), CommonUtils.getLong(frm.getLocatorId()), "A");
            if (byId.isPresent()) {
                itemCodeMaster = byId.get();
            } else {
                throw new ValidationException(ErrorResponseConstant.INVALID_ITEM_ID_CD, ErrorResponseConstant.INVALID_ITEM_ID_MSG + "-" + frm.getItemCode() + ", " + frm.getLocatorId());
            }

            ItemCodeMasterHist historyData = getHistoryData(itemCodeMaster, req.getUserId(), processDtls.getId(), ProcessStageEnum.ISN.name(), req.getOrgId());

            Double quantity = itemCodeMaster.getQuantity() == null ? 0d : itemCodeMaster.getQuantity();
            Double updateQnty = quantity - frm.getQuantity();
            if (updateQnty < 0) {
                throw new ValidationException(ErrorResponseConstant.QTY_GREAT_THAN_AVL_CD, ErrorResponseConstant.QTY_GREAT_THAN_AVL_MSG + "-" + frm.getItemCode() + ", " + frm.getLocatorId());
            }
            itemCodeMaster.setQuantity(updateQnty);

            if (itemCodeMaster.getInitQuantity() != null && itemCodeMaster.getInitQuantity() == 0) {
                itemCodeMaster.setInitQuantity(updateQnty);
            }
            itemCodeMaster.setUpdateDate(date);
            itemCodeMaster.setUpdateUser(req.getUserId());
            updateMaster.add(itemCodeMaster);

            historyData.setPostQuantity(updateQnty);

            Double totalPreQty = historyData.getTotalPreQuantity();
            Double totalPostQty = totalPreQty - frm.getQuantity();
            historyData.setTotalPostQuantity(totalPostQty);
            masterHists.add(historyData);
        }

        itemMasterRepository.saveAll(updateMaster);
        itemMasterHistRepository.saveAll(masterHists);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(req.getProcessType());
        return res;
    }

    @Override
    public Object getAllProcess(Long orgId) {
        return processDao.getAllProcess(orgId);
        //return processDtlsRepository.findAllByOrgId(orgId);
    }

    @Transactional
    @Override
    public ProcessFormRes saveGatePassDtls(GatePassReq req) {
        ProcessDtls processDtls = null;
        if ((ProcessTypeEnum.PO.name().equalsIgnoreCase(req.getProcessType()) || ProcessTypeEnum.NIRP.name().equalsIgnoreCase(req.getProcessType())) && ProcessStageEnum.IGP.name().equalsIgnoreCase(req.getNextStageUpdate())) {
            validateGatePassData(req);
            processDtls = prepareProcessDetails(req.getProcessType(), req.getUserId(), req.getNextStageUpdate(), req.getOrgId());
            processDtls.setCorrectionProcessFlag("N");
            processDtls = processDtlsRepository.save(processDtls);
            req.setProcessId(processDtls.getId() + "");
        } else {
            processDtls = validateAndUpdateProcessStage(req.getProcessId(), req.getCurrentStageInDB(), req.getNextStageUpdate(), req.getUserId(), req.getOrgId());
            validateGatePassData(req);
            processDtls = processDtlsRepository.save(processDtls);
        }

        ProcessFormRes res = new ProcessFormRes();

        GatePassDtls gatePassDtls = prepareGatePassDtls(req);
        List<GatePassDtls> gatePass = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(CommonUtils.getLong(req.getProcessId()), req.getGatePassType());
        if (gatePass.size() > 1) {
            throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
        }
        if (!gatePass.isEmpty()) {
            List<AcceptRejectDtls> rnProcess = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(CommonUtils.getLong(req.getProcessId()), NoteTypeEnum.REJECTION.name());
            if (rnProcess.isEmpty()) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            } else {
                // Date rnDate = rnProcess.get(0).getCreateDate();
                // Date gpDate = gatePass.get(0).getCreateDate();
                /*if (gatePassDtls.getUpdateDate() != null) {
                    throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
                }*/
            }
            //gatePassDtls.setId(gatePass.get(0).getId());
            //gatePassDtls.setUpdateDate(new Date());
            //gatePassDtls.setUpdateUser(req.getUserId());
        }
        GatePassDtls save = gatePassDtlsRepository.save(gatePassDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), req.getNextStageUpdate(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }

    @Transactional
    @Override
    public Object saveReturnNote(ReturnNoteFormReq req) {
        ProcessDtls processDtls = validateAndUpdateProcessStage(req.getProcessId(), ProcessStageEnum.IGP.name(), ProcessStageEnum.RN.name(), req.getUserId(), req.getOrgId());
        validateReturnNoteData(req);
        processDtls = processDtlsRepository.save(processDtls);

        if (!ProcessTypeEnum.PO.name().equalsIgnoreCase(processDtls.getProcessType())) {
            validateIssueNoteItemsBalance(CommonUtils.getLong(req.getProcessId()), ProcessStageEnum.RN.name(), req.getItems());
        }
        ProcessFormRes res = new ProcessFormRes();
        ReturnNoteDetails noteDtls = prepareReturnNoteDtls(req);
        List<ReturnNoteDetails> allByProcessId = returnNoteDtlsRepository.findAllByProcessId(CommonUtils.getLong(req.getProcessId()));
        if (!allByProcessId.isEmpty()) {
            if (ProcessTypeEnum.PO.name().equalsIgnoreCase(processDtls.getProcessType())) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            }
            noteDtls.setId(allByProcessId.get(0).getId());
        }
        ReturnNoteDetails save = returnNoteDtlsRepository.save(noteDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), ProcessStageEnum.RN.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }

    @Transactional
    @Override
    public Object saveGRN(GRNReq req) {
        ProcessDtls processDtls = null;
        if ("Y".equalsIgnoreCase(req.getCorrectionProcessFlag())) {
            processDtls = prepareProcessDetails(ProcessTypeEnum.CRN.name(), req.getUserId(), ProcessStageEnum.ISN.name(), req.getOrgId());
            processDtls.setCorrectionProcessFlag(req.getCorrectionProcessFlag());
            processDtls = processDtlsRepository.save(processDtls);
        } else {
            processDtls = validateAndUpdateProcessStage(req.getProcessId(), ProcessStageEnum.RN.name(), ProcessStageEnum.GRN.name(), req.getUserId(), req.getOrgId());
            processDtls = processDtlsRepository.save(processDtls);
        }
        validateGRNData(req);
        req.setProcessId(processDtls.getId() + "");
        if (!ProcessTypeEnum.PO.name().equalsIgnoreCase(processDtls.getProcessType()) && !ProcessTypeEnum.CRN.name().equalsIgnoreCase(processDtls.getProcessType())) {
            validateIssueNoteItemsBalance(CommonUtils.getLong(req.getProcessId()), ProcessStageEnum.GRN.name(), req.getItems());
        }
        ProcessFormRes res = new ProcessFormRes();

        GRNDtls grnDtls = prepareGRNDtls(req);
        List<GRNDtls> allByProcessId = grnDtlsRepository.findAllByProcessId(CommonUtils.getLong(req.getProcessId()));
        if (!allByProcessId.isEmpty()) {
            if (ProcessTypeEnum.PO.name().equalsIgnoreCase(processDtls.getProcessType())) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            }
            grnDtls.setId(allByProcessId.get(0).getId());
        }
        GRNDtls save = grnDtlsRepository.save(grnDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), ProcessStageEnum.GRN.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        List<ItemCodeMaster> updateMaster = new ArrayList<>();
        List<ItemCodeMasterHist> masterHists = new ArrayList<>();
        Date date = new Date();
        for (ItemDetailsForm frm : req.getItems()) {
            ItemCodeMaster itemCodeMaster = null;
            Optional<ItemCodeMaster> byId = itemMasterRepository.findAllByItemMasterCdAndLocatorIdAndStatus(frm.getItemCode(), CommonUtils.getLong(frm.getLocatorId()), "A");
            if (byId.isPresent()) {
                itemCodeMaster = byId.get();
            } else {
                itemCodeMaster = masterService.saveMasterNewEntry(frm.getItemCode(), CommonUtils.getLong(frm.getLocatorId()), req.getUserId());
                // throw new ValidationException(ErrorResponseConstant.INVALID_ITEM_ID_CD, ErrorResponseConstant.INVALID_ITEM_ID_MSG + "-" + frm.getItemCode() + ", " + frm.getLocatorId());
            }

            ItemCodeMasterHist historyData = getHistoryData(itemCodeMaster, req.getUserId(), processDtls.getId(), ProcessStageEnum.GRN.name(), req.getOrgId());

            Double quantity = itemCodeMaster.getQuantity() == null ? 0d : itemCodeMaster.getQuantity();
            Double updateQnty = quantity + frm.getQuantity();
            itemCodeMaster.setQuantity(updateQnty);
            if (itemCodeMaster.getInitQuantity() != null && itemCodeMaster.getInitQuantity() == 0) {
                itemCodeMaster.setInitQuantity(updateQnty);
            }
            itemCodeMaster.setUpdateDate(date);
            itemCodeMaster.setUpdateUser(req.getUserId());

            if (frm.getUnitPrice() != null) {
                double reqQty = frm.getQuantity();
                String itemCode = frm.getItemCode();
                BigDecimal unitPrice = frm.getUnitPrice();
                BigDecimal updatedItemPrice = updateItemPrice(itemCode, reqQty, unitPrice, historyData.getTotalPreQuantity(), historyData.getPrice(), req.getOrgId());
                itemCodeMaster.setPrice(updatedItemPrice);
            }

            updateMaster.add(itemCodeMaster);

            historyData.setPostQuantity(updateQnty);

            Double totalPreQty = historyData.getTotalPreQuantity();
            Double totalPostQty = totalPreQty + frm.getQuantity();
            historyData.setTotalPostQuantity(totalPostQty);
            masterHists.add(historyData);
        }
        itemMasterRepository.saveAll(updateMaster);
        itemMasterHistRepository.saveAll(masterHists);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }

    private BigDecimal updateItemPrice(String itemCode, double reqQty, BigDecimal unitPrice, Double totalPreQuantity, BigDecimal price, Long orgId) {
        BigDecimal avgPrice;
        if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
            avgPrice = unitPrice == null ? BigDecimal.ZERO : unitPrice;
        } else {
            BigDecimal totalOldQty = totalPreQuantity == null ? BigDecimal.ZERO : new BigDecimal(totalPreQuantity);
            BigDecimal totalNewQty = new BigDecimal(reqQty);
            BigDecimal oldTotalVal = totalOldQty.multiply(price);
            BigDecimal newTotalVal = totalNewQty.multiply(unitPrice == null ? BigDecimal.ZERO : unitPrice);
            BigDecimal totalNewCount = totalOldQty.add(totalNewQty);
            BigDecimal totalAllVal = oldTotalVal.add(newTotalVal);

            avgPrice = totalAllVal.divide(totalNewCount, 2, RoundingMode.HALF_EVEN);
        }

        List<ItemCodeMaster> allItems = masterDao.getAllActiveItemByOrgId(itemCode, orgId);
        List<ItemCodeMaster> updated = new ArrayList<>();
        if (allItems != null) {
            for (ItemCodeMaster mst : allItems) {
                mst.setPrice(avgPrice);
                updated.add(mst);
            }
            itemMasterRepository.saveAll(updated);
        }

        return avgPrice;
    }

    private void validateIssueNoteItemsBalance(Long processId, String processStage, List<ItemDetailsForm> items) {
        Map<String, Double> availableItemCount = new HashMap();
        List<ProcessItemMap> issueNote = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.ISN.name());
        for (ProcessItemMap pim : issueNote) {
            Double cnt = 0d;
            if (availableItemCount.containsKey(pim.getItemCode())) {
                cnt = availableItemCount.get(pim.getItemCode());
            }
            cnt += pim.getQuantity(); // adding count for issue note of all items
            availableItemCount.put(pim.getItemCode(), cnt);
        }

        List<ProcessItemMap> prevData = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, processStage);
        for (ProcessItemMap ppim : prevData) {
            Double pCnt = 0d;
            if (availableItemCount.containsKey(ppim.getItemCode())) {
                pCnt = availableItemCount.get(ppim.getItemCode());
            } else {
                throw new ValidationException(ErrorResponseConstant.ITEM_NOT_FOUND_ISSUE_NOT_CD, ErrorResponseConstant.ITEM_NOT_FOUND_ISSUE_NOT_MSG + ppim.getItemCode());
            }
            pCnt -= ppim.getQuantity();  // subtracting count for prev data of all items already submitted
            availableItemCount.put(ppim.getItemCode(), pCnt);
        }

        if (items != null) {
            for (ItemDetailsForm idf : items) {
                Double cnt = 0d;
                if (availableItemCount.containsKey(idf.getItemCode())) {
                    cnt = availableItemCount.get(idf.getItemCode());
                }
                cnt -= idf.getQuantity();
                if (cnt < 0) {
                    throw new ValidationException(ErrorResponseConstant.MAX_QTY_ISSUE_NOTE_ERROR_CD, ErrorResponseConstant.MAX_QTY_ISSUE_NOTE_ERROR_MSG + idf.getItemCode() + ", quantity balance " + availableItemCount.get(idf.getItemCode()));
                }
            }
        }
    }

    @Override
    public GetSubProcessDtlsRes getSubProcessDtls(GetSubProcessDtlsReq req, boolean checkProcessId, boolean isTxnDtls) {
        if (Objects.isNull(req.getProcessId())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, (ErrorResponseConstant.INVALID_PROCESS_ID_MSG));
        }
        Optional<ProcessDtls> byId = null;
        if (checkProcessId) {
            byId = processDtlsRepository.findById(req.getProcessId());
            if (!byId.isPresent()) {
                throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, ErrorResponseConstant.INVALID_PROCESS_ID_MSG);
            }
        }

        /*if(byId.get().getOrgId().compareTo(req.getOrgId()) != 0){
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ORG_ID, ErrorResponseConstant.INVALID_PROCESS_ORG_ID_MSG);
        }*/

        Long uniqueId = null;
        GetSubProcessDtlsRes response = new GetSubProcessDtlsRes();
        if (ProcessStageEnum.ISN.name().equalsIgnoreCase(req.getProcessStage())) {
            List<IssueNoteDetails> allByProcessId = demandDtlsRepository.findAllByProcessId(req.getProcessId());
            if (!allByProcessId.isEmpty()) {
                IssueNoteDetails details = allByProcessId.get(0);
                response.setProcessData(prepareResponse(details));
            }
        } else if (ProcessStageEnum.OGP.name().equalsIgnoreCase(req.getProcessStage())) {
            List<GatePassDtls> allByProcessId = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.OUT.name());
            if (!allByProcessId.isEmpty()) {
                GatePassDtls gatePassDtls = CommonUtils.getCorrectGPD(req.isRejectProcess(), allByProcessId);
                uniqueId = gatePassDtls.getId();
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = CommonUtils.getCorrectGPD(req.isRejectProcess(), gpList);
                }
                response.setProcessData(prepareResponse(gatePassDtls, gp));

            }
        } else if (ProcessStageEnum.IGP.name().equalsIgnoreCase(req.getProcessStage())) {
            List<GatePassDtls> allByProcessId = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
            if (!allByProcessId.isEmpty()) {
                GatePassDtls gatePassDtls = CommonUtils.getCorrectGPD(req.isRejectProcess(), allByProcessId);
                uniqueId = gatePassDtls.getId();
                response.setProcessData(prepareResponse(gatePassDtls, gatePassDtls));
                if (byId != null && ProcessTypeEnum.IOP.name().equalsIgnoreCase(byId.get().getProcessType())) {
                    List<GatePassDtls> dataList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.OUT.name());
                    if (!dataList.isEmpty()) {
                        GatePassDtls data = dataList.get(0);
                        GatePassDtlsResponse processData = (GatePassDtlsResponse) response.getProcessData();
                        processData.setNoa(data.getNoaNo());
                        processData.setDateOfDelivery(CommonUtils.getDate(data.getDateOfDelivery()));
                        processData.setModeOfDelivery(data.getModeOfDelivery());
                        processData.setChallanNo(data.getChallanNo());
                        response.setProcessData(processData);
                    }
                }
            }
        } else if (ProcessStageEnum.RN.name().equalsIgnoreCase(req.getProcessStage())) {
            List<ReturnNoteDetails> allByProcessId = returnNoteDtlsRepository.findAllByProcessId(req.getProcessId());
            if (!allByProcessId.isEmpty()) {
                ReturnNoteDetails dtls = allByProcessId.get(0);
                response.setProcessData(prepareResponse(dtls));
            }
        } else if (ProcessStageEnum.GRN.name().equalsIgnoreCase(req.getProcessStage())) {
            List<GRNDtls> allByProcessId = grnDtlsRepository.findAllByProcessId(req.getProcessId());
            if (!allByProcessId.isEmpty()) {
                GRNDtls dtls = allByProcessId.get(0);
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = gpList.get(0);
                }
                response.setProcessData(prepareResponse(dtls, gp));
            }
        } else if (ProcessStageEnum.ACT.name().equalsIgnoreCase(req.getProcessStage())) {
            List<AcceptRejectDtls> allByProcessId = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.ACCEPTANCE.name());
            if (!allByProcessId.isEmpty()) {
                AcceptRejectDtls dtls = CommonUtils.getCorrectACT(req.isRejectProcess(), allByProcessId);
                uniqueId = dtls.getId();
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = CommonUtils.getCorrectGPD(req.isRejectProcess(), gpList);
                }

                response.setProcessData(prepareResponse(dtls, gp));
            }
        } else if (ProcessStageEnum.REJ.name().equalsIgnoreCase(req.getProcessStage())) {
            List<AcceptRejectDtls> allByProcessId = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.REJECTION.name());
            if (!allByProcessId.isEmpty()) {
                AcceptRejectDtls dtls = CommonUtils.getCorrectACT(req.isRejectProcess(), allByProcessId);
                uniqueId = dtls.getId();
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = CommonUtils.getCorrectGPD(req.isRejectProcess(), gpList);
                }

                response.setProcessData(prepareResponse(dtls, gp));
            }
        } else if (ProcessStageEnum.IR.name().equalsIgnoreCase(req.getProcessStage())) {
            List<InspectionReportDtls> allByProcessId = inspectionReportDtlsRepository.findAllByProcessId(req.getProcessId());
            if (!allByProcessId.isEmpty()) {
                InspectionReportDtls dtls = CommonUtils.getCorrectIRD(req.isRejectProcess(), allByProcessId);
                uniqueId = dtls.getId();
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = CommonUtils.getCorrectGPD(req.isRejectProcess(), gpList);
                    //uniqueId = gp.getId();
                }
                response.setProcessData(prepareResponse(dtls, gp));
            }
        } else if (ProcessStageEnum.IRN.name().equalsIgnoreCase(req.getProcessStage())) {
            List<InspectionReportDtlsNew> allByProcessId = inspectionNewReportDtlsRepository.findAllByProcessId(req.getProcessId());
            if (!allByProcessId.isEmpty()) {
                InspectionReportDtlsNew dtls = CommonUtils.getCorrectIRDN(req.isRejectProcess(), allByProcessId);
                uniqueId = dtls.getId();
                GatePassDtls gp = null;
                List<GatePassDtls> gpList = gatePassDtlsRepository.findAllByProcessIdAndGatePassType(req.getProcessId(), GatePassTypeEnum.IN.name());
                if (!gpList.isEmpty()) {
                    gp = CommonUtils.getCorrectGPD(req.isRejectProcess(), gpList);
                }
                response.setProcessData(prepareResponse(dtls, gp));
            }
        } else {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_STAGE, ErrorResponseConstant.INVALID_PROCESS_STAGE_MSG);
        }

        List<ProcessItemMapDomain> domainList = new ArrayList<>();
        List<ProcessItemMap> allByProcessId = null;

        if (isTxnDtls) {
            if (uniqueId != null) {
                allByProcessId = processItemMapRepository.findAllByProcessIdAndUniqueId(req.getProcessId(), uniqueId);
            }
            if (allByProcessId == null || allByProcessId.isEmpty()) {
                allByProcessId = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), req.getProcessStage());
            }
        // } else {
        //     if (ProcessStageEnum.ISN.name().equalsIgnoreCase(req.getProcessStage())) {
        //         allByProcessId = getBalanceItemsForISN(req.getProcessId());
        //     } else if (ProcessStageEnum.RN.name().equalsIgnoreCase(req.getProcessStage())) {
        //         allByProcessId = getBalanceItemsForRN(req.getProcessId());
        //     } else {
        //         if (uniqueId != null) {
        //             allByProcessId = processItemMapRepository.findAllByProcessIdAndUniqueId(req.getProcessId(), uniqueId);
        //         }
        //         if (allByProcessId == null || allByProcessId.isEmpty()) {
        //             allByProcessId = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), req.getProcessStage());
        //         }
        //     }
        // }
        } else {
            if (ProcessStageEnum.ISN.name().equalsIgnoreCase(req.getProcessStage())) {
                allByProcessId = getBalanceItemsForISN(req.getProcessId());
            } else if (ProcessStageEnum.RN.name().equalsIgnoreCase(req.getProcessStage())) {
                allByProcessId = getBalanceItemsForRN(req.getProcessId());
            } else if (ProcessStageEnum.IRN.name().equalsIgnoreCase(req.getProcessStage())) {
                if (uniqueId != null) {
                    allByProcessId = processItemMapRepository.findAllByProcessIdAndUniqueId(req.getProcessId(), uniqueId);
                }
                if (allByProcessId == null || allByProcessId.isEmpty()) {
                    allByProcessId = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), req.getProcessStage());
                }
                
                if (allByProcessId != null && !allByProcessId.isEmpty() && req.getNoteType() != null) {
                    List<ProcessItemMap> filteredItems = new ArrayList<>();
                    
                    for (ProcessItemMap item : allByProcessId) {
                        if ("ACCEPTANCE".equalsIgnoreCase(req.getNoteType())) {
                            Double acceptedQty = item.getAcceptedQuantity() != null ? item.getAcceptedQuantity() : 0.0;
                            if (acceptedQty > 0) {
                                item.setQuantity(acceptedQty);
                                filteredItems.add(item);
                            }
                        } else if ("REJECTION".equalsIgnoreCase(req.getNoteType())) {
                            Double rejectedQty = item.getRejectedQuantity() != null ? item.getRejectedQuantity() : 0.0;
                            if (rejectedQty > 0) {
                                item.setQuantity(rejectedQty);
                                filteredItems.add(item);
                            }
                        }
                    }
                    
                    allByProcessId = filteredItems;
                }
            } else {
                if (uniqueId != null) {
                    allByProcessId = processItemMapRepository.findAllByProcessIdAndUniqueId(req.getProcessId(), uniqueId);
                }
                if (allByProcessId == null || allByProcessId.isEmpty()) {
                    allByProcessId = processItemMapRepository.findAllByProcessIdAndProcessStage(req.getProcessId(), req.getProcessStage());
                }
            }
        }
        for (ProcessItemMap im : allByProcessId) {
            ProcessItemMapDomain domain = new ProcessItemMapDomain();
            BeanUtils.copyProperties(im, domain);
            domainList.add(domain);
        }

        response.setItemList(domainList);
        response.setProcessStage(req.getProcessStage());
        response.setProcessId(req.getProcessId());

        return response;
    }

    private ProcessBaseResponse prepareResponse(InspectionReportDtls entity, GatePassDtls details) {
        InspectionReportDtlsResponse response = new InspectionReportDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setInspectionRptDt(CommonUtils.getDate(entity.getInspectionRptDt()));
        if (details != null) {
            response.setNoa(details.getNoaNo());
            response.setNoaDate(details.getNoaDate());
            response.setNoaDateFormat(CommonUtils.getDate(details.getNoaDate()));
            response.setChallanNo(details.getChallanNo());
            response.setModeOfDelivery(details.getModeOfDelivery());
        }
        return response;
    }

    private ProcessBaseResponse prepareResponse(InspectionReportDtlsNew entity, GatePassDtls details) {
        InspectionReportDtlsResponse response = new InspectionReportDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setInspectionRptDt(CommonUtils.getDate(entity.getInspectionRptDt()));
        if (details != null) {
            response.setNoa(details.getNoaNo());
            response.setNoaDate(details.getNoaDate());
            response.setNoaDateFormat(CommonUtils.getDate(details.getNoaDate()));
            response.setChallanNo(details.getChallanNo());
            response.setModeOfDelivery(details.getModeOfDelivery());
        }
        return response;
    }

    private ProcessBaseResponse prepareResponse(AcceptRejectDtls entity, GatePassDtls details) {
        AcceptRejectDtlsResponse response = new AcceptRejectDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setAcptRejNoteDT(CommonUtils.getDate(entity.getAcptRejNoteDT()));
        if (details != null) {
            response.setNoa(details.getNoaNo());
            response.setNoaDate(details.getNoaDate());
            response.setNoaDateFormat(CommonUtils.getDate(details.getNoaDate()));
            response.setModeOfDelivery(details.getModeOfDelivery());
            response.setChallanNo(details.getChallanNo());
        }

        return response;
    }

    private ProcessBaseResponse prepareResponse(GRNDtls entity, GatePassDtls details) {
        GRNDtlsResponse response = new GRNDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setGrnDate(CommonUtils.getDate(entity.getGrnDate()));
        response.setNoaDate(CommonUtils.getDate(entity.getNoaDate()));
        if (details != null) {
            response.setTermsCondition(details.getTermsCondition());
            response.setNote(details.getNote());
            if (CommonUtils.isBlank(response.getNoaNo())) {
                response.setNoaNo(details.getNoaNo());
            }
        }
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

    private ProcessBaseResponse prepareResponse(GatePassDtls entity, GatePassDtls igpData) {
        GatePassDtlsResponse response = new GatePassDtlsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setNoaDate(CommonUtils.getDate(entity.getNoaDate()));
        response.setDateOfDelivery(CommonUtils.getDate(entity.getDateOfDelivery()));
        response.setGatePassDate(CommonUtils.getDate(entity.getGatePassDate()));
        if (igpData != null) {
            if (CommonUtils.isBlank(response.getSupplierCode())) {
                response.setSupplierCode(igpData.getSupplierCode());
            }
            if (CommonUtils.isBlank(response.getNoaNo())) {
                response.setNoa(igpData.getNoaNo());
            }
        }
        return response;
    }

    private ProcessBaseResponse prepareResponse(IssueNoteDetails entity) {
        IssueNoteDetailsResponse response = new IssueNoteDetailsResponse();
        BeanUtils.copyProperties(entity, response);
        copyCommonDates(entity, response);
        response.setIssueNoteDt(CommonUtils.getDate(entity.getIssueNoteDt()));
        response.setDemandNoteDt(CommonUtils.getDate(entity.getDemandNoteDt()));
        return response;
    }

    private void copyCommonDates(ProcessBaseEntity entity, ProcessBaseResponse response) {
        response.setApprovedDate(CommonUtils.getDate(entity.getApprovedDate()));
        response.setGenDate(CommonUtils.getDate(entity.getGenDate()));
        response.setIssueDate(CommonUtils.getDate(entity.getIssueDate()));
    }

    @Transactional
    @Override
    public Object saveAcceptRejectNoteReq(AcceptRejectNoteReq req) {
        ProcessDtls processDtls = validateAndUpdateProcessStage(String.valueOf(req.getProcessId()), NoteTypeEnum.ACCEPTANCE.name().equalsIgnoreCase(req.getTypeOfNote()) ? ProcessStageEnum.IR.name() : ProcessStageEnum.GRN.name(), NoteTypeEnum.ACCEPTANCE.name().equalsIgnoreCase(req.getTypeOfNote()) ? ProcessStageEnum.ACT.name() : ProcessStageEnum.REJ.name(), req.getUserId(), req.getOrgId());
        validateAcceptRejectNoteData(req);
        processDtls = processDtlsRepository.save(processDtls);

        ProcessFormRes res = new ProcessFormRes();

        AcceptRejectDtls noteDtls = prepareAcceptRejectDtls(req);
        List<AcceptRejectDtls> allByProcessId = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), req.getTypeOfNote());
        if (allByProcessId.size() > 1) {
            throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
        }

        if (!allByProcessId.isEmpty()) {
            List<AcceptRejectDtls> rnProcess = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.REJECTION.name());
            if (rnProcess.isEmpty()) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            }
        }
        AcceptRejectDtls save = acptRejNoteDtlsRepository.save(noteDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), NoteTypeEnum.ACCEPTANCE.name().equalsIgnoreCase(req.getTypeOfNote()) ? ProcessStageEnum.ACT.name() : ProcessStageEnum.REJ.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }

    @Transactional
    @Override
    public Object saveInspectionReportReq(InspectionReportReq req) {
        ProcessDtls processDtls = validateAndUpdateProcessStage(String.valueOf(req.getProcessId()), ProcessStageEnum.REJ.name(), ProcessStageEnum.IR.name(), req.getUserId(), req.getOrgId());
        validateInspectionReport(req);
        processDtls = processDtlsRepository.save(processDtls);

        ProcessFormRes res = new ProcessFormRes();

        InspectionReportDtls noteDtls = prepareInspectionReportReq(req);
        List<InspectionReportDtls> allByProcessId = inspectionReportDtlsRepository.findAllByProcessId(req.getProcessId());
        if (allByProcessId.size() > 1) {
            throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
        }

        if (!allByProcessId.isEmpty()) {
            List<AcceptRejectDtls> rnProcess = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.REJECTION.name());
            if (rnProcess.isEmpty()) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            }
        }

        InspectionReportDtls save = inspectionReportDtlsRepository.save(noteDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), ProcessStageEnum.IR.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }


    @Transactional
    @Override
    public Object saveInspectionNewReportReq(InspectionReportReq req) {
        ProcessDtls processDtls = validateAndUpdateProcessStage(String.valueOf(req.getProcessId()), ProcessStageEnum.REJ.name(), ProcessStageEnum.IRN.name(), req.getUserId(), req.getOrgId());
        validateInspectionReport(req);
        processDtls = processDtlsRepository.save(processDtls);

        ProcessFormRes res = new ProcessFormRes();

        InspectionReportDtlsNew noteDtls = prepareNewInspectionReportReq(req);
        List<InspectionReportDtlsNew> allByProcessId = inspectionNewReportDtlsRepository.findAllByProcessId(req.getProcessId());
        if (allByProcessId.size() > 1) {
            throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
        }

        if (!allByProcessId.isEmpty()) {
            List<AcceptRejectDtls> rnProcess = acptRejNoteDtlsRepository.findAllByProcessIdAndTypeOfNote(req.getProcessId(), NoteTypeEnum.REJECTION.name());
            if (rnProcess.isEmpty()) {
                throw new ValidationException(ErrorResponseConstant.DATA_ALREADY_SUBMIT_CD, ErrorResponseConstant.DATA_ALREADY_SUBMIT_MSG);
            }
        }

        InspectionReportDtlsNew save = inspectionNewReportDtlsRepository.save(noteDtls);

        List<ProcessItemMap> processItemMap = prepareProcessItemMap(req.getItems(), processDtls, req.getUserId(), ProcessStageEnum.IRN.name(), req.getOrgId(), save.getId());
        processItemMapRepository.saveAll(processItemMap);

        res.setSubProcessId(save.getId());
        res.setProcessId(processDtls.getId() + "");
        res.setProcessType(processDtls.getProcessType());
        return res;
    }

    @Override
    public void validateUserType(String auth) {
        if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            throw new ValidationException(ErrorResponseConstant.ACTION_NOT_ALWD_HQ_CD, ErrorResponseConstant.ACTION_NOT_ALWD_HQ_MSG);
        }
    }

    private InspectionReportDtlsNew prepareNewInspectionReportReq(InspectionReportReq req) {
        InspectionReportDtlsNew details = new InspectionReportDtlsNew();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(req.getProcessId());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private InspectionReportDtls prepareInspectionReportReq(InspectionReportReq req) {
        InspectionReportDtls details = new InspectionReportDtls();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(req.getProcessId());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private void validateInspectionReport(InspectionReportReq req) {
        if (Objects.isNull(req.getDateOfInspectionDate())) {
            //throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getDateOfInspectionDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDateOfInspection(date);
        }

        if (Objects.isNull(req.getDateOfDeliveryDate()) || req.getDateOfDeliveryDate().isEmpty()) {
            //   throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setDateOfDelivery(null);
        } else {
            Date date = CommonUtils.getDate(req.getDateOfDeliveryDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDateOfDelivery(date);
        }

        if (Objects.isNull(req.getInspectionRptDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getInspectionRptDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setInspectionRptDt(date);
        }

        validateCommonProcessData(req);
    }

    private AcceptRejectDtls prepareAcceptRejectDtls(AcceptRejectNoteReq req) {
        AcceptRejectDtls details = new AcceptRejectDtls();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(req.getProcessId());
        details.setAcptRejNoteDT(req.getAcptRejNodeDTDate());
        details.setNoaDate(req.getNoaDateDt());
        details.setDateOfDelivery(req.getDateOfDeliveryDt());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private void validateAcceptRejectNoteData(AcceptRejectNoteReq req) {
        if (Objects.isNull(req.getAcptRejNodeDT())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getAcptRejNodeDT(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setAcptRejNodeDTDate(date);
        }

        if (Objects.isNull(req.getDateOfDelivery()) || req.getDateOfDelivery().isEmpty()) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setDateOfDeliveryDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getDateOfDelivery(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDateOfDeliveryDt(date);
        }

        if (Objects.isNull(req.getNoaDate()) || req.getNoaDate().isEmpty()) {
            //throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setNoaDateDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getNoaDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setNoaDateDt(date);
        }

        validateCommonProcessData(req);
    }

    private GRNDtls prepareGRNDtls(GRNReq req) {
        GRNDtls details = new GRNDtls();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(CommonUtils.getLong(req.getProcessId()));
        details.setGrnDate(req.getGrnDateDt());
        details.setDateOfDelivery(req.getDateOfDeliveryDt());
        details.setNoaDate(req.getNoaDateDt());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private void validateGRNData(GRNReq req) {
        if (Objects.isNull(req.getGrnDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getGrnDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setGrnDateDt(date);
        }

        if (Objects.isNull(req.getDateOfDelivery()) || req.getDateOfDelivery().isEmpty()) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setDateOfDeliveryDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getDateOfDelivery(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDateOfDeliveryDt(date);
        }

        if (Objects.isNull(req.getNoaDate()) || req.getNoaDate().isEmpty()) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setNoaDateDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getNoaDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setNoaDateDt(date);
        }

        validateCommonProcessData(req);
    }

    private ReturnNoteDetails prepareReturnNoteDtls(ReturnNoteFormReq req) {
        ReturnNoteDetails details = new ReturnNoteDetails();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(CommonUtils.getLong(req.getProcessId()));
        details.setReturnNoteDt(req.getReturnNoteDtDate());
        details.setIssueNoteDt(req.getIssueNoteDtDate());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private void validateReturnNoteData(ReturnNoteFormReq req) {
        if (Objects.isNull(req.getReturnNoteDt())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getReturnNoteDt(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setReturnNoteDtDate(date);
        }
        if (Objects.isNull(req.getIssueNoteDt())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getIssueNoteDt(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setIssueNoteDtDate(date);
        }

        validateCommonProcessData(req);
    }

    private GatePassDtls prepareGatePassDtls(GatePassReq req) {
        GatePassDtls details = new GatePassDtls();
        BeanUtils.copyProperties(req, details);
        details.setProcessId(CommonUtils.getLong(req.getProcessId()));
        details.setGatePassDate(req.getGatePassDateDt());
        details.setNoaDate(req.getNoaDateDt());
        details.setDateOfDelivery(req.getDateOfDeliveryDt());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private ProcessDtls validateAndUpdateProcessStage(String processId, String currentStage, String nextStage, String userName, Long orgId) {
        Long aLong = CommonUtils.getLong(processId);
        if (aLong == null) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, ErrorResponseConstant.INVALID_PROCESS_ID_MSG);
        }
        Optional<ProcessDtls> byId = processDtlsRepository.findById(aLong);
        if (!byId.isPresent()) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, ErrorResponseConstant.INVALID_PROCESS_ID_MSG);
        }

      /*  if (byId.get().getOrgId().compareTo(orgId) != 0) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ORG_ID, ErrorResponseConstant.INVALID_PROCESS_ORG_ID_MSG);
        }*/
        /*if (!currentStage.equalsIgnoreCase(byId.get().getProcessStage()) && !nextStage.equalsIgnoreCase(byId.get().getProcessStage())) {
            throw new ValidationException(ErrorResponseConstant.CURR_PROCESS_STAGE_INVALID, (ErrorResponseConstant.CURR_PROCESS_STAGE_INVALID_MSG));
        }*/
        if (!"A".equalsIgnoreCase(byId.get().getStatus())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_ID, ErrorResponseConstant.INVALID_PROCESS_ID_MSG);
        }

        ProcessDtls processDtls = byId.get();
        /*if (ProcessTypeEnum.IRP.name().equalsIgnoreCase(processDtls.getProcessType()) && ProcessStageEnum.GRN.name().equalsIgnoreCase(byId.get().getProcessStage())) {
            throw new ValidationException(ErrorResponseConstant.CURR_PROCESS_STAGE_COMPLETED_CD, (ErrorResponseConstant.CURR_PROCESS_STAGE_COMPLETED_MSG));
        }*/
        processDtls.setStatus("A");
        processDtls.setProcessStage(nextStage);
        processDtls.setUpdateDate(new Date());
        processDtls.setUpdateUser(userName);
        return processDtls;
    }

    private void validateGatePassData(GatePassReq req) {
        if (Objects.isNull(req.getGatePassDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getGatePassDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setGatePassDateDt(date);
        }
        if (Objects.isNull(req.getNoaDate()) || req.getNoaDate().isEmpty()) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setNoaDateDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getNoaDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setNoaDateDt(date);
        }

        if (Objects.isNull(req.getGatePassDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getGatePassDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setOgpDateDt(date);
        }

        if (Objects.isNull(req.getDateOfDelivery()) || req.getDateOfDelivery().isEmpty()) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
            req.setDateOfDeliveryDt(null);
        } else {
            Date date = CommonUtils.getDate(req.getDateOfDelivery(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDateOfDeliveryDt(date);
        }

        if (CommonUtils.isBlank(req.getGatePassNo())) {
            throw new ValidationException(ErrorResponseConstant.GATE_PASS_NOT_EMPTY_CD, ErrorResponseConstant.GATE_PASS_NOT_EMPTY_MSG);
        }
        validateCommonProcessData(req);
    }

    private void validateIssueNoteReq(IssueNoteFormReq req) {
        if (Objects.isNull(req.getDemandNoteDt())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getDemandNoteDt(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setDemandNoteDateDt(date);
        }
        if (Objects.isNull(req.getIssueNoteDt())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getIssueNoteDt(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_DATE_MSG));
            }
            req.setIssueNoteDateDt(date);
        }
        if (!InterProcessTypeEnum.INTER_ORG.name().equalsIgnoreCase(req.getType()) && CommonUtils.isBlank(req.getDemandNoteNo())) {
            throw new ValidationException(ErrorResponseConstant.DEMAND_NOT_EMPTY_CD, (ErrorResponseConstant.DEMAND_NOT_EMPTY_MSG));
        }

        if (CommonUtils.isBlank(req.getIssueNoteNo())) {
            throw new ValidationException(ErrorResponseConstant.ISSUE_DT_NOT_EMPTY_CD, (ErrorResponseConstant.ISSUE_DT_NOT_EMPTY_MSG));
        }

        String processType = req.getProcessType();
        if (CommonUtils.isBlank(req.getDemandNoteNo()) ||
                (!processType.equalsIgnoreCase(ProcessTypeEnum.IOP.name()) && !processType.equalsIgnoreCase(ProcessTypeEnum.PO.name()) && !processType.equalsIgnoreCase(ProcessTypeEnum.IRP.name()) && !processType.equalsIgnoreCase(ProcessTypeEnum.NIRP.name()) && !processType.equalsIgnoreCase(ProcessTypeEnum.CRN.name()))) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_TYPE, (ErrorResponseConstant.INVALID_PROCESS_TYPE_MSG));
        }

        validateCommonProcessData(req);
    }

    private void validateCommonProcessData(ProcessBaseReq req) {
        if (CommonUtils.isBlank(req.getGenName())) {
            throw new ValidationException(ErrorResponseConstant.GEN_NAME_NOT_EMPTY_CD, ErrorResponseConstant.GEN_NAME_NOT_EMPTY_MSG);
        }
        if (CommonUtils.isBlank(req.getApprovedName())) {
            throw new ValidationException(ErrorResponseConstant.APR_NAME_NOT_EMPTY_CD, ErrorResponseConstant.APR_NAME_NOT_EMPTY_MSG);
        }
        if (CommonUtils.isBlank(req.getIssueName())) {
            //throw new ValidationException(ErrorResponseConstant.ISS_NAME_NOT_EMPTY_CD, ErrorResponseConstant.ISS_NAME_NOT_EMPTY_MSG);
        }
        if (Objects.isNull(req.getGenDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getGenDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_GEN_DATE_MSG));
            }
            req.setGenDateDt(date);
        }
        if (Objects.isNull(req.getIssueDate())) {
            // throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getIssueDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_ISSUE_DATE_MSG));
            }
            req.setIssueDateDt(date);
        }
        if (Objects.isNull(req.getApprovedDate())) {
            throw new ValidationException(ErrorResponseConstant.DATE_EMPTY_CD, (ErrorResponseConstant.DATE_EMPTY_MSG));
        } else {
            Date date = CommonUtils.getDate(req.getApprovedDate(), ApplicationConstant.DATE_FORMAT);
            if (date == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, (ErrorResponseConstant.INVALID_APPR_DATE_MSG));
            }
            req.setApprovedDateDt(date);
        }
    }

    private List<ProcessItemMap> prepareProcessItemMap(List<ItemDetailsForm> items, ProcessDtls processDtls, String userId, String processStage, Long orgId, Long uniqueId) {
        if (items == null || items.isEmpty()) {
            throw new ValidationException(ErrorResponseConstant.ITEM_CANNOT_EMPTY_CD, ErrorResponseConstant.ITEM_CANNOT_EMPTY);
        }
        List<ProcessItemMap> processItemMaps = new ArrayList<>();
        for (ItemDetailsForm frm : items) {
            if (CommonUtils.getLong(frm.getLocatorId()) == null || !locatorMasterRepository.findById(CommonUtils.getLong(frm.getLocatorId())).isPresent()) {
                throw new ValidationException(ErrorResponseConstant.INVALID_LOCATOR_CD, ErrorResponseConstant.INVALID_LOCATOR_ID_MSG + " - " + frm.getLocatorId());
            }

            if ((ProcessStageEnum.ISN.name().equalsIgnoreCase(processStage) || ProcessStageEnum.GRN.name().equalsIgnoreCase(processStage)) && !masterDao.validateLocatorIdAndOrgId(CommonUtils.getLong(frm.getLocatorId()), orgId)) {
                throw new ValidationException(ErrorResponseConstant.INVALID_LOCATOR_ORG_CD, (ErrorResponseConstant.INVALID_LOCATOR_ORG_MSG + " " + frm.getItemCode()));
            }

            ItemCodeMaster itemMaster = null;
            Optional<ItemCodeMaster> byId = itemMasterRepository.findAllByItemMasterCdAndLocatorIdAndStatus(frm.getItemCode(), CommonUtils.getLong(frm.getLocatorId()), "A");
            if (byId.isPresent()) {
                itemMaster = byId.get();

            }
            if (itemMaster == null && !ProcessStageEnum.GRN.name().equalsIgnoreCase(processStage)) {
                throw new ValidationException(ErrorResponseConstant.ITEM_CD_NOT_EXIST_CD, (ErrorResponseConstant.ITEM_CD_NOT_EXIST + " " + frm.getItemCode()));
            }
            if (ProcessStageEnum.IRN.name().equalsIgnoreCase(processStage)) {
                if (frm.getInspectedQuantity() <= 0) {
                    throw new ValidationException(ErrorResponseConstant.INSP_QNTY_LESS_THAN_ONE_CD, ErrorResponseConstant.INSP_QNTY_LESS_THAN_ONE_MSG);
                } else if (frm.getInspectedQuantity() != (frm.getAcceptedQuantity() + frm.getRejectedQuantity())) {
                    throw new ValidationException(ErrorResponseConstant.QNTY_SUM_INVALID_CD, ErrorResponseConstant.QNTY_SUM_INVALID_MSG);
                }
            } else {
                if (frm.getQuantity() <= 0) {
                    throw new ValidationException(ErrorResponseConstant.QNTY_LESS_THAN_ONE_CD, ErrorResponseConstant.QNTY_LESS_THAN_ONE_MSG);
                }
            }
            if (ProcessStageEnum.ISN.name().equalsIgnoreCase(processStage) && frm.getQuantity() > itemMaster.getQuantity()) {
                String errorMsg = (ErrorResponseConstant.ITEM_QNTY_GREAT_MSG + " " + frm.getItemCode() + ". Available quantity is " + itemMaster.getQuantity());
                //  throw new ValidationException(ErrorResponseConstant.ITEM_QNTY_GREAT_CD, errorMsg);
            }

            if (!ProcessStageEnum.GRN.name().equalsIgnoreCase(processStage) && !ProcessStageEnum.IRN.name().equalsIgnoreCase(processStage) && !ProcessTypeEnum.NIRP.name().equalsIgnoreCase(processDtls.getProcessType()) && frm.getNoOfDays() <= 0) {
                throw new ValidationException(ErrorResponseConstant.DAYS_THAN_ONE_CD, ErrorResponseConstant.DAYS_THAN_ONE_MSG);
            }

            ProcessItemMap processItemMap = new ProcessItemMap();
            processItemMap.setsNo((long) frm.getSrNo());
            processItemMap.setItemCode(frm.getItemCode());
            processItemMap.setItemDesc(frm.getItemDesc());
            processItemMap.setProcessType(processDtls.getProcessType());
            processItemMap.setProcessId(processDtls.getId());
            processItemMap.setQuantity(frm.getQuantity());
            processItemMap.setRemarks(frm.getRemarks());
            processItemMap.setUom(frm.getUom());
            processItemMap.setRequiredDays(frm.getNoOfDays());
            processItemMap.setCreateDate(new Date());
            processItemMap.setCreateUser(userId);
            processItemMap.setProcessStage(processStage);
            processItemMap.setBudgetHeadProcurement(frm.getBudgetHeadProcurement());
            processItemMap.setConditionOfGoods(frm.getConditionOfGoods());
            processItemMap.setLocatorId(frm.getLocatorId());
            processItemMap.setAcceptedQuantity(frm.getAcceptedQuantity());
            processItemMap.setInspectedQuantity(frm.getInspectedQuantity());
            processItemMap.setRejectedQuantity(frm.getRejectedQuantity());
            processItemMap.setItemId(frm.getItemId());
            processItemMap.setUnitPrice(frm.getUnitPrice() == null ? null : frm.getUnitPrice().doubleValue());
            processItemMap.setUniqueId(uniqueId);
            processItemMaps.add(processItemMap);
        }

        return processItemMaps;
    }

    private IssueNoteDetails prepareIssueNoteDtls(IssueNoteFormReq req, ProcessDtls processDtls) {

        IssueNoteDetails details = new IssueNoteDetails();
        BeanUtils.copyProperties(req, details);
        details.setDemandNoteDt(req.getDemandNoteDateDt());
        details.setIssueNoteDt(req.getIssueNoteDateDt());
        details.setProcessId(processDtls.getId());
        details.setCreateDate(new Date());
        details.setCreateUser(req.getUserId());
        details.setProcessId(processDtls.getId());
        details.setApprovedDate(req.getApprovedDateDt());
        details.setApprovedName(req.getApprovedName());
        details.setIssueDate(req.getIssueDateDt());
        details.setIssueName(req.getIssueName());
        details.setGenDate(req.getGenDateDt());
        details.setGenName(req.getGenName());
        return details;
    }

    private ProcessDtls prepareProcessDetails(String processType, String userId, String processStage, Long orgId) {
        ProcessDtls processDtls = new ProcessDtls();
        processDtls.setProcessType(processType);
        processDtls.setStatus("A");
        processDtls.setCreateDate(new Date());
        processDtls.setCreateUser(userId);
        processDtls.setProcessStage(processStage);
        processDtls.setOrgId(orgId);
        return processDtls;
    }

    private ItemCodeMasterHist getHistoryData(ItemCodeMaster i, String createUser, Long processId, String processStage, Long orgId) {
        ItemCodeMasterHist hist = new ItemCodeMasterHist();
        BeanUtils.copyProperties(i, hist);
        hist.setId(null);
        hist.setItemMasterId(i.getId());
        hist.setCreateDate(new Date());
        hist.setProcessStage(processStage);
        hist.setProcessId(processId);
        hist.setCreateUser(createUser);
        hist.setPreQuantity(i.getQuantity());
        hist.setInitQuantity(i.getInitQuantity());
        hist.setTotalPreQuantity(masterDao.getTotalQuantityOfItemInOrg(i.getItemMasterCd(), orgId));
        return hist;
    }


    private List<ProcessItemMap> getBalanceItemsForISN(Long processId) {
    List<ProcessItemMap> isnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.ISN.name());
    List<ProcessItemMap> rnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.RN.name());
    
    if (isnItems == null || isnItems.isEmpty()) {
        return isnItems;
    }
    
    if (rnItems == null || rnItems.isEmpty()) {
        return isnItems;
    }

    // Create a map of returned quantities by itemCode+locatorId
    Map<String, Double> returnedQuantities = new HashMap<>();
    for (ProcessItemMap rnItem : rnItems) {
        String key = rnItem.getItemCode() + "_" + rnItem.getLocatorId();
        Double currentReturned = returnedQuantities.getOrDefault(key, 0.0);
        returnedQuantities.put(key, currentReturned + (rnItem.getQuantity() == null ? 0.0 : rnItem.getQuantity()));
    }

    // Subtract returned quantities from issued quantities
    for (ProcessItemMap isnItem : isnItems) {
        String key = isnItem.getItemCode() + "_" + isnItem.getLocatorId();
        Double issuedQty = isnItem.getQuantity() == null ? 0.0 : isnItem.getQuantity();
        Double returnedQty = returnedQuantities.getOrDefault(key, 0.0);
        Double balanceQty = issuedQty - returnedQty;
        isnItem.setQuantity(balanceQty);
    }
    
    return isnItems;
}

    // private List<ProcessItemMap> getBalanceItemsForRN(Long processId) {
    //     // List<ProcessItemMap> leftItems = new ArrayList<>();
    //     List<ProcessItemMap> rnnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.RN.name());
    //     List<ProcessItemMap> grnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.GRN.name());
    //     if (rnnItems == null || rnnItems.isEmpty() || grnItems == null || grnItems.isEmpty()) {
    //         return rnnItems;
    //     }

    //     if (rnnItems.size() > 1) {
    //         Double sum = 0d;
    //         for (ProcessItemMap rnItem : rnnItems) {
    //             sum = sum + rnItem.getQuantity();
    //         }
    //         ProcessItemMap processItemMap = rnnItems.get(0);
    //         processItemMap.setQuantity(sum);
    //         rnnItems.clear();
    //         rnnItems.add(processItemMap);
    //     }

    //     for (ProcessItemMap rnItem : rnnItems) {
    //         for (ProcessItemMap grnItem : grnItems) {
    //             Double quantityRn = rnItem.getQuantity() == null ? 0d : rnItem.getQuantity();
    //             if (grnItems.contains(rnItem)) {
    //                 Double quantityGrn = grnItem.getQuantity() == null ? 0d : grnItem.getQuantity();
    //                 Double newQty = quantityRn - quantityGrn;
    //                 rnItem.setQuantity(newQty);
    //             }
    //             //   leftItems.add(rnItem);
    //         }

    //     }
    //     return rnnItems;
    // }

    private List<ProcessItemMap> getBalanceItemsForRN(Long processId) {
    List<ProcessItemMap> rnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.RN.name());
    List<ProcessItemMap> grnItems = processItemMapRepository.findAllByProcessIdAndProcessStage(processId, ProcessStageEnum.GRN.name());
    
    if (rnItems == null || rnItems.isEmpty()) {
        return rnItems;
    }
    
    // Step 1: Consolidate multiple RN entries for the same item (by itemCode + locatorId)
    Map<String, ProcessItemMap> consolidatedRnItems = new HashMap<>();
    for (ProcessItemMap rnItem : rnItems) {
        String key = rnItem.getItemCode() + "_" + rnItem.getLocatorId();
        
        if (consolidatedRnItems.containsKey(key)) {
            // Item already exists - add quantities
            ProcessItemMap existing = consolidatedRnItems.get(key);
            Double existingQty = existing.getQuantity() == null ? 0.0 : existing.getQuantity();
            Double newQty = rnItem.getQuantity() == null ? 0.0 : rnItem.getQuantity();
            existing.setQuantity(existingQty + newQty);
        } else {
            // First occurrence of this item - add to map
            consolidatedRnItems.put(key, rnItem);
        }
    }
    
    // Step 2: If no GRN items yet, return all consolidated RN items
    if (grnItems == null || grnItems.isEmpty()) {
        return new ArrayList<>(consolidatedRnItems.values());
    }

    // Step 3: Create a map of GRN quantities by itemCode + locatorId
    Map<String, Double> grnQuantities = new HashMap<>();
    for (ProcessItemMap grnItem : grnItems) {
        String key = grnItem.getItemCode() + "_" + grnItem.getLocatorId();
        Double currentGrn = grnQuantities.getOrDefault(key, 0.0);
        Double grnQty = grnItem.getQuantity() == null ? 0.0 : grnItem.getQuantity();
        grnQuantities.put(key, currentGrn + grnQty);
    }

    // Step 4: Subtract GRN quantities from consolidated RN quantities
    for (ProcessItemMap rnItem : consolidatedRnItems.values()) {
        String key = rnItem.getItemCode() + "_" + rnItem.getLocatorId();
        Double returnedQty = rnItem.getQuantity() == null ? 0.0 : rnItem.getQuantity();
        Double grnQty = grnQuantities.getOrDefault(key, 0.0);
        Double balanceQty = returnedQty - grnQty;
        rnItem.setQuantity(balanceQty);
    }
    
    return new ArrayList<>(consolidatedRnItems.values());
}

    @Transactional
    @Override
    public Object doCorrectionProcess(CorrectionProcessReq req) {
        CorrectionProcessRes res = new CorrectionProcessRes();

        if (req.getIssueNoteDtls() != null) {
            IssueNoteFormReq issueNoteDtls = req.getIssueNoteDtls();
            issueNoteDtls.setCorrectionProcessFlag("Y");
            issueNoteDtls.setUserId(req.getUserId());
            issueNoteDtls.setOrgId(req.getOrgId());
            issueNoteDtls.setProcessType(ProcessTypeEnum.CRN.name());
            IssueNoteFormRes issueNoteFormRes = (IssueNoteFormRes) saveIssueNote(issueNoteDtls);
            res.setIssueNoteResponse(issueNoteFormRes);
        }

        if (req.getGrnDtls() != null) {
            GRNReq grnDtls = req.getGrnDtls();
            grnDtls.setUserId(req.getUserId());
            grnDtls.setOrgId(req.getOrgId());
            grnDtls.setCorrectionProcessFlag("Y");
            ProcessFormRes grnResponse = (ProcessFormRes) saveGRN(grnDtls);
            res.setGrnResponse(grnResponse);
        }
        return res;
    }
}
