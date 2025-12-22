package com.sai.inventory.service;

import com.sai.inventory.domain.master.request.*;
import com.sai.inventory.domain.master.response.GeneralParamRes;

import java.util.List;

public interface GeneralParamsService {
    List<GeneralParamRes> getGeneralParams(GeneralParamReq id);

    Object getAllItemNames();

    Object getAllItemType();

    Object getAllDiscipline();

    Object getAllSubCategories();

    Object getAllItemNamesByDtls(ItemNameReq req);

    Object getAllItemTypeByDtls(ItemTypeReq req);

    Object getAllDisciplineByDtls(DisciplineReq req);

    Object getAllSubCategoriesByDtls(SubCategoryReq req);

    void saveGeneralParam(GeneralParamReq req);

    void saveSubCategories(SaveSubCatReq req);

    void saveItemType(SaveItemTypeReq req);

    void saveItemName(SaveItemNameReq req);

    void saveDiscipline(SaveDiscReq req);

    String getNextParamVal(String paramType);
}
