package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacilityData {
    @SerializedName("college_facility_id")
    @Expose
    private String collegeFacilityId;
    @SerializedName("college_id")
    @Expose
    private String collegeId;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public String getCollegeFacilityId() {
        return collegeFacilityId;
    }

    public void setCollegeFacilityId(String collegeFacilityId) {
        this.collegeFacilityId = collegeFacilityId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
