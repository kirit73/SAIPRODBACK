package com.sai.inventory.repository;

import com.sai.inventory.entity.IssueNoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueNoteDtlsRepository extends JpaRepository<IssueNoteDetails, Long> {


    List<IssueNoteDetails> findAllByProcessId(Long processId);
}
