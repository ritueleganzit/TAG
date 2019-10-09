package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopCollegeResponse
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<CollegeResult> result = null;
    @SerializedName("college_count")
    @Expose
    private Integer collegeCount;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CollegeResult> getResult() {
        return result;
    }

    public void setResult(List<CollegeResult> result) {
        this.result = result;
    }

    public Integer getCollegeCount() {
        return collegeCount;
    }

    public void setCollegeCount(Integer collegeCount) {
        this.collegeCount = collegeCount;
    }


}
