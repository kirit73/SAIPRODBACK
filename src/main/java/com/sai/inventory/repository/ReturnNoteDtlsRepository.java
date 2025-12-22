package com.sai.inventory.repository;

import com.sai.inventory.entity.ReturnNoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnNoteDtlsRepository extends JpaRepository<ReturnNoteDetails, Long> {

    List<ReturnNoteDetails> findAllByProcessId(Long processId);
}
