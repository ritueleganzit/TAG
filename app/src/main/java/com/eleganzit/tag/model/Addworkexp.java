package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addworkexp {

    @SerializedName("work_id")
    @Expose
    private Integer workId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("current_job")
    @Expose
    private String currentJob;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

}
