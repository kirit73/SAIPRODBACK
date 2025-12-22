package com.sai.inventory.controller;

import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.GeneralParamRes;
import com.sai.inventory.service.GeneralParamsService;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.GenParamTypeEnum;
import com.sai.inventory.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genparam")
public class GeneralParamsController {
    @Autowired
    private GeneralParamsService generalParamsService;
    @Autowired
    private JwtService jwtService;

    @CrossOrigin
    @GetMapping("/getAllBrands")
    public ResponseEntity getAllBrands() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.BRANDS.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllColors")
    public ResponseEntity getAllColors() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.COLORS.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllSizes")
    public ResponseEntity getAllSizes() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.SIZES.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllUsageCategories")
    public ResponseEntity getAllUsageCategories() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.USAGE_CAT.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllCategories")
    public ResponseEntity getAllCategories() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.CATEGORIES.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllUserType")
    public ResponseEntity getAllUserType() {
        GeneralParamReq id = new GeneralParamReq();
        id.setParamType(GenParamTypeEnum.USER_TYPE.name());
        List<GeneralParamRes> paramResList = generalParamsService.getGeneralParams(id);
        return ResponseBuilder.getSuccessResponse(paramResList);
    }


    @CrossOrigin
    @GetMapping("/getAllItemNames")
    public ResponseEntity getAllItemNames() {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllItemNames());
    }


    @CrossOrigin
    @GetMapping("/getAllItemType")
    public ResponseEntity getAllItemType() {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllItemType());
    }


    @CrossOrigin
    @GetMapping("/getAllDiscipline")
    public ResponseEntity getAllDiscipline() {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllDiscipline());
    }


    @CrossOrigin
    @GetMapping("/getAllSubCategories")
    public ResponseEntity getAllSubCategories() {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllSubCategories());
    }


    @CrossOrigin
    @PostMapping("/getAllItemNamesByDtls")
    public ResponseEntity getAllItemNamesByDtls(@RequestBody ItemNameReq req) {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllItemNamesByDtls(req));
    }


    @CrossOrigin
    @PostMapping("/getAllItemTypeByDtls")
    public ResponseEntity getAllItemTypeByDtls(@RequestBody ItemTypeReq req) {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllItemTypeByDtls(req));
    }


    @CrossOrigin
    @PostMapping("/getAllDisciplineByDtls")
    public ResponseEntity getAllDisciplineByDtls(@RequestBody DisciplineReq req) {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllDisciplineByDtls(req));
    }


    @CrossOrigin
    @PostMapping("/getAllSubCategoriesByDtls")
    public ResponseEntity getAllSubCategoriesByDtls(@RequestBody SubCategoryReq req) {
        return ResponseBuilder.getSuccessResponse(generalParamsService.getAllSubCategoriesByDtls(req));
    }


    @PostMapping("/saveBrand")
    public ResponseEntity saveBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GeneralParamReq req) {
        req.setParamType(GenParamTypeEnum.BRANDS.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveGeneralParam(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveSize")
    public ResponseEntity saveSize(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GeneralParamReq req) {
        req.setParamType(GenParamTypeEnum.SIZES.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveGeneralParam(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveColor")
    public ResponseEntity saveColor(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GeneralParamReq req) {
        req.setParamType(GenParamTypeEnum.COLORS.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveGeneralParam(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveCategories")
    public ResponseEntity saveCategories(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GeneralParamReq req) {
        req.setParamType(GenParamTypeEnum.CATEGORIES.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveGeneralParam(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveUsageCategories")
    public ResponseEntity saveUsageCategories(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody GeneralParamReq req) {
        req.setParamType(GenParamTypeEnum.USAGE_CAT.name());
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveGeneralParam(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveSubCategories")
    public ResponseEntity saveSubCategories(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody SaveSubCatReq req) {
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveSubCategories(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveItemType")
    public ResponseEntity saveItemType(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody SaveItemTypeReq req) {
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveItemType(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveItemName")
    public ResponseEntity saveItemName(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody SaveItemNameReq req) {
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveItemName(req);
        return ResponseBuilder.getSuccessResponse();
    }

    @PostMapping("/saveDiscipline")
    public ResponseEntity saveDiscipline(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody SaveDiscReq req) {
        req.setOrgId(jwtService.extractOrganizationId(CommonUtils.getAuthKey(auth)));
        req.setCreateUser(jwtService.extractUsername(CommonUtils.getAuthKey(auth)));
        generalParamsService.saveDiscipline(req);
        return ResponseBuilder.getSuccessResponse();
    }
}
