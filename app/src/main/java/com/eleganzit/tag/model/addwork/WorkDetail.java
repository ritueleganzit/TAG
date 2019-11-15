package com.eleganzit.tag.model.addwork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkDetail {
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("current_job_que")
    @Expose
    private String currentJobQue;

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

    public String getCurrentJobQue() {
        return currentJobQue;
    }

    public void setCurrentJobQue(String currentJobQue) {
        this.currentJobQue = currentJobQue;
    }

}
