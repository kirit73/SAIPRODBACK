package com.sai.inventory.repository;

import com.sai.inventory.entity.AcceptRejectDtls;
import com.sai.inventory.entity.ReturnNoteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcptRejNoteDtlsRepository extends JpaRepository<AcceptRejectDtls, Long> {

    List<AcceptRejectDtls> findAllByProcessIdAndTypeOfNote(Long processId, String typeOfNote);

    List<AcceptRejectDtls> findAllByProcessId(Long processId);
}
