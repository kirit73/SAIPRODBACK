package com.sai.inventory.repository;

import com.sai.inventory.entity.ProcessItemMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessItemMapRepository extends JpaRepository<ProcessItemMap, Long> {
    List<ProcessItemMap> findAllByProcessIdAndProcessStage(Long processId, String processStage);
    List<ProcessItemMap> findAllByProcessIdAndProcessStageAndItemCode(Long processId, String processStage, String itemCode);
    List<ProcessItemMap> findAllByProcessIdAndUniqueId(Long processId, Long uniqueId);
}
