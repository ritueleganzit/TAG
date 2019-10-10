package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollegeHomeResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("college_info")
    @Expose
    private CollegeInfo collegeInfo;
    @SerializedName("cource_fee_data")
    @Expose
    private List<CourceFee> courceFeeData = null;
    @SerializedName("gallery")
    @Expose
    private List<GalleryData> gallery = null;
    @SerializedName("facility")
    @Expose
    private List<FacilityData> facility = null;

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

    public CollegeInfo getCollegeInfo() {
        return collegeInfo;
    }

    public void setCollegeInfo(CollegeInfo collegeInfo) {
        this.collegeInfo = collegeInfo;
    }

    public List<CourceFee> getCourceFeeData() {
        return courceFeeData;
    }

    public void setCourceFeeData(List<CourceFee> courceFeeData) {
        this.courceFeeData = courceFeeData;
    }

    public List<GalleryData> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryData> gallery) {
        this.gallery = gallery;
    }

    public List<FacilityData> getFacility() {
        return facility;
    }

    public void setFacility(List<FacilityData> facility) {
        this.facility = facility;
    }


}
