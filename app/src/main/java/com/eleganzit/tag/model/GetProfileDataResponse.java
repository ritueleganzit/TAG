package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfileDataResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("personaldata")
    @Expose
    private List<Personaldatum> personaldata = null;
    @SerializedName("education")
    @Expose
    private List<Education> education = null;
    @SerializedName("workdata")
    @Expose
    private List<Workdata> workdata = null;
    @SerializedName("preferance_data")
    @Expose
    private List<Preferancedata> preferanceData = null;

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

    public List<Personaldatum> getPersonaldata() {
        return personaldata;
    }

    public void setPersonaldata(List<Personaldatum> personaldata) {
        this.personaldata = personaldata;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Workdata> getWorkdata() {
        return workdata;
    }

    public void setWorkdata(List<Workdata> workdata) {
        this.workdata = workdata;
    }

    public List<Preferancedata> getPreferanceData() {
        return preferanceData;
    }

    public void setPreferanceData(List<Preferancedata> preferanceData) {
        this.preferanceData = preferanceData;
    }


}
