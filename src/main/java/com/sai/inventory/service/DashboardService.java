package com.sai.inventory.service;

import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.dashboard.request.GetFNSCategoryReq;
import com.sai.inventory.domain.dashboard.request.IssueNoteDataReq;
import com.sai.inventory.domain.dashboard.request.PurchasingSummaryReq;
import com.sai.inventory.domain.dashboard.response.FNSCategoryRes;
import com.sai.inventory.entity.internal.TxnSummaryResp;

import java.util.List;

public interface DashboardService {
    List<FNSCategoryRes> getFNSCategory(GetFNSCategoryReq req);

    Object getDashboardIssueNoteData(IssueNoteDataReq req);

    Object getTotalValueOfAllItem(OrgIdRequest req);

    List<TxnSummaryResp> getPurchaseSummary(PurchasingSummaryReq req);
}
