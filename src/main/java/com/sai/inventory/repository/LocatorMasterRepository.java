package com.sai.inventory.repository;

import com.sai.inventory.entity.DepartmentMaster;
import com.sai.inventory.entity.LocatorMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocatorMasterRepository extends JpaRepository<LocatorMaster, Long> {
    List<LocatorMaster> findAllByStatus(String status);
}
