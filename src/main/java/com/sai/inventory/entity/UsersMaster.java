package com.sai.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_master")
public class UsersMaster extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @Column(name = "USER_CD")
    private String userCd;

    @Column(name = "USER_TYPE")
    private String userType;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DEPARTMENT")
    private String department;
    @JsonIgnore
    @Column(name = "CHANGE_PWD_DATE")
    private Date changePasswordDate;
    @JsonIgnore
    @Column(name = "CHANGE_PWD_BY")
    private String changePasswordBy;
    @JsonIgnore
    @Column(name = "LAST_LOGIN_DT")
    private Date lastLoginDate;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getChangePasswordDate() {
        return changePasswordDate;
    }

    public void setChangePasswordDate(Date changePasswordDate) {
        this.changePasswordDate = changePasswordDate;
    }

    public String getChangePasswordBy() {
        return changePasswordBy;
    }

    public void setChangePasswordBy(String changePasswordBy) {
        this.changePasswordBy = changePasswordBy;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
