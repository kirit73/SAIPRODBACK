package com.sai.inventory.service.impl;

import com.sai.inventory.dao.DashboardDao;
import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.domain.ProcessItemMapDomain;
import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.dashboard.request.GetFNSCategoryReq;
import com.sai.inventory.domain.dashboard.request.IssueNoteDataReq;
import com.sai.inventory.domain.dashboard.request.PurchasingSummaryReq;
import com.sai.inventory.domain.dashboard.response.FNSCategoryRes;
import com.sai.inventory.domain.dashboard.response.TotalValueOfAllItemResp;
import com.sai.inventory.domain.process.response.GetSubProcessDtlsRes;
import com.sai.inventory.domain.process.response.IssueNoteDetailsResponse;
import com.sai.inventory.domain.process.response.ProcessBaseResponse;
import com.sai.inventory.entity.*;
import com.sai.inventory.entity.internal.FNSCategoryEntity;
import com.sai.inventory.entity.internal.TxnSummaryResp;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.*;
import com.sai.inventory.service.DashboardService;
import com.sai.inventory.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardDao dashboardDao;
    @Autowired
    private IssueNoteDtlsRepository demandDtlsRepository;

    @Autowired
    private ProcessDtlsRepository processDtlsRepository;
    @Autowired
    private ProcessItemMapRepository processItemMapRepository;
    @Autowired
    private ReturnNoteDtlsRepository returnNoteDtlsRepository;
    @Autowired
    private MasterDao masterDao;
    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Override
    public List<FNSCategoryRes> getFNSCategory(GetFNSCategoryReq req) {
        List<FNSCategoryRes> response = new ArrayList<>();
        List<FNSCategoryEntity> fnsCategory = dashboardDao.getFNSCategory(req);
        if (fnsCategory != null) {
            for (FNSCategoryEntity itemCodeMaster : fnsCategory) {
                FNSCategoryRes res = new FNSCategoryRes();
                res.setFnsCategory(CommonUtils.getGetFNSCategory(itemCodeMaster.getCreateDt()));
                res.setItemCode(itemCodeMaster.getId().getItemMasterCd());
                res.setItemDescription(itemCodeMaster.getItemMasterDesc());
                res.setSubCategory(itemCodeMaster.getSubCategory());
                res.setLastTxnDate(CommonUtils.formatDate(itemCodeMaster.getCreateDt()));
                res.setSubCategoryDesc(itemCodeMaster.getSubCategoryDesc());
                res.setOrgId(itemCodeMaster.getOrgId());
                response.add(res);
            }
        }
        return response;
    }

    public Object getDashboardIssueNoteData(IssueNoteDataReq req) {
        req = validateRequest(req);
        List<GetSubProcessDtlsRes> responseList = new ArrayList<>();
        List<IssueNoteDetails> issueNoteList = dashboardDao.findAllByDtls(req);
        if (!issueNoteList.isEmpty()) {
            for (IssueNoteDetails details : issueNoteList) {
                GetSubProcessDtlsRes response = new GetSubProcessDtlsRes();
                response.setProcessData(prepareResponse(details));

                List<ProcessItemMapDomain> domainList = new ArrayList<>();
                List<ProcessItemMap> allByProcessId = processItemMapRepository.findAllByProcessIdAndProcessStage(details.getProcessId(), ProcessStageEnum.ISN.name());
                int maxDays = Integer.MIN_VALUE;
                for (ProcessItemMap im : allByProcessId) {
                    ProcessItemMapDomain domain = new ProcessItemMapDomain();
                    BeanUtils.copyProperties(im, domain);
                    domainList.add(domain);
                    if (maxDays < im.getRequiredDays()) {
                        maxDays = im.getRequiredDays();
                    }
                }
                if (req.isPendingReturn()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(details.getCreateDate());
                    calendar.add(Calendar.DAY_OF_YEAR, maxDays);
                    if (new Date().before(calendar.getTime())) {
                        continue;
                    } else {
                        List<ReturnNoteDetails> rnList = returnNoteDtlsRepository.findAllByProcessId(details.getProcessId());
                        if (!rnList.isEmpty()) {
                            continue;
                        }
                    }
                }

                response.setItemList(domainList);
                response.setProcessStage(ProcessStageEnum.ISN.name());
                response.setProcessId(details.getProcessId());

                responseList.add(response);
            }
        }
        return responseList;
    }

    @Override
    public Object getTotalValueOfAllItem(OrgIdRequest req) {
        TotalValueOfAllItemResp resp = new TotalValueOfAllItemResp();
        List<ItemCodeMaster> allActiveItemByOrgId = null;
        if(req.getOrgId() != null && req.getOrgId() > 0) {
            allActiveItemByOrgId = masterDao.getAllActiveItemByOrgId(req.getOrgId());
        }else{
            allActiveItemByOrgId = itemMasterRepository.findAllByStatus("A");
        }
        BigDecimal avg = BigDecimal.ZERO;
        for (ItemCodeMaster item : allActiveItemByOrgId) {
            BigDecimal price = item.getPrice() == null ? BigDecimal.ZERO : item.getPrice();
            BigDecimal quantity = item.getQuantity() == null ? BigDecimal.ZERO : new BigDecimal(item.getQuantity());
            avg = avg.add(price.multiply(quantity));
        }
        resp.setTotalValue(avg.setScale(2, RoundingMode.HALF_UP));
        resp.setTotalItems(new BigDecimal(allActiveItemByOrgId.size()));
        return resp;
    }

    @Override
    public List<TxnSummaryResp> getPurchaseSummary(PurchasingSummaryReq req) {
        if (CommonUtils.isNotBlank(req.getStartDate()) && CommonUtils.isNotBlank(req.getEndDate())) {
            Date date1 = CommonUtils.getDate(req.getStartDate(), ApplicationConstant.DATE_FORMAT);
            Date date2 = CommonUtils.getDate(req.getEndDate(), ApplicationConstant.DATE_FORMAT);
            Date startDT = CommonUtils.changeDateToDayStart(date1);
            Date endDT = CommonUtils.changeDateToDayENd(date2);
            if (startDT == null || endDT == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
            } else if(startDT.after(endDT)){
                throw new ValidationException(ErrorResponseConstant.FROM_TO_DATE_INVALID_CD, ErrorResponseConstant.FROM_TO_DATE_INVALID_MSG);
            }
            req.setStartDateDt(startDT);
            req.setEndDateDt(endDT);
        } else {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }

        List<ProcessDtls> txnSummary = dashboardDao.getPurchaseSummary(req);
        List<TxnSummaryResp> res = new ArrayList<>();
        if (txnSummary != null) {
            for (ProcessDtls r : txnSummary) {
                TxnSummaryResp resp = new TxnSummaryResp();
                BeanUtils.copyProperties(r, resp);
                resp.setTxnDate(CommonUtils.formatDate(r.getCreateDate()));
                resp.setGeneratedBy(r.getCreateUser());
                BigDecimal totalValue = BigDecimal.ZERO;
                List<ProcessItemMap> items = null;
                if(StringUtils.isBlank(req.getItemCode())) {
                    items = processItemMapRepository.findAllByProcessIdAndProcessStage(r.getId(), ProcessStageEnum.GRN.name());
                }else {
                    items = processItemMapRepository.findAllByProcessIdAndProcessStageAndItemCode(r.getId(), ProcessStageEnum.GRN.name(), req.getItemCode());
                }
                if (items != null) {
                    for (ProcessItemMap i : items) {
                        BigDecimal quantity = i.getQuantity() == null ? BigDecimal.ZERO : new BigDecimal(i.getQuantity());
                        Double it = i.getUnitPrice();
                        BigDecimal price = it == null ? BigDecimal.ZERO : new BigDecimal(it);
                        totalValue = totalValue.add((price.multiply(quantity)));
                        totalValue = totalValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                       /* List<ItemCodeMaster> itemCodes = masterDao.getAllActiveItemByOrgId(i.getItemCode(), req.getOrgId());
                        if (itemCodes != null && !itemCodes.isEmpty()) {
                            ItemCodeMaster it = itemCodes.get(0);
                            BigDecimal price = it.getPrice() == null ? BigDecimal.ZERO : it.getPrice();
                            totalValue = totalValue.add((price.multiply(quantity)));

                        }*/
                    }
                }
                resp.setTotalValue(totalValue);

                res.add(resp);
            }
        }
        return res;
    }

    private IssueNoteDataReq validateRequest(IssueNoteDataReq req) {
        if (!ProcessTypeEnum.IRP.name().equalsIgnoreCase(req.getType()) && !ProcessTypeEnum.NIRP.name().equalsIgnoreCase(req.getType())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PROCESS_TYPE, ErrorResponseConstant.INVALID_PROCESS_TYPE_ISN_MSG);
        }

        if (!ProcessTypeEnum.IRP.name().equalsIgnoreCase(req.getType()) && req.isPendingReturn()) {
            throw new ValidationException(101, "Pending Return true is allowed for IRP case only");
        }

        if (CommonUtils.isNotBlank(req.getStartDate()) && CommonUtils.isNotBlank(req.getEndDate())) {
            Date date1 = CommonUtils.getDate(req.getStartDate(), ApplicationConstant.DATE_FORMAT);
            Date date2 = CommonUtils.getDate(req.getEndDate(), ApplicationConstant.DATE_FORMAT);
            if (date1 == null || date2 == null) {
                throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
            }

            Date startDT = CommonUtils.changeDateToDayStart(date1);
            Date endDT = CommonUtils.changeDateToDayENd(date2);
            if (startDT.after(endDT)) {
                throw new ValidationException(ErrorResponseConstant.FROM_TO_DATE_INVALID_CD, ErrorResponseConstant.FROM_TO_DATE_INVALID_MSG);
            }
            req.setStartDateDt(startDT);
            req.setEndDateDt(endDT);
        } else {
            throw new ValidationException(ErrorResponseConstant.INVALID_DATE_CD, ErrorResponseConstant.INVALID_DATE_MSG);
        }

        return req;
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
}
