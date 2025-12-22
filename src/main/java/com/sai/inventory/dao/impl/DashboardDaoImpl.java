package com.sai.inventory.dao.impl;

import com.sai.inventory.dao.DashboardDao;
import com.sai.inventory.domain.dashboard.request.GetFNSCategoryReq;
import com.sai.inventory.domain.dashboard.request.IssueNoteDataReq;
import com.sai.inventory.domain.dashboard.request.PurchasingSummaryReq;
import com.sai.inventory.entity.IssueNoteDetails;
import com.sai.inventory.entity.ProcessDtls;
import com.sai.inventory.entity.internal.FNSCategoryEntity;
import com.sai.inventory.util.CommonUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DashboardDaoImpl implements DashboardDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FNSCategoryEntity> getFNSCategory(GetFNSCategoryReq request) {
        String query = null;
        Map<String, Object> paramMap = new HashMap();

        query = "select  item_master_cd ,ITEM_MASTER_DESC, SUBCATEGORY, create_dt, status, SUBCATEGORY_DESC, ORG_ID, LOCATOR_ID from (select im.ITEM_MASTER_CD, im.ITEM_MASTER_DESC , im.SUBCATEGORY, im.status, im.locator_id, \n" +
                "                 (select CREATE_DT from process_item_map pim where pim.item_cd = im.ITEM_MASTER_CD and  CREATE_DT > Date_add(Now(), interval - 12 month) order by CREATE_DT desc LIMIT 1) CREATE_DT,  \n" +
                "                 (select sub_category_desc from sub_category_mst where category_cd = IM.CATEGORY and sub_category_code =  IM.SUBCATEGORY LIMIT 1) SUBCATEGORY_DESC, lam.org_id from item_master im \n" +
                "                                            left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID  \n" +
                "                                                          left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID \n" +
                "                                                         ) a \n" +
                "                                                     where a.status = 'A'  ";
        if (CommonUtils.isNotBlank(request.getItemCode())) {
            query = query + " and a.ITEM_MASTER_CD  = :itemCode ";
            paramMap.put("itemCode", request.getItemCode());
        }
        if (request.getOrgId() != null && request.getOrgId() > 0) {
            query = query + " and a.org_id =:orgId ";
            paramMap.put("orgId", request.getOrgId());
        }

        Query q = entityManager.createNativeQuery(query, FNSCategoryEntity.class);

        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return q.getResultList();

    }

    @Override
    public List<IssueNoteDetails> findAllByDtls(IssueNoteDataReq req) {
        Map<String, Object> paramMap = new HashMap();

        String query = "select ind.* from issue_note_dtls ind " +
                " left join process_details pd on ind.process_id = pd.process_id " +
                " where  pd.create_dt between :startDt and :endDate" +
                " and pd.process_type =:type ";

        if (req.getOrgId() != null && req.getOrgId() > 0) {
            query = query + " and pd.ORG_ID =:orgId  ";
            paramMap.put("orgId", req.getOrgId());
        }

        Query q = entityManager.createNativeQuery(query, IssueNoteDetails.class)
                .setParameter("startDt", req.getStartDateDt())
                .setParameter("endDate", req.getEndDateDt())
                .setParameter("type", req.getType());

        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }

        return q.getResultList();
    }

    @Override
    public List<ProcessDtls> getPurchaseSummary(PurchasingSummaryReq req) {
        Map<String, Object> paramMap = new HashMap();
        String query = "select * from process_details pd  where process_id  in ( select pd.process_id from process_details pd " +
                "   left join process_item_map pim on pim.process_id = pd.process_id " +
                "   left join item_master im on im.ITEM_MASTER_CD = pim.item_cd " +
                "   where pd.status = 'A' and pd.process_type = 'PO' " +
                "   and pd.create_dt >=:startDt and pd.create_dt <=:endDate " +
                "   and pd.process_id in (select  process_id from grn_dtls) ";

        if (CommonUtils.isNotBlank(req.getCategoryCode())) {
            query = query + "   and im.CATEGORY =:cc ";
            paramMap.put("cc", req.getCategoryCode());
        }
        if (CommonUtils.isNotBlank(req.getSubCategoryCode())) {
            query = query + "   and im.SUBCATEGORY =:scc ";
            paramMap.put("scc", req.getSubCategoryCode());
        }
        if (CommonUtils.isNotBlank(req.getItemCode())) {
            query = query + "   and im.ITEM_MASTER_CD =:itemCd ";
            paramMap.put("itemCd", req.getItemCode());
        }
        if(req.getOrgId() != null && req.getOrgId() > 0) {
            query = query + "  and pd.org_id =:orgId ";
            paramMap.put("orgId", req.getOrgId());
        }

        query = query + "   ) ";

        Query q = entityManager.createNativeQuery(query, ProcessDtls.class)
                .setParameter("startDt", req.getStartDateDt())
                .setParameter("endDate", req.getEndDateDt());

        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                q.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return q.getResultList();
    }
}
