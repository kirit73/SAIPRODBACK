package com.sai.inventory.domain.process.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class StockLedgerRes {
    private List<ItemCodeMasterHistResp> txns;
    private Double initQuantity;
    private Double finalQuantity;
    private String createDate;
    private String createUser;
    private Double firstQuantity;

    public void setTxns(List<ItemCodeMasterHistResp> txns) {
        this.txns = txns;
    }

    public List<ItemCodeMasterHistResp> getTxns() {
        return txns;
    }

    public void setInitQuantity(Double initQuantity) {
        this.initQuantity = initQuantity;
    }

    public Double getInitQuantity() {
        return initQuantity;
    }

    public void setFinalQuantity(Double finalQuantity) {
        this.finalQuantity = finalQuantity;
    }

    public Double getFinalQuantity() {
        return finalQuantity;
    }


    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Double getFirstQuantity() {
        return firstQuantity;
    }

    public void setFirstQuantity(Double firstQuantity) {
        this.firstQuantity = firstQuantity;
    }
}
