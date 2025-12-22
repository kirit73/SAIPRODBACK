package com.sai.inventory.util;

import com.sai.inventory.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CachingUtil {

    private static Map itemTypeCache = new HashMap();
    private static Map discplineCache = new HashMap();
    private static Map subCategoryCache = new HashMap();
    private static Map generalParamCache = new HashMap();
    private static Map localtorCache = new HashMap();
    private static Map uomCache = new HashMap();

    public static List<ItemTypeMaster> fetchItemTypeMaster(String category, String subCategory, String type) {
        String key = category + subCategory + type;
        if (itemTypeCache.containsKey(key)) {
            return (List<ItemTypeMaster>) itemTypeCache.get(key);
        }
        return null;
    }

    public static List<DisciplineMaster> fetchDisciplineMaster(String category, String subCategory, String type, String disciplines) {
        String key = category + subCategory + type + disciplines;
        if (discplineCache.containsKey(key)) {
            return (List<DisciplineMaster>) discplineCache.get(key);
        }
        return null;
    }

    public static List<SubCategoryMaster> fetchSubCategoryMaster(String category, String subCategory) {
        String key = category + subCategory;
        if (subCategoryCache.containsKey(key)) {
            return (List<SubCategoryMaster>) subCategoryCache.get(key);
        }
        return null;
    }

    public static Optional<GeneralParameter> fetchGeneralParamMaster(String id, String type) {
        String key = type + id;
        if (generalParamCache.containsKey(key)) {
            GeneralParameter generalParameter = (GeneralParameter) generalParamCache.get(key);
            return Optional.ofNullable(generalParameter);
        }
        return Optional.ofNullable(null);
    }

    public static void addItemTypeMaster(String category, String subCategory, String type, List<ItemTypeMaster> itemType) {
        if (itemType == null || itemType.isEmpty()) {
            return;
        }
        String key = category + subCategory + type;
        itemTypeCache.put(key, itemType);
    }

    public static void addDisciplineMaster(String category, String subCategory, String type, String disciplines, List<DisciplineMaster> discType) {
        if (discType == null || discType.isEmpty()) {
            return;
        }
        String key = category + subCategory + type + disciplines;
        discplineCache.put(key, discType);
    }

    public static void addSubCategoryMaster(String category, String subCategory, List<SubCategoryMaster> subCat) {
        if (subCat == null || subCat.isEmpty()) {
            return;
        }
        String key = category + subCategory;
        subCategoryCache.put(key, subCat);
    }

    public static void addGeneralParamMaster(String id, String type, GeneralParameter gParam) {
        String key = type + id;
        generalParamCache.put(key, gParam);
    }

    public static UOMMaster fetchUOMMaster(String uomId) {
        if (uomCache.containsKey(uomId)) {
            return (UOMMaster) uomCache.get(uomId);
        }
        return null;
    }

    public static LocatorMaster fetchLocatorMaster(String locatorId) {
        if (localtorCache.containsKey(locatorId)) {
            return (LocatorMaster) localtorCache.get(locatorId);
        }
        return null;
    }

    public static void addUOMMaster(String uomId, UOMMaster master) {
        uomCache.put(uomId, master);
    }

    public static void addLocatorMaster(String id, LocatorMaster master) {
        localtorCache.put(id, master);
    }
}
