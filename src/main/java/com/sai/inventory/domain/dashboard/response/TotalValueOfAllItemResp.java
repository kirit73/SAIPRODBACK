package com.sai.inventory.domain.dashboard.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.inventory.domain.master.response.ItemCodeMasterResp;

import java.math.BigDecimal;
import java.util.List;

public class TotalValueOfAllItemResp {

    @JsonIgnore
    private List<ItemCodeMasterResp> itemCodes;
    private BigDecimal totalItems;
    private BigDecimal totalValue;

    public List<ItemCodeMasterResp> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(List<ItemCodeMasterResp> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public BigDecimal getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(BigDecimal totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
