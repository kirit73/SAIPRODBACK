package com.sai.inventory.repository;

import com.sai.inventory.entity.GeneralParameter;
import com.sai.inventory.entity.GeneralParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GeneralParamRepository extends JpaRepository<GeneralParameter, GeneralParameterId> {
    @Query(value = "FROM GeneralParameter where id.paramType=:paramType and status=:status")
    List<GeneralParameter> findAllByParamTypeAndStatus(String paramType, String status);
}
