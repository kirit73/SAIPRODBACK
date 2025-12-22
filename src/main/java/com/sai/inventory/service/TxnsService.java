package com.sai.inventory.service;

import java.util.List;

import com.sai.inventory.domain.process.request.*;
import com.sai.inventory.domain.process.response.StockLedgerRes;

public interface TxnsService {


    Object getTxnSummary(TxnSummaryReq req);

    Object getTxnsDtls(TxnDetailsReq req);

    Object getTxnDtlsByTxnType(TxnDtlsByTxnTypeReq req);

    Object getStockLedger(StockLedgerRrq req);

    Object getTxnsDtlsV2(TxnDetailsReq req);

    Object getTxnSummaryForAllOrg(TxnSummaryReq req);

   List<StockLedgerRes> getStockLedgerBatch(StockLedgerBatchReq req);

    List<StockLedgerRes> getStockLedgerBySubCategory(StockLedgerSubCategoryReq req);
}
