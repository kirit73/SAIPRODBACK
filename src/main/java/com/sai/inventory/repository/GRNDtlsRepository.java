package com.sai.inventory.repository;

import com.sai.inventory.entity.GRNDtls;
import com.sai.inventory.entity.ReturnNoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GRNDtlsRepository extends JpaRepository<GRNDtls, Long> {

    List<GRNDtls> findAllByProcessId(Long processId);
}
