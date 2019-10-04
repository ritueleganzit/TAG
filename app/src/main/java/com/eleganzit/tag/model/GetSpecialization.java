package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetSpecialization implements Serializable {
    @SerializedName("specialization_id")
    @Expose
    private Integer specializationId;
    @SerializedName("cource_name")
    @Expose
    private String courceName;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("eligibility")
    @Expose
    private String eligibility;
    @SerializedName("cirrculum")
    @Expose
    private String cirrculum;

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getCirrculum() {
        return cirrculum;
    }

    public void setCirrculum(String cirrculum) {
        this.cirrculum = cirrculum;
    }

}
