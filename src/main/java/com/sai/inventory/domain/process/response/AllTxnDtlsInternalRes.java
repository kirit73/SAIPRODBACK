package com.sai.inventory.domain.process.response;

import com.sai.inventory.domain.ProcessItemMapDomain;
import com.sai.inventory.entity.ProcessItemMap;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AllTxnDtlsInternalRes {

    public AllTxnDtlsInternalRes(ProcessBaseResponse data, List<ProcessItemMap> itemList){
        this.data = data;
        this.itemList = itemList;
    }

    public AllTxnDtlsInternalRes(ProcessBaseResponse data, List<ProcessItemMapDomain> itemList, boolean a){
        this.data = data;
        this.itemList = new ArrayList<>();
        for (ProcessItemMapDomain d: itemList ) {
            ProcessItemMap map = new ProcessItemMap();
            BeanUtils.copyProperties(d, map);
            this.itemList.add(map);
        }
    }

    private List<ProcessItemMap> itemList;
    private ProcessBaseResponse data;

    public List<ProcessItemMap> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProcessItemMap> itemList) {
        this.itemList = itemList;
    }

    public ProcessBaseResponse getData() {
        return data;
    }

    public void setData(ProcessBaseResponse data) {
        this.data = data;
    }
}
