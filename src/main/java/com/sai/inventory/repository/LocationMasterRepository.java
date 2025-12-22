package com.sai.inventory.repository;

import com.sai.inventory.entity.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationMasterRepository extends JpaRepository<LocationMaster, Long> {

    List<LocationMaster> findAllByStatus(String status);

    List<LocationMaster> findAllByStatusAndOrgId(String status, Long orgId);
}
