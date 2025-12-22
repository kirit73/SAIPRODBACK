package com.sai.inventory.repository;

import com.sai.inventory.entity.ItemNameMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemNameRepository extends JpaRepository<ItemNameMaster, Long> {
    List<ItemNameMaster> findAllByCategoryCodeAndSubCategoryCodeAndDesciplineCodeAndTypeCode(String categoryCode, String subCategoryCode, String disciplineCode, String typeCode);

    List<ItemNameMaster> findAllByCategoryCodeAndSubCategoryCodeAndDesciplineCodeAndTypeCodeAndItemNameCode(String categoryCode, String subCategoryCode, String disciplineCode, String typeCode, String itemNameCode);

}
