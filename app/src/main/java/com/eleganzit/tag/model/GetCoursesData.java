package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCoursesData {

    @SerializedName("cource_id")
    @Expose
    private Integer courceId;
    @SerializedName("cource_name")
    @Expose
    private String courceName;
    @SerializedName("cource_description")
    @Expose
    private String courceDescription;
    @SerializedName("cource_details")
    @Expose
    private String courceDetails;
    @SerializedName("isTrending")
    @Expose
    private Integer isTrending;
    @SerializedName("available_seat")
    @Expose
    private Integer availableSeat;
    @SerializedName("cource_after")
    @Expose
    private String courceAfter;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("eligibility")
    @Expose
    private String eligibility;

    public Integer getCourceId() {
        return courceId;
    }

    public void setCourceId(Integer courceId) {
        this.courceId = courceId;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public String getCourceDescription() {
        return courceDescription;
    }

    public void setCourceDescription(String courceDescription) {
        this.courceDescription = courceDescription;
    }

    public String getCourceDetails() {
        return courceDetails;
    }

    public void setCourceDetails(String courceDetails) {
        this.courceDetails = courceDetails;
    }

    public Integer getIsTrending() {
        return isTrending;
    }

    public void setIsTrending(Integer isTrending) {
        this.isTrending = isTrending;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getCourceAfter() {
        return courceAfter;
    }

    public void setCourceAfter(String courceAfter) {
        this.courceAfter = courceAfter;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }
}
