package com.sai.inventory.repository;

import com.sai.inventory.entity.InspectionReportDtlsNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionNewReportDtlsRepository extends JpaRepository<InspectionReportDtlsNew, Long> {

    List<InspectionReportDtlsNew> findAllByProcessId(Long processId);
}
