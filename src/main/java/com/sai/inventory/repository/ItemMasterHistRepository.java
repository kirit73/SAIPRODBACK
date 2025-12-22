package com.sai.inventory.repository;

import com.sai.inventory.entity.ItemCodeMasterHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ItemMasterHistRepository extends JpaRepository<ItemCodeMasterHist, Long> {
    List<ItemCodeMasterHist> findAllByStatus(String status);

    List<ItemCodeMasterHist> findAllByItemMasterCdAndStatus(String itemCode, String status);

    List<ItemCodeMasterHist> findAllByIdAndStatus(Long id, String a);

    Optional<ItemCodeMasterHist> findAllByItemMasterCdAndLocatorIdAndStatus(String itemCode, Long locatorId, String status);

    List<ItemCodeMasterHist> findAllByItemMasterCd(String itemCode);

    Optional<ItemCodeMasterHist> findByIdAndStatus(Long itemMasterId, String status);

    // NEW METHODS - PROPER FIX WITH DEFAULT METHODS

    /**
     * Get all transactions for multiple items within a date range
     * Note: Change 'updateDate' to 'updateDt' if that's your entity field name
     */
    @Query("SELECT imh FROM ItemCodeMasterHist imh WHERE imh.itemMasterCd IN :itemCodes " +
           "AND imh.updateDate >= :fromDate AND imh.updateDate <= :toDate " +
           "ORDER BY imh.itemMasterCd, imh.updateDate")
    List<ItemCodeMasterHist> findByItemMasterCdInAndUpdateDateBetween(
        @Param("itemCodes") List<String> itemCodes,
        @Param("fromDate") Date fromDate,
        @Param("toDate") Date toDate
    );

    /**
     * Get all transactions with orgId parameter (ignored since column doesn't exist)
     * Uses default method to call the base method
     */
    default List<ItemCodeMasterHist> findByItemMasterCdInAndUpdateDateBetweenAndOrgId(
        List<String> itemCodes,
        Date fromDate,
        Date toDate,
        Long orgId
    ) {
        // Ignore orgId and call the base method
        return findByItemMasterCdInAndUpdateDateBetween(itemCodes, fromDate, toDate);
    }
}