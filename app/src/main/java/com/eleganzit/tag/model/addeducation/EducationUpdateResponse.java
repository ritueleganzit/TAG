package com.eleganzit.tag.model.addeducation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EducationUpdateResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("return_data")
    @Expose
    private List<ReturnDatum> returnData = null;

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

    public List<ReturnDatum> getReturnData() {
        return returnData;
    }

    public void setReturnData(List<ReturnDatum> returnData) {
        this.returnData = returnData;
    }

}
