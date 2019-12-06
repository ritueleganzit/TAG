package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facilities {
    @SerializedName("facility_id")
    @Expose
    private Integer facilityId;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("facility_icon")
    @Expose
    private String facilityIcon;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("isOther")
    @Expose
    private Integer isOther;

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityIcon() {
        return facilityIcon;
    }

    public void setFacilityIcon(String facilityIcon) {
        this.facilityIcon = facilityIcon;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsOther() {
        return isOther;
    }

    public void setIsOther(Integer isOther) {
        this.isOther = isOther;
    }


}
