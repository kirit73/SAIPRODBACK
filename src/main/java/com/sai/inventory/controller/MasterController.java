package com.sai.inventory.controller;

import com.sai.inventory.domain.common.request.DeptRequestByIdReq;
import com.sai.inventory.domain.common.request.OrgIdRequest;
import com.sai.inventory.domain.common.request.RequestByIdReq;
import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.CommonIdResponse;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.service.MasterService;
import com.sai.inventory.util.ApplicationConstant;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import com.sai.inventory.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/saveLocationMaster")
    public ResponseEntity saveLocationMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LocationMasterReq userId) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setOrgId(orgId);
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveLocationMaster(userId));
    }

    @GetMapping("/getLocationMaster")
    public ResponseEntity getLocationMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        return ResponseBuilder.getSuccessResponse(masterService.getLocationMaster(orgId));
    }

    @PostMapping("/getLocationMasterById")
    public ResponseEntity getLocationMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody RequestByIdReq id) {
        return ResponseBuilder.getSuccessResponse(masterService.getLocationMasterById(id));
    }

    @PostMapping("/updateLocationMaster")
    public ResponseEntity updateLocationMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LocationMasterReq userId) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        userId.setOrgId(orgId);
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateLocationMaster(userId));
    }

    @PostMapping("/deleteLocationMaster")
    public ResponseEntity deleteLocationMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody RequestByIdReq id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteLocationMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveDeptMaster")
    public ResponseEntity saveDeptMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody DepartmentMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveDeptMaster(userId));
    }

    @GetMapping("/getDeptMaster")
    public ResponseEntity getDeptMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getDeptMaster());
    }

    @PostMapping("/getDeptMasterById")
    public ResponseEntity getDeptMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody DeptRequestByIdReq id) {
        return ResponseBuilder.getSuccessResponse(masterService.getDeptMasterById(id));
    }

    @PostMapping("/updateDeptMaster")
    public ResponseEntity updateDeptMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody DepartmentMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateDeptMaster(userId));
    }

    @PostMapping("/deleteDeptMaster")
    public ResponseEntity deleteLocationMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody DeptRequestByIdReq id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteDeptMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveUOMMaster")
    public ResponseEntity saveUOMMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody UOMMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getUOMMaster")
    public ResponseEntity getUOMMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getUOMMaster());
    }

    @PostMapping("/getUOMMasterById")
    public ResponseEntity getUOMMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getMasterById(id));
    }

    @PostMapping("/updateUOMMaster")
    public ResponseEntity updateUOMMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody UOMMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteUOMMaster")
    public ResponseEntity deleteUOMMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveOrgMaster")
    public ResponseEntity saveOrgMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrganizationMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getOrgMaster")
    public ResponseEntity getOrgMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getOrganizationMaster());
    }

    @GetMapping("/getParentOrgMaster")
    public ResponseEntity getParentOrgMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getParentOrganizationMaster());
    }

    @PostMapping("/getOrgMasterById")
    public ResponseEntity getOrgMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getOrgMasterAndLocationById(id));
    }

    @PostMapping("/getOrgMasterByParentId")
    public ResponseEntity getOrgMasterByParentId(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getOrgMasterByParentId(id));
    }

    @PostMapping("/updateOrgMaster")
    public ResponseEntity updateOrgMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrganizationMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteOrgMaster")
    public ResponseEntity deleteOrgMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteOrgMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveVendorMaster")
    public ResponseEntity saveVendorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody VendorMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getVendorMaster")
    public ResponseEntity getVendorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getVendorMaster());
    }

    @PostMapping("/getVendorMasterById")
    public ResponseEntity getVendorMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getVendorMasterById(id));
    }

    @PostMapping("/updateVendorMaster")
    public ResponseEntity updateVendorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody VendorMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteVendorMaster")
    public ResponseEntity deleteVendorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteVendorMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveEmpMaster")
    public ResponseEntity saveEmpMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody EmployeeMasterReq userId) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        userId.setOrganizationId(orgId);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getEmpMaster")
    public ResponseEntity getEmpMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        return ResponseBuilder.getSuccessResponse(masterService.getEmployeeMaster(orgId));
    }

    @PostMapping("/getEmpMasterById")
    public ResponseEntity getEmpMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getEmployeeMasterById(id));
    }

    @PostMapping("/updateEmpMaster")
    public ResponseEntity updateEmpMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody EmployeeMasterReq userId) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        userId.setOrganizationId(orgId);
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteEmpMaster")
    public ResponseEntity deleteEmpMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteEmpMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveLocatorMaster")
    public ResponseEntity saveLocatorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LocatorMasterReq userId) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        userId.setOrgId(orgId);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getLocatorMaster")
    public ResponseEntity getLocatorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        Long orgId = jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth));
        return ResponseBuilder.getSuccessResponse(masterService.getLocatorMaster(orgId));
    }

    @PostMapping("/getLocatorMasterById")
    public ResponseEntity getLocatorMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getLocatorMasterById(id));
    }

    @PostMapping("/updateLocatorMaster")
    public ResponseEntity updateLocatorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LocatorMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setUserId(userName);
        userId.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteLocatorMaster")
    public ResponseEntity deleteLocatorMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteLocatorMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveUserMaster")
    public ResponseEntity saveUserMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody UsersMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setCreateUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getUserMaster")
    public ResponseEntity getUserMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getUserMaster());
    }

    @PostMapping("/getUserMasterById")
    public ResponseEntity getUserMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getUserMasterById(id));
    }

    @PostMapping("/updateUserMaster")
    public ResponseEntity updateUserMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody UsersMasterReq userId) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        userId.setCreateUserId(userName);
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        masterService.changePassword(changePasswordReq);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/deleteUserMaster")
    public ResponseEntity deleteUserMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteUserMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveItemMaster")
    public ResponseEntity saveItemMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody ItemMasterReq userId) {
        userId.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        userId.setCreateUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(masterService.saveMaster(userId));
    }

    @GetMapping("/getItemMaster")
    public ResponseEntity getItemMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return ResponseBuilder.getSuccessResponse(masterService.getItemMaster(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth))));
    }

    @PostMapping("/getItemMasterByOrgId")
    public ResponseEntity getItemMasterByOrgId(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody FetchItemMasterReq req) {
        if(CommonUtils.isBlank(req.getOrgId())){
            req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }
        return ResponseBuilder.getSuccessResponse(masterService.getItemMasterByOrgId(req.getOrgId()));
    }

    @PostMapping("/getItemMasterById")
    public ResponseEntity getItemMasterById(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        return ResponseBuilder.getSuccessResponse(masterService.getItemMasterById(id));
    }

    @PostMapping("/updateItemMaster")
    public ResponseEntity updateItemMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody ItemMasterReq userId) {
        userId.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        userId.setCreateUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(masterService.updateMaster(userId));
    }

    @PostMapping("/deleteItemMaster")
    public ResponseEntity deleteItemMaster(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody CommonIdResponse id) {
        String userName = jwtService.extractUsername(CommonUtils.getAuthKey(auth));
        id.setUserId(userName);
        masterService.deleteItemMaster(id);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/getOHQ")
    public ResponseEntity getOHQ(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OHQRequest id) {
        id.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        if (ApplicationConstant.SUPER_SUPER_ADMIN_TYPE.equalsIgnoreCase(jwtService.extractUserType(CommonUtils.getAuthKey(auth)))) {
            /*if (CommonUtils.isBlank(id.getOrgId())) {
                throw new ValidationException(ErrorResponseConstant.ORG_ID_IS_MAND_CD, ErrorResponseConstant.ORG_ID_IS_MAND_MSG);
            }*/
        } else {
            id.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        }
        return ResponseBuilder.getSuccessResponse(masterService.getOHQ(id));
    }

    @PostMapping("/getItemMasterByItemCode")
    public ResponseEntity getItemMasterByItemCode(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody FetchItemMasterCode req) {
        return ResponseBuilder.getSuccessResponse(masterService.getItemMasterByItemCode(req));
    }

    @PostMapping("/getAllOHQ")
    public ResponseEntity getAllOHQ(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OHQRequest id) {
        id.setUserId(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(masterService.getAllOHQ(id));
    }

    @PostMapping("/getLocatorMasterByOrgId")
    public ResponseEntity getLocatorMasterByOrgId(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrgIdRequest id) {
        return ResponseBuilder.getSuccessResponse(masterService.getLocatorMasterByOrgId(id));
    }

    @PostMapping("/getLocationMasterByOrgId")
    public ResponseEntity getLocationMasterByOrgId(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody OrgIdRequest id) {
        return ResponseBuilder.getSuccessResponse(masterService.getLocationMasterByOrgId(id));
    }

    @PostMapping("/validateDuplicateItemName")
    public ResponseEntity validateDuplicateItemName(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody ValidateItemNameReq req) {
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        return ResponseBuilder.getSuccessResponse(masterService.validateDuplicateItemName(req));
    }
}
