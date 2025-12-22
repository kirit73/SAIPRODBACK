package com.sai.inventory.repository;

import com.sai.inventory.entity.ProcessDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessDtlsRepository extends JpaRepository<ProcessDtls, Long> {

    List<ProcessDtls> findAllByOrgId(Long orgId);
}
