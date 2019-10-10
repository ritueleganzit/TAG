package com.eleganzit.tag.model.appliedcollege;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDetail {
    @SerializedName("applied_course_name")
    @Expose
    private String appliedCourseName;
    @SerializedName("applied_specialization_name")
    @Expose
    private String appliedSpecializationName;

    public String getAppliedCourseName() {
        return appliedCourseName;
    }

    public void setAppliedCourseName(String appliedCourseName) {
        this.appliedCourseName = appliedCourseName;
    }

    public String getAppliedSpecializationName() {
        return appliedSpecializationName;
    }

    public void setAppliedSpecializationName(String appliedSpecializationName) {
        this.appliedSpecializationName = appliedSpecializationName;
    }

}
