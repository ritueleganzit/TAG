package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseList {
    @SerializedName("cource_id")
    @Expose
    private Integer courceId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("area_of_interest")
    @Expose
    private Integer areaOfInterest;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;

    public Integer getCourceId() {
        return courceId;
    }

    public void setCourceId(Integer courceId) {
        this.courceId = courceId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(Integer areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
