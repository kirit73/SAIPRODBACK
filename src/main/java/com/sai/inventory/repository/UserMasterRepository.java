package com.sai.inventory.repository;

import com.sai.inventory.entity.UsersMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMasterRepository extends JpaRepository<UsersMaster, Long> {
    List<UsersMaster> findAllByStatus(String status);
    List<UsersMaster> findAllByUserCd(String userCd);

    Optional<UsersMaster> findByUserCd(String username);
}
