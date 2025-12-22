package com.sai.inventory.repository;

import com.sai.inventory.entity.SubCategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryMaster, Long> {
    List<SubCategoryMaster> findAllByCategoryCode(String categoryCode);

    List<SubCategoryMaster> findAllByCategoryCodeAndSubCategoryCode(String category, String subCategory);
}
