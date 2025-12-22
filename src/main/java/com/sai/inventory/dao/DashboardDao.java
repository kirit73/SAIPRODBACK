package com.sai.inventory.dao;

import com.sai.inventory.domain.dashboard.request.GetFNSCategoryReq;
import com.sai.inventory.domain.dashboard.request.IssueNoteDataReq;
import com.sai.inventory.domain.dashboard.request.PurchasingSummaryReq;
import com.sai.inventory.entity.IssueNoteDetails;
import com.sai.inventory.entity.ProcessDtls;
import com.sai.inventory.entity.internal.FNSCategoryEntity;

import java.util.List;

public interface DashboardDao {
    List<FNSCategoryEntity> getFNSCategory(GetFNSCategoryReq request);

    List<IssueNoteDetails> findAllByDtls(IssueNoteDataReq req);

    List<ProcessDtls> getPurchaseSummary(PurchasingSummaryReq req);
}
