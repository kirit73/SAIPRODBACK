package com.sai.inventory.repository;

import com.sai.inventory.entity.ItemTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemTypeMaster, Long> {
    List<ItemTypeMaster> findAllByCategoryCodeAndSubCategoryCode(String categoryCode, String subCategoryCode);

    List<ItemTypeMaster> findAllByCategoryCodeAndSubCategoryCodeAndTypeCode(String category, String subCategory, String type);
}
