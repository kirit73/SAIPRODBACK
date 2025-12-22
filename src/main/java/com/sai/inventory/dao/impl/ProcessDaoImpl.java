package com.sai.inventory.dao.impl;

import com.sai.inventory.dao.ProcessDao;
import com.sai.inventory.domain.process.request.StockLedgerRrq;
import com.sai.inventory.domain.process.response.ItemCodeMasterHistResp;
import com.sai.inventory.entity.ProcessDtls;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProcessDaoImpl implements ProcessDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object getAllProcess(Long orgId) {
        String query = "select * from process_details pd where ORG_ID  =:orgId " +
                "union " +
                "select * from process_details where process_id in (select process_id from issue_note_dtls where ce_regional_center_cd =:orgId) "
                +
                "union " +
                "select * from process_details where process_id in (select process_id from gate_pass_dtls   where ce_regional_center_cd =:orgId) "
                +
                "union " +
                "select * from process_details where process_id in (select process_id from grn_dtls gd  where ce_regional_center_cd =:orgId) "
                +
                "union " +
                "select * from process_details where process_id in (select process_id from acpt_rej_note_dtls  where ce_regional_center_cd =:orgId) ";

        return entityManager.createNativeQuery(query, ProcessDtls.class)
                .setParameter("orgId", orgId)
                .getResultList();
    }

    @Override
    public List<ItemCodeMasterHistResp> getStockLedger(StockLedgerRrq req) {
        // String query = "select * from (select im.ITEM_MASTER_HIST_ID, im.process_id , im.PROCESS_STAGE , im.PRE_QUANTITY , im.POST_QUANTITY , im.CREATE_DT , IM.CREATE_USER, IM.ITEM_MASTER_DESC, IM.ITEM_MASTER_CD, IM.LOCATION_ID, IM.LOCATOR_ID, IM.TOTAL_PRE_QTY, IM.TOTAL_POST_QTY, DATE_FORMAT(im.CREATE_DT, '%d/%m/%Y') AS TXN_DT,"
        String query = "select * from (select im.ITEM_MASTER_HIST_ID, im.process_id , im.PROCESS_STAGE , im.PRE_QUANTITY , im.POST_QUANTITY , im.CREATE_DT , IM.CREATE_USER, IM.ITEM_MASTER_DESC, IM.ITEM_MASTER_CD, IM.LOCATION_ID, IM.LOCATOR_ID, IM.TOTAL_PRE_QTY, IM.TOTAL_POST_QTY, DATE_FORMAT(im.CREATE_DT, '%d/%m/%Y %H:%i:%s') AS TXN_DT,"
                +
                " (select create_dt from item_master where ITEM_MASTER_CD = im.ITEM_MASTER_CD order by create_dt asc limit 1) ORG_CREATE_DT, (select create_user from item_master where ITEM_MASTER_CD = im.ITEM_MASTER_CD order by create_dt asc limit 1) ORG_CREATE_USER, IM.INIT_QTY "
                +
                " from item_master_hist im " +
                " left join locator_master lm on im.LOCATOR_ID = lm.LOCATOR_MASTER_ID " +
                "    left join location_master lam on im.LOCATION_ID = lam.LOCATION_ID " +
                "   where im.ITEM_MASTER_CD  =:itemCd " +
                "   and im.status = 'A'  " +
                "   and lam.org_id =:orgId order by im.create_dt asc ) a where a.create_dt between :fromDt and :toDt ";

        return entityManager.createNativeQuery(query, ItemCodeMasterHistResp.class)
                .setParameter("orgId", req.getOrgId())
                .setParameter("itemCd", req.getItemCode())
                .setParameter("fromDt", req.getFromDateDt())
                .setParameter("toDt", req.getToDateDt())
                .getResultList();
    }
}
