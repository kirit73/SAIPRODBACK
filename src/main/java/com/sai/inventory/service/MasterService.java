package com.sai.inventory.service;

import com.sai.inventory.domain.common.request.DeptRequestByIdReq;
import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.common.request.RequestByIdReq;
import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.*;
import com.sai.inventory.entity.*;

import java.util.List;

public interface MasterService {
    LocationMasterResp saveLocationMaster(LocationMasterReq userId);

    List<LocationMaster> getLocationMaster(Long orgId);

    LocationMaster getLocationMasterById(RequestByIdReq id);

    LocationMasterResp updateLocationMaster(LocationMasterReq locationMasterReq);

    void deleteLocationMaster(RequestByIdReq locationMasterReq);

    DeptMasterResp saveDeptMaster(DepartmentMasterReq userId);

    List<DepartmentMaster> getDeptMaster();

    DepartmentMaster getDeptMasterById(DeptRequestByIdReq id);

    DeptMasterResp updateDeptMaster(DepartmentMasterReq userId);

    void deleteDeptMaster(DeptRequestByIdReq req);

    CommonIdResponse saveMaster(UOMMasterReq req);

    List<UOMMaster> getUOMMaster();

    UOMMaster getMasterById(CommonIdResponse id);

    CommonIdResponse updateMaster(UOMMasterReq req);

    void deleteMaster(CommonIdResponse req);

    CommonIdResponse saveMaster(OrganizationMasterReq req);

    List<OrganizationMaster> getOrganizationMaster();

    OrganizationMaster getOrgMasterById(CommonIdResponse id);

    List<OrganizationMaster> getOrgMasterByParentId(CommonIdResponse id);

    CommonIdResponse updateMaster(OrganizationMasterReq req);

    void deleteOrgMaster(CommonIdResponse req);

    CommonIdResponse saveMaster(VendorMasterReq req);

    List<VendorMaster> getVendorMaster();

    VendorMaster getVendorMasterById(CommonIdResponse id);

    CommonIdResponse updateMaster(VendorMasterReq req);

    void deleteVendorMaster(CommonIdResponse req);

    CommonIdResponse saveMaster(EmployeeMasterReq req);

    List<EmployeeMaster> getEmployeeMaster(Long orgId);

    EmployeeMaster getEmployeeMasterById(CommonIdResponse id);

    CommonIdResponse updateMaster(EmployeeMasterReq req);

    void deleteEmpMaster(CommonIdResponse req);

    CommonIdResponse saveMaster(LocatorMasterReq req);

    List<LocatorMaster> getLocatorMaster(Long orgId);

    LocatorMaster getLocatorMasterById(CommonIdResponse id);

    CommonIdResponse saveMaster(UsersMasterReq req);

    List<UsersMaster> getUserMaster();

    CommonIdResponse updateMaster(LocatorMasterReq req);

    void deleteLocatorMaster(CommonIdResponse req);

    UsersMaster getUserMasterById(CommonIdResponse id);

    CommonIdResponse updateMaster(UsersMasterReq req);

    void deleteUserMaster(CommonIdResponse req);

    List<ItemCodeMasterResp> getItemMaster(Long aLong);

    CommonIdResponse saveMaster(ItemMasterReq req);

    List<ItemCodeMasterResp> getItemMasterByOrgId(Long aLong);

    Object getItemMasterById(CommonIdResponse id);

    CommonIdResponse updateMaster(ItemMasterReq req);

    void deleteItemMaster(CommonIdResponse req);

    List<OrganizationMaster> getParentOrganizationMaster();

    Object getOHQ(OHQRequest id);

    ItemCodeMaster saveMasterNewEntry(String itemCode, Long locatorId, String createUser);

    Object changePassword(ChangePasswordReq changePasswordReq);

    Object getItemMasterByItemCode(FetchItemMasterCode req);

    Object getOrgMasterAndLocationById(CommonIdResponse id);

    Object getAllOHQ(OHQRequest id);

    Object getLocatorMasterByOrgId(OrgIdRequest id);

    Object getLocationMasterByOrgId(OrgIdRequest id);

    Object validateDuplicateItemName(ValidateItemNameReq req);
}
