package com.sai.inventory.repository;

import com.sai.inventory.entity.GatePassDtls;
import com.sai.inventory.entity.ProcessDtls;
import com.sai.inventory.util.ProcessStageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatePassDtlsRepository extends JpaRepository<GatePassDtls, Long> {

    List<GatePassDtls> findAllByProcessIdAndGatePassType(Long processId, String processStage);

    List<GatePassDtls> findAllByProcessId(Long aLong);
}
