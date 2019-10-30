package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpResponse {
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("username")
    @Expose
    private String username;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
