package com.sai.inventory.repository;

import com.sai.inventory.entity.ItemCodeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemMasterRepository extends JpaRepository<ItemCodeMaster, Long> {
    List<ItemCodeMaster> findAllByStatus(String status);

    List<ItemCodeMaster> findAllByItemMasterCdAndStatus(String itemCode, String status);

    List<ItemCodeMaster> findAllByIdAndStatus(Long id, String a);

    Optional<ItemCodeMaster> findAllByItemMasterCdAndLocatorIdAndStatus(String itemCode, Long locatorId, String status);

    List<ItemCodeMaster> findAllByItemMasterCd(String itemCode);

    Optional<ItemCodeMaster> findByIdAndStatus(Long itemMasterId, String status);

    // NEW METHODS - PROPER FIX

    /**
     * Get all item codes by subcategory
     */
    @Query("SELECT DISTINCT im.itemMasterCd FROM ItemCodeMaster im WHERE im.subCategory = :subCategory AND im.status = 'A'")
    List<String> findItemCodesBySubCategory(@Param("subCategory") String subCategory);

    /**
     * Get all item codes by subcategory and orgId
     * Since orgId doesn't exist in entity, this just calls the subcategory method
     */
    default List<String> findItemCodesBySubCategoryAndOrgId(String subCategory, Long orgId) {
        // Ignore orgId and call the subcategory method
        return findItemCodesBySubCategory(subCategory);
    }

    /**
     * Get all active item codes
     */
    @Query("SELECT DISTINCT im.itemMasterCd FROM ItemCodeMaster im WHERE im.status = 'A'")
    List<String> findAllItemCodes();

    /**
     * Get all active item codes by orgId
     * Since orgId doesn't exist in entity, this just calls findAllItemCodes
     */
    default List<String> findAllItemCodesByOrgId(Long orgId) {
        // Ignore orgId and call the method to get all item codes
        return findAllItemCodes();
    }

    /**
     * Find item master by item code
     */
    @Query("SELECT im FROM ItemCodeMaster im WHERE im.itemMasterCd = :itemCode AND im.status = 'A' ORDER BY im.id DESC")
    ItemCodeMaster findByItemMasterCd(@Param("itemCode") String itemCode);
}