package com.eleganzit.tag.model.schoolstream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSchoolListResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("school_count")
    @Expose
    private Integer schoolCount;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getSchoolCount() {
        return schoolCount;
    }

    public void setSchoolCount(Integer schoolCount) {
        this.schoolCount = schoolCount;
    }

}
