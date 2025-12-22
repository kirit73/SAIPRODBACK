package com.sai.inventory.repository;

import com.sai.inventory.entity.DepartmentMaster;
import com.sai.inventory.entity.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptMasterRepository extends JpaRepository<DepartmentMaster, Long> {
    List<DepartmentMaster> findAllByStatus(String status);
}
