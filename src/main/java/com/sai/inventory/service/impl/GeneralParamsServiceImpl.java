package com.sai.inventory.service.impl;

import com.sai.inventory.dao.MasterDao;
import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.GeneralParamRes;
import com.sai.inventory.entity.*;
import com.sai.inventory.exception.ValidationException;
import com.sai.inventory.repository.*;
import com.sai.inventory.service.GeneralParamsService;
import com.sai.inventory.util.CommonUtils;
import com.sai.inventory.util.ErrorResponseConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralParamsServiceImpl implements GeneralParamsService {

    @Autowired
    private GeneralParamRepository generalParamRepository;

    @Autowired
    private ItemNameRepository itemNameRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private MasterDao masterDao;

    @Override
    public List<GeneralParamRes> getGeneralParams(GeneralParamReq id) {
        List<GeneralParamRes> generalParamRes = new ArrayList<>();
        if (CommonUtils.isBlank(id.getParamType())) {
            throw new ValidationException(ErrorResponseConstant.INVALID_PARAM_TYPE_CD, ErrorResponseConstant.INVALID_PARAM_TYPE);
        }

        if (CommonUtils.isBlank(id.getParamVal())) {
            List<GeneralParameter> allByParamType = generalParamRepository.findAllByParamTypeAndStatus(id.getParamType().toUpperCase(), "A");
            for (GeneralParameter gp : allByParamType) {
                generalParamRes.add(prepareResponse(gp));
            }
        } else {
            GeneralParameterId parameterId = new GeneralParameterId();
            parameterId.setParamType(id.getParamType().toUpperCase());
            parameterId.setParamVal(id.getParamVal());
            Optional<GeneralParameter> byId = generalParamRepository.findById(parameterId);
            if (byId.isPresent()) {
                GeneralParameter gp = byId.get();
                generalParamRes.add(prepareResponse(gp));
            }
        }
        return generalParamRes;
    }

    @Override
    public Object getAllItemNames() {
        return itemNameRepository.findAll();
    }

    @Override
    public Object getAllItemType() {
        return itemTypeRepository.findAll();
    }

    @Override
    public Object getAllDiscipline() {
        return disciplineRepository.findAll();
    }

    @Override
    public Object getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public Object getAllItemNamesByDtls(ItemNameReq req) {
        return itemNameRepository.findAllByCategoryCodeAndSubCategoryCodeAndDesciplineCodeAndTypeCode(req.getCategoryCode(), req.getSubCategoryCode(), req.getDisciplineCode(), req.getTypeCode());
    }

    @Override
    public Object getAllItemTypeByDtls(ItemTypeReq req) {
        return itemTypeRepository.findAllByCategoryCodeAndSubCategoryCode(req.getCategoryCode(), req.getSubCategoryCode());
    }

    @Override
    public Object getAllDisciplineByDtls(DisciplineReq req) {
        return disciplineRepository.findAllByCategoryCodeAndSubCategoryCodeAndTypeCode(req.getCategoryCode(), req.getSubCategoryCode(), req.getTypeCode());
    }

    @Override
    public Object getAllSubCategoriesByDtls(SubCategoryReq req) {
        return subCategoryRepository.findAllByCategoryCode(req.getCategoryCode());
    }

    private GeneralParamRes prepareResponse(GeneralParameter gp) {
        GeneralParamRes res = new GeneralParamRes();
        res.setParamDesc(gp.getParamDesc());
        res.setParamType(gp.getId().getParamType());
        res.setParamVal(gp.getId().getParamVal());
        res.setParamStatus(gp.getStatus());
        return res;
    }

    @Transactional
    @Override
    public void saveGeneralParam(GeneralParamReq req) {
        if(StringUtils.isBlank(req.getParamDesc())){
            throw new ValidationException(ErrorResponseConstant.PARAM_DESC_EMPTY_CD, ErrorResponseConstant.PARAM_DESC_EMPTY_MSG);
        } else if(StringUtils.isNotBlank(req.getParamVal())) {
            Optional<GeneralParameter> byId = generalParamRepository.findById(new GeneralParameterId(req.getParamVal(), req.getParamType()));
            if (byId.isPresent() && "A".equalsIgnoreCase(byId.get().getStatus())) {
                throw new ValidationException(ErrorResponseConstant.PARAM_ALREADY_EXIST_CD, ErrorResponseConstant.PARAM_ALREADY_EXIST_MSG);
            }
        }else{
            String nextParamVal = getNextParamVal(req.getParamType());
            req.setParamVal(nextParamVal);
        }

        GeneralParameter generalParameter = new GeneralParameter();
        generalParameter.setOrgId(req.getOrgId());
        generalParameter.setParamDesc(req.getParamDesc());
        generalParameter.setId(new GeneralParameterId(req.getParamVal(), req.getParamType()));
        generalParameter.setStatus("A");
        generalParameter.setCreateUser(req.getCreateUser());
        generalParameter.setCreateDate(new Date());
        generalParamRepository.save(generalParameter);
    }

    @Override
    public void saveSubCategories(SaveSubCatReq r) {
        if (StringUtils.isAnyEmpty(r.getCategory(), r.getSubCategory(), r.getSubCategoryDesc())) {
            throw new ValidationException(ErrorResponseConstant.MANDATORY_FIELDS_CD, ErrorResponseConstant.MANDATORY_FIELDS_MSG);
        }
        List<SubCategoryMaster> itemLst = subCategoryRepository.findAllByCategoryCodeAndSubCategoryCode(r.getCategory(), r.getSubCategory());
        if (itemLst.size() > 0) {
            throw new ValidationException(ErrorResponseConstant.PARAM_ALREADY_EXIST_CD, ErrorResponseConstant.PARAM_ALREADY_EXIST_MSG);
        }

        SubCategoryMaster subCategoryMaster = new SubCategoryMaster();
        subCategoryMaster.setCategoryCode(r.getCategory());
        subCategoryMaster.setSubCategoryCode(r.getSubCategory());
        subCategoryMaster.setCreateUser(r.getCreateUser());
        subCategoryMaster.setSubCategoryDesc(r.getSubCategoryDesc());
        subCategoryMaster.setOrgId(r.getOrgId());
        subCategoryMaster.setCreateDt(new Date());
        subCategoryRepository.save(subCategoryMaster);

    }

    @Override
    public void saveItemType(SaveItemTypeReq r) {
        if (StringUtils.isAnyEmpty(r.getCategory(), r.getSubCategory(), r.getType())) {
            throw new ValidationException(ErrorResponseConstant.MANDATORY_FIELDS_CD, ErrorResponseConstant.MANDATORY_FIELDS_MSG);
        }
        List<ItemTypeMaster> itemLst = itemTypeRepository.findAllByCategoryCodeAndSubCategoryCodeAndTypeCode(r.getCategory(), r.getSubCategory(), r.getType());
        if (itemLst.size() > 0) {
            throw new ValidationException(ErrorResponseConstant.PARAM_ALREADY_EXIST_CD, ErrorResponseConstant.PARAM_ALREADY_EXIST_MSG);
        }

        ItemTypeMaster master = new ItemTypeMaster();
        master.setCategoryCode(r.getCategory());
        master.setCreateUser(r.getCreateUser());
        master.setCreateDt(new Date());
        master.setTypeCode(r.getType());
        master.setSubCategoryCode(r.getSubCategory());
        master.setOrgId(r.getOrgId());
        master.setTypeDesc(r.getTypeDesc());
        itemTypeRepository.save(master);
    }

    @Override
    public void saveItemName(SaveItemNameReq req) {
        if (StringUtils.isAnyEmpty(req.getItemName(), req.getItemNameCode(), req.getDisciplineCode(), req.getCategoryCode(), req.getSubCategoryCode(), req.getTypeCode())) {
            throw new ValidationException(ErrorResponseConstant.MANDATORY_FIELDS_CD, ErrorResponseConstant.MANDATORY_FIELDS_MSG);
        }
        List<ItemNameMaster> itemLst = itemNameRepository.findAllByCategoryCodeAndSubCategoryCodeAndDesciplineCodeAndTypeCodeAndItemNameCode(req.getCategoryCode(), req.getSubCategoryCode(), req.getDisciplineCode(), req.getTypeCode(), req.getItemNameCode());
        if (itemLst.size() > 0) {
            throw new ValidationException(ErrorResponseConstant.PARAM_ALREADY_EXIST_CD, ErrorResponseConstant.PARAM_ALREADY_EXIST_MSG);
        }
        ItemNameMaster master = new ItemNameMaster();
        master.setCategoryCode(req.getCategoryCode());
        master.setItemName(req.getItemName());
        master.setItemNameCode(req.getItemNameCode());
        master.setSubCategoryCode(req.getSubCategoryCode());
        master.setDesciplineCode(req.getDisciplineCode());
        master.setTypeCode(req.getTypeCode());
        master.setOrgId(req.getOrgId());
        master.setCreateDt(new Date());
        master.setCreateUser(req.getCreateUser());
        itemNameRepository.save(master);
    }

    @Override
    public void saveDiscipline(SaveDiscReq r) {
        if (StringUtils.isAnyEmpty(r.getDisciplines(), r.getDisciplinesName(), r.getCategory(), r.getSubCategory(), r.getType())) {
            throw new ValidationException(ErrorResponseConstant.MANDATORY_FIELDS_CD, ErrorResponseConstant.MANDATORY_FIELDS_MSG);
        }
        List<DisciplineMaster> itemLst = disciplineRepository.findAllByCategoryCodeAndSubCategoryCodeAndTypeCodeAndDisciplineCode(r.getCategory(), r.getSubCategory(), r.getType(), r.getDisciplines());
        if (itemLst.size() > 0) {
            throw new ValidationException(ErrorResponseConstant.PARAM_ALREADY_EXIST_CD, ErrorResponseConstant.PARAM_ALREADY_EXIST_MSG);
        }

        DisciplineMaster master = new DisciplineMaster();
        master.setCategoryCode(r.getCategory());
        master.setSubCategoryCode(r.getSubCategory());
        master.setCreateDt(new Date());
        master.setCreateUser(r.getCreateUser());
        master.setDisciplineCode(r.getDisciplines());
        master.setDisciplineName(r.getDisciplinesName());
        master.setTypeCode(r.getType());
        master.setOrgId(r.getOrgId());
        disciplineRepository.save(master);
    }

    @Override
    public String getNextParamVal(String paramType) {
        String nextParamVal = masterDao.getNextParamVal(paramType);
        int length = nextParamVal.length();
        Long crntVal = CommonUtils.getLong(nextParamVal);
        if (crntVal == null) {
            throw new ValidationException(ErrorResponseConstant.MAX_VAL_NON_NUMERIC_CD, ErrorResponseConstant.MAX_VAL_NON_NUMERIC_MSG);
        }

        String nxtVal = String.valueOf(crntVal + 1);
        int lengthN = nxtVal.length();
        if (nxtVal.length() < length) {
            for(int i = 0 ; i < length - lengthN ; i++){
                nxtVal = "0"+nxtVal;
            }
        }
        return nxtVal;
    }
}
