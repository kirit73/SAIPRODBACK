package com.sai.inventory.domain.master.request;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class EmployeeMasterReq {
    private Long employeeMasterId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String email;
    private Long organizationId;
    private String organizationName;
    private Long departmentId;
    private String departmentName;
    private Long salary;
    private String endDate;
    private String status;
    private String userId;

    @JsonIgnore
    private Date endDateInDate;

    public Long getEmployeeMasterId() {
        return employeeMasterId;
    }

    public void setEmployeeMasterId(Long employeeMasterId) {
        this.employeeMasterId = employeeMasterId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEndDateInDate(Date endDateInDate) {
        this.endDateInDate = endDateInDate;
    }

    public Date getEndDateInDate() {
        return endDateInDate;
    }
}
