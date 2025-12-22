package com.sai.inventory.repository;

import com.sai.inventory.entity.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpMasterRepository extends JpaRepository<EmployeeMaster, Long> {
    List<EmployeeMaster> findAllByStatus(String status);

    List<EmployeeMaster> findAllByStatusAndOrganizationId(String status, Long orgId);
}
