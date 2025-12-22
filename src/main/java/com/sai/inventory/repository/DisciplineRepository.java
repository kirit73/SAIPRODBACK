package com.sai.inventory.repository;

import com.sai.inventory.entity.DisciplineMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineMaster, Long> {
    List<DisciplineMaster> findAllByCategoryCodeAndSubCategoryCodeAndTypeCode(String categoryCode, String subCategoryCode, String typeCode);

    List<DisciplineMaster> findAllByCategoryCodeAndSubCategoryCodeAndTypeCodeAndDisciplineCode(String category, String subCategory, String type, String disciplines);
}
