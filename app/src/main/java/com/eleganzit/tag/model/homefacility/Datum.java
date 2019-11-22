package com.eleganzit.tag.model.homefacility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("facility_id")
    @Expose
    private Integer collegeFacilityId;

    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("facility_icon")
    @Expose
    private String facility_icon;

    public Integer getCollegeFacilityId() {
        return collegeFacilityId;
    }

    public void setCollegeFacilityId(Integer collegeFacilityId) {
        this.collegeFacilityId = collegeFacilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacility_icon() {
        return facility_icon;
    }

    public void setFacility_icon(String facility_icon) {
        this.facility_icon = facility_icon;
    }
}
