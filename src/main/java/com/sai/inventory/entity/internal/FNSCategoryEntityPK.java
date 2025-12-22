package com.sai.inventory.entity.internal;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FNSCategoryEntityPK {

    @Column(name = "ITEM_MASTER_CD")
    private String itemMasterCd;
    @Column(name = "LOCATOR_ID")
    private Long locatorId;

    public String getItemMasterCd() {
        return itemMasterCd;
    }

    public void setItemMasterCd(String itemMasterCd) {
        this.itemMasterCd = itemMasterCd;
    }

    public Long getLocatorId() {
        return locatorId;
    }

    public void setLocatorId(Long locatorId) {
        this.locatorId = locatorId;
    }
}
