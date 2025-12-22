package com.sai.inventory.repository;

import com.sai.inventory.entity.DepartmentMaster;
import com.sai.inventory.entity.OrganizationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationMasterRepository extends JpaRepository<OrganizationMaster, Long> {

    @Query("from OrganizationMaster where status=:status and parentOrgId <> :parentOrgId")
    List<OrganizationMaster> findAllByStatus(String status, Long parentOrgId);

    List<OrganizationMaster> findAllByStatus(String status);

    List<OrganizationMaster> findAllByParentOrgId(Long parentOrgId);
}
