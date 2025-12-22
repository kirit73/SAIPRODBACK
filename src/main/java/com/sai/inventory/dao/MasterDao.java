package com.sai.inventory.dao;

import com.sai.inventory.domain.master.request.ItemMasterReq;
import com.sai.inventory.domain.master.request.OHQRequest;
import com.sai.inventory.domain.process.request.TxnSummaryReq;
import com.sai.inventory.entity.ItemCodeMaster;
import com.sai.inventory.entity.LocatorMaster;
import com.sai.inventory.entity.internal.OHQDataEntity;

import java.util.List;

public interface MasterDao {
    List<OHQDataEntity> getOHQData(OHQRequest request);

    List getTxnSummary(TxnSummaryReq request);

    List<ItemCodeMaster> findAllItemMasterByStatusAndOrg(String a, Long aLong);

    boolean validateLocationIdAndLocatorIdAndOrgId(Long locationId, Long locatorId, Long orgId);

    boolean validateLocatorIdAndOrgId(Long locatorId, Long orgId);

    List<LocatorMaster> findAllLocatorByStatusAndOrgId(String a, Long orgId);

    boolean validateLocationIdAndOrgId(String location, Long orgId);

    String getNextItemNameMasterCode(ItemMasterReq req);

    Double getTotalQuantityOfItemInOrg(String itemMasterCd, Long orgId);

    List<OHQDataEntity> getAlllOHQData(OHQRequest id);

    Object getLocatorDtlsByOrgId(Long orgId);

    List<ItemCodeMaster> getAllActiveItemByOrgId(String itemCode, Long orgId);

    List<ItemCodeMaster> getAllActiveItemByOrgId(Long orgId);

    String getNextParamVal(String paramType);

    List<ItemCodeMaster> getItemMasterByDesc(String itemName);

    List<ItemCodeMaster> getItemMasterByDescAndOrgId(String itemName, Long orgId);

}
