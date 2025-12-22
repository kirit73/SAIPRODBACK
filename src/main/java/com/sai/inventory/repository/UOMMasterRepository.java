package com.sai.inventory.repository;

import com.sai.inventory.entity.UOMMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UOMMasterRepository extends JpaRepository<UOMMaster, Long> {
    List<UOMMaster> findAllByStatus(String status);
}
