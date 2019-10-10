package com.eleganzit.tag.model.homefacility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("college_facility_id")
    @Expose
    private Integer collegeFacilityId;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public Integer getCollegeFacilityId() {
        return collegeFacilityId;
    }

    public void setCollegeFacilityId(Integer collegeFacilityId) {
        this.collegeFacilityId = collegeFacilityId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
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
