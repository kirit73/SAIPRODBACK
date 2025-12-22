package com.sai.inventory.repository;

import com.sai.inventory.entity.UOMMaster;
import com.sai.inventory.entity.VendorMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorMasterRepository extends JpaRepository<VendorMaster, Long> {
    List<VendorMaster> findAllByStatus(String status);
}
