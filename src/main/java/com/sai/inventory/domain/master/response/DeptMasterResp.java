package com.sai.inventory.domain.master.response;

public class DeptMasterResp {

    public DeptMasterResp(Long deptMasterId) {
        this.deptMasterId = deptMasterId;
    }

    private Long deptMasterId;

    public Long getDeptMasterId() {
        return deptMasterId;
    }

    public void setDeptMasterId(Long deptMasterId) {
        this.deptMasterId = deptMasterId;
    }
}
