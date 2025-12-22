package com.sai.inventory.repository;

import com.sai.inventory.entity.InspectionReportDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionReportDtlsRepository extends JpaRepository<InspectionReportDtls, Long> {

    List<InspectionReportDtls> findAllByProcessId(Long processId);
}
