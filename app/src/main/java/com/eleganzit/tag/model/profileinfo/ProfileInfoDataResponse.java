package com.eleganzit.tag.model.profileinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileInfoDataResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ProfileInfoData data;

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

    public ProfileInfoData getData() {
        return data;
    }

    public void setData(ProfileInfoData data) {
        this.data = data;
    }

}
