package com.sai.inventory.dao;

import com.sai.inventory.domain.process.request.StockLedgerRrq;
import com.sai.inventory.domain.process.response.ItemCodeMasterHistResp;

import java.util.List;

public interface ProcessDao {
    Object getAllProcess(Long orgId);

    List<ItemCodeMasterHistResp> getStockLedger(StockLedgerRrq req);
}
