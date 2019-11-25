package com.eleganzit.tag.model.homecourse;

import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.newhome.CourseResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cource_fee_data")
    @Expose
    private List<CourseResult> courceFeeData = null;

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

    public List<CourseResult> getCourceFeeData() {
        return courceFeeData;
    }

    public void setCourceFeeData(List<CourseResult> courceFeeData) {
        this.courceFeeData = courceFeeData;
    }

}
