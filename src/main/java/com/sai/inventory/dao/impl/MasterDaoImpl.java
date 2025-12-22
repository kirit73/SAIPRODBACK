package com.sai.inventory.dao.impl;

import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.domain.master.request.ItemMasterReq;
import com.sai.inventory.domain.master.request.OHQRequest;
import com.sai.inventory.domain.process.request.TxnSummaryReq;
import com.sai.inventory.entity.ItemCodeMaster;
import com.sai.inventory.entity.ItemNameMaster;
import com.sai.inventory.entity.LocatorMaster;
import com.sai.inventory.entity.ProcessDtls;
import com.sai.inventory.entity.internal.OHQDataEntity;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.ItemNameRepository;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MasterDaoImpl implements MasterDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemNameRepository itemNameRepository;

    @Override
    public List<OHQDataEntity> getOHQData(OHQRequest request) {
        Map<String, Object> paramMap = new HashMap();

        String query = "select im.ITEM_MASTER_ID, im.ITEM_MASTER_CD, im.ITEM_MASTER_DESC, um.UOM_ID, um.UOM_NAME, lm.LOCATOR_MASTER_ID , lm.LOCATOR_DESC, im.QUANTITY, im.LOCATION_ID , lam.LOCATION_NAME, im.PRICE, IM.SUBCATEGORY, (select sub_category_desc from sub_category_mst where category_cd = IM.CATEGORY and sub_category_code =  IM.SUBCATEGORY LIMIT 1) SUBCATEGORY_DESC from item_master im "
                +
                "           left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "           left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "           left join uom_master um  on im.UOM_ID = um.UOM_ID " +
                "           where  im.status = 'A' ";
        if (CommonUtils.isNotBlank(request.getItemCode())) {
            query = query + " and im.ITEM_MASTER_CD  =:itemCode ";
            paramMap.put("itemCode", request.getItemCode());
        }
        if (CommonUtils.isNotBlank(request.getItemDesc())) {
            query = query + " and UPPER(im.ITEM_MASTER_DESC)  =:itemDesc ";
            paramMap.put("itemDesc", request.getItemDesc().toUpperCase());
        }
        if (request.getOrgId() != null) {
            query = query + " and lam.org_id =:orgId";
            paramMap.put("orgId", request.getOrgId());
        }

        Query query1 = entityManager.createNativeQuery(query, OHQDataEntity.class);
        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                query1.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return query1.getResultList();
    }

    @Override
    public List getTxnSummary(TxnSummaryReq request) {
        System.out.println("txn symmary callleddd");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");

        if (CommonUtils.isNotBlank(request.getItemCode()) && request.getStartDateDt() != null
                && request.getEndDateDt() != null && CommonUtils.isNotBlank(request.getTxnType())) {
            switch (request.getTxnType()) {
                case "ISN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from issue_note_dtls ) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from issue_note_dtls where ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd)");

                    break;
                case "OGP":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'OUT') and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'OUT' and ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");

                    break;
                case "IGP":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'IN') and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'IN' and ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "RN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from return_note_dtls) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from return_note_dtls gd  where regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "GRN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from grn_dtls) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from grn_dtls gd  where ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "ACT":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'ACCEPTANCE') and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'ACCEPTANCE' and ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "REJ":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'REJECTION') and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'REJECTION' and ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "IR":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_rpt_dtls) and process_id in (select  process_id from process_item_map where item_cd =:itemCd)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_rpt_dtls where ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");

                    break;
                case "IRN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_new_rpt_dtls) and process_id in (select  process_id from process_item_map where item_cd =:itemCd)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_new_rpt_dtls where ce_regional_center_cd =:orgId) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                case "PO":
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_type = 'PO' and process_id in (select  process_id from grn_dtls) and process_id in (select  process_id from process_item_map where item_cd =:itemCd) ");
                    break;
                default:
                    throw new ValidationException(ErrorResponseConstant.INVALID_TXN_TYPE_CD,
                            ErrorResponseConstant.INVALID_TXN_TYPE_MSG);
            }
            return entityManager.createNativeQuery(stringBuilder.toString(), ProcessDtls.class)
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .setParameter("itemCd", request.getItemCode())
                    .getResultList();
        }
        else if (CommonUtils.isNotBlank(request.getTxnType()) && request.getStartDateDt() != null
                && request.getEndDateDt() != null) {
            switch (request.getTxnType()) {

                case "ISN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from issue_note_dtls )");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from issue_note_dtls where ce_regional_center_cd =:orgId)");
                    break;
                case "OGP":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'OUT')");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'OUT' and ce_regional_center_cd =:orgId)");

                    break;
                case "IGP":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'IN')");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where gate_type = 'IN' and ce_regional_center_cd =:orgId)");
                    break;
                case "RN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from return_note_dtls)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from return_note_dtls where regional_center_cd =:orgId)");
                    break;
                case "GRN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from grn_dtls)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from grn_dtls gd  where ce_regional_center_cd =:orgId)");
                    break;
                case "ACT":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'ACCEPTANCE')");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'ACCEPTANCE' and ce_regional_center_cd =:orgId)");
                    break;
                case "REJ":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'REJECTION')");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from acpt_rej_note_dtls where type_of_note = 'REJECTION' and ce_regional_center_cd =:orgId)");
                    break;
                case "IR":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_rpt_dtls)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_rpt_dtls where ce_regional_center_cd =:orgId)");

                    break;
                case "IRN":
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("select * from process_details where status = 'A' and org_id =:orgId");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_new_rpt_dtls)");
                    stringBuilder.append(" union ");
                    stringBuilder.append("select * from process_details where status = 'A' ");
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from inspection_new_rpt_dtls where ce_regional_center_cd =:orgId)");

                    break;
                case "PO":
                    stringBuilder.append(
                            " and create_dt >=:startDate and create_dt <=:endDate and process_type = 'PO' and process_id in (select  process_id from grn_dtls)");
                    break;
                default:
                    throw new ValidationException(ErrorResponseConstant.INVALID_TXN_TYPE_CD,
                            ErrorResponseConstant.INVALID_TXN_TYPE_MSG);

            }
            return entityManager.createNativeQuery(stringBuilder.toString(), ProcessDtls.class)
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }

        else if (CommonUtils.isNotBlank(request.getItemCode()) && request.getStartDateDt() != null
                && request.getEndDateDt() != null) {
            return getTxnSummaryForCdSDateEDateOrgId(request);
        } else if (request.getStartDateDt() != null && request.getEndDateDt() != null) {
            return getTxnSummaryForSDateEDateOrgId(request);
        } else if (CommonUtils.isNotBlank(request.getItemCode())) {
            return getTxnSummaryForItemCdOrgId(request);
        } else {
            return getTxnSummaryWOInput(request);
        }
    }

    private List getTxnSummaryWOInput(TxnSummaryReq request) {
        String s = "select * from process_details where status = 'A' and org_id =:orgId" +
                " union " +
                " select * from process_details where status = 'A' and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
        List l = entityManager.createNativeQuery(s, ProcessDtls.class)
                .setParameter("orgId", request.getOrgId())
                .getResultList();
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and process_id in (select process_id from issue_note_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        return l;
    }

    private List getTxnSummaryForItemCdOrgId(TxnSummaryReq request) {
        String s = "select * from process_details where status = 'A' and org_id =:orgId and process_id in (select  process_id from process_item_map where item_cd =:itemCd)"
                +
                " union " +
                "select * from process_details where status = 'A' and org_id =:orgId and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
        List l = entityManager.createNativeQuery(s, ProcessDtls.class)
                .setParameter("itemCd", request.getItemCode())
                .setParameter("orgId", request.getOrgId())
                .getResultList();
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and org_id =:orgId and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from issue_note_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("itemCd", request.getItemCode())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and org_id =:orgId and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("itemCd", request.getItemCode())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        return l;
    }

    private List getTxnSummaryForSDateEDateOrgId(TxnSummaryReq request) {
        String s = "select * from process_details where status = 'A' and org_id =:orgId and create_dt >=:startDate and create_dt <=:endDate "
                +
                " union " +
                " select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where ce_regional_center_cd =:orgId) ";
        List l = entityManager.createNativeQuery(s, ProcessDtls.class)
                .setParameter("startDate", request.getStartDateDt())
                .setParameter("endDate", request.getEndDateDt())
                .setParameter("orgId", request.getOrgId())
                .getResultList();

        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }

        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        return l;
    }

    private List getTxnSummaryForCdSDateEDateOrgId(TxnSummaryReq request) {
        String s = "select * from process_details where status = 'A' and org_id =:orgId and create_dt >=:startDate and create_dt <=:endDate and process_id in (select process_id from process_item_map where item_cd =:itemCd)"
                +
                " union " +
                " select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
        List l = entityManager.createNativeQuery(s, ProcessDtls.class)
                .setParameter("itemCd", request.getItemCode())
                .setParameter("startDate", request.getStartDateDt())
                .setParameter("endDate", request.getEndDateDt())
                .setParameter("orgId", request.getOrgId())
                .getResultList();
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from issue_note_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("itemCd", request.getItemCode())
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        if (l == null || l.size() == 0) {
            s = "select * from process_details where status = 'A' and create_dt >=:startDate and create_dt <=:endDate and process_id in (select  process_id from process_item_map where item_cd =:itemCd) and process_id in (select process_id from gate_pass_dtls where ce_regional_center_cd =:orgId)";
            l = entityManager.createNativeQuery(s, ProcessDtls.class)
                    .setParameter("itemCd", request.getItemCode())
                    .setParameter("startDate", request.getStartDateDt())
                    .setParameter("endDate", request.getEndDateDt())
                    .setParameter("orgId", request.getOrgId())
                    .getResultList();
        }
        return l;
    }

    @Override
    public List<ItemCodeMaster> findAllItemMasterByStatusAndOrg(String status, Long orgId) {
        String query = "select im.* from item_master im " +
                "           left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "           left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "           where im.status =:status and lam.org_id =:orgId";

        return entityManager.createNativeQuery(query, ItemCodeMaster.class)
                .setParameter("orgId", orgId)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public boolean validateLocationIdAndLocatorIdAndOrgId(Long locationId, Long locatorId, Long orgId) {
        String query = "select lm.LOCATOR_MASTER_ID, lm2.LOCATION_ID, om.ORGANIZATION_ID from locator_master lm left join location_master lm2 on lm.LOCATION = lm2.LOCATION_ID "
                +
                " left join organization_master om  on lm2.ORG_ID  = om.ORGANIZATION_ID " +
                " where lm.LOCATOR_MASTER_ID  =:locatorId " +
                " and lm2.LOCATION_ID =:locationId " +
                " and om.ORGANIZATION_ID =:orgId";

        List resultList = entityManager.createNativeQuery(query)
                .setParameter("orgId", orgId)
                .setParameter("locatorId", locatorId)
                .setParameter("locationId", locationId)
                .getResultList();

        return resultList.size() > 0;
    }

    @Override
    public boolean validateLocatorIdAndOrgId(Long locatorId, Long orgId) {
        String query = "select lm.LOCATOR_MASTER_ID, lm2.LOCATION_ID, om.ORGANIZATION_ID from locator_master lm left join location_master lm2 on lm.LOCATION = lm2.LOCATION_ID "
                +
                " left join organization_master om  on lm2.ORG_ID  = om.ORGANIZATION_ID " +
                " where lm.LOCATOR_MASTER_ID  =:locatorId " +
                " and om.ORGANIZATION_ID =:orgId";

        List resultList = entityManager.createNativeQuery(query)
                .setParameter("orgId", orgId)
                .setParameter("locatorId", locatorId)
                .getResultList();

        return resultList.size() > 0;
    }

    @Override
    public List<LocatorMaster> findAllLocatorByStatusAndOrgId(String status, Long orgId) {
        String query = "select lm.* from " +
                " locator_master lm left join location_master lm2 on lm.LOCATION = lm2.LOCATION_ID " +
                " left join organization_master om  on lm2.ORG_ID  = om.ORGANIZATION_ID " +
                " where om.ORGANIZATION_ID =:orgId " +
                "and lm.status =:status";

        return entityManager.createNativeQuery(query, LocatorMaster.class)
                .setParameter("orgId", orgId)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public boolean validateLocationIdAndOrgId(String location, Long orgId) {
        String query = "select * from location_master where LOCATION_ID=:locationId and ORG_ID=:orgId and status = 'A' ";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter("orgId", orgId)
                .setParameter("locationId", location)
                .getResultList();

        return resultList.size() > 0;
    }

    @Override
    public String getNextItemNameMasterCode(ItemMasterReq req) {

        List<ItemNameMaster> itemLst = itemNameRepository
                .findAllByCategoryCodeAndSubCategoryCodeAndDesciplineCodeAndTypeCode(req.getCategory(),
                        req.getSubCategory(), req.getDisciplines(), req.getType());
        List<String> s = new ArrayList<>();
        if (itemLst != null && itemLst.size() > 0) {
            for (ItemNameMaster inm : itemLst) {
                if (inm.getItemNameCode() != null) {
                    s.add(inm.getItemNameCode());
                }
            }
        }

        String q = "select item_name_code from items_name_mst where descipline_code=:desciplineCode and category_cd=:categoryCd and sub_category_code=:subCategoryCode and type_code=:typeCode ";
        List<String> stringList = entityManager.createNativeQuery(q, String.class)
                .setParameter("desciplineCode", req.getDisciplines())
                .setParameter("categoryCd", req.getCategory())
                .setParameter("subCategoryCode", req.getSubCategory())
                .setParameter("typeCode", req.getType())
                .getResultList();

        /*
         * OptionalLong max = stringList.stream().mapToLong(e ->
         * Long.parseLong(e)).max();
         * if (max.isPresent()) {
         * long asLong = max.getAsLong();
         * if (asLong < 100) {
         * return CommonUtils.get3Digit(asLong);
         * }
         * }
         */

        for (int i = 1; i <= 999; i++) {
            String currVal = CommonUtils.get3Digit(i);
            if (!stringList.contains(currVal) && !s.contains(currVal)) {
                return currVal;
            }
        }

        /*
         * String query =
         * "select max(item_name_code)+1 from items_name_mst where descipline_code=:desciplineCode and category_cd=:categoryCd and sub_category_code=:subCategoryCode and type_code=:typeCode and item_name_code not in (:stringList)"
         * ;
         * Double singleResult = (Double) entityManager.createNativeQuery(query)
         * .setParameter("desciplineCode", req.getDisciplines())
         * .setParameter("categoryCd", req.getCategory())
         * .setParameter("subCategoryCode", req.getSubCategory())
         * .setParameter("typeCode", req.getType())
         * .setParameter("stringList", stringList)
         * .getSingleResult();
         * 
         * return String.valueOf(singleResult.intValue());
         */
        return "1000";
    }

    @Override
    public Double getTotalQuantityOfItemInOrg(String itemMasterCd, Long orgId) {
        String query = " select sum(quantity) from item_master im " +
                "                 left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "                    left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "                   where im.ITEM_MASTER_CD  =:itemCd " +
                "                   and im.status = 'A'  " +
                "                   and lam.org_id =:orgId";
        BigDecimal singleResult = (BigDecimal) entityManager.createNativeQuery(query)
                .setParameter("itemCd", itemMasterCd)
                .setParameter("orgId", orgId)
                .getSingleResult();
        if (singleResult == null) {
            singleResult = BigDecimal.ZERO;
        }

        return singleResult.doubleValue();
    }

    @Override
    public List<OHQDataEntity> getAlllOHQData(OHQRequest request) {
        String query = "select im.ITEM_MASTER_ID, im.ITEM_MASTER_CD, im.ITEM_MASTER_DESC, um.UOM_ID, um.UOM_NAME, lm.LOCATOR_MASTER_ID , lm.LOCATOR_DESC, im.QUANTITY, im.LOCATION_ID , lam.LOCATION_NAME, im.PRICE, IM.SUBCATEGORY, (select sub_category_desc from sub_category_mst where category_cd = IM.CATEGORY and sub_category_code =  IM.SUBCATEGORY LIMIT 1) SUBCATEGORY_DESC from item_master im "
                +
                "           left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "           left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "           left join uom_master um  on im.UOM_ID = um.UOM_ID ";
        if (CommonUtils.isNotBlank(request.getItemCode())) {
            query = query + "   where im.ITEM_MASTER_CD  =:itemCode and im.status = 'A'";
            return entityManager.createNativeQuery(query, OHQDataEntity.class)
                    .setParameter("itemCode", request.getItemCode())
                    .getResultList();
        } else if (CommonUtils.isNotBlank(request.getItemDesc())) {
            query = query + "   where UPPER(im.ITEM_MASTER_DESC)  =:itemDesc and im.status = 'A'";
            return entityManager.createNativeQuery(query, OHQDataEntity.class)
                    .setParameter("itemDesc", request.getItemDesc().toUpperCase())
                    .getResultList();
        }
        query = query + "   where im.status = 'A'";
        return entityManager.createNativeQuery(query, OHQDataEntity.class)
                .getResultList();
    }

    @Override
    public List<LocatorMaster> getLocatorDtlsByOrgId(Long orgId) {
        String query = "select lm.* from locator_master lm " +
                " left join location_master lam on lm.LOCATION = lam.LOCATION_ID " +
                " where lam.ORG_ID  =:orgId and lm.STATUS  = 'A';";
        return entityManager.createNativeQuery(query, LocatorMaster.class)
                .setParameter("orgId", orgId)
                .getResultList();
    }

    @Override
    public List<ItemCodeMaster> getAllActiveItemByOrgId(String itemMasterCd, Long orgId) {
        String query = " select im.* from item_master im " +
                "                 left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "                    left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "                   where im.ITEM_MASTER_CD  =:itemCd " +
                "                   and im.status = 'A'  " +
                "                   and lam.org_id =:orgId";
        return entityManager.createNativeQuery(query, ItemCodeMaster.class)
                .setParameter("itemCd", itemMasterCd)
                .setParameter("orgId", orgId)
                .getResultList();

    }

    @Override
    public List<ItemCodeMaster> getAllActiveItemByOrgId(Long orgId) {
        String query = " select im.* from item_master im " +
                "                 left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "                    left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "                   where im.status = 'A'  " +
                "                   and lam.org_id =:orgId";
        return entityManager.createNativeQuery(query, ItemCodeMaster.class)
                .setParameter("orgId", orgId)
                .getResultList();
    }

    @Override
    public String getNextParamVal(String paramType) {
        String query = "select param_val from general_parameter where param_type =:paramType and STATUS ='A' and param_val != '999' and param_val != '99' order by cast(param_val as unsigned) desc limit 1";
        return (String) entityManager.createNativeQuery(query)
                .setParameter("paramType", paramType)
                .getSingleResult();
    }

    @Override
    public List<ItemCodeMaster> getItemMasterByDesc(String itemName) {
        String query = " select im.* from item_master im  where im.status = 'A'  and upper(im.item_master_desc) =:itemName and LOCATION_ID not in ('31','32')";
        return entityManager.createNativeQuery(query, ItemCodeMaster.class)
                .setParameter("itemName", itemName.toUpperCase())
                .getResultList();
    }

    @Override
    public List<ItemCodeMaster> getItemMasterByDescAndOrgId(String itemName, Long orgId) {
        String query = " select im.* from item_master im " +
                "                 left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "                    left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "                   where im.status = 'A'  " +
                "                   and lam.org_id =:orgId " +
                "                   and upper(im.item_master_desc) =:itemName";
        return entityManager.createNativeQuery(query, ItemCodeMaster.class)
                .setParameter("itemName", itemName.toUpperCase().trim())
                .setParameter("orgId", orgId)
                .getResultList();
    }
}
