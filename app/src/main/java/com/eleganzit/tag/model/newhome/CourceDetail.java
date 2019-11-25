package com.eleganzit.tag.model.newhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourceDetail {
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_count")
    @Expose
    private Integer courseCount;
    @SerializedName("course_result")
    @Expose
    private List<CourseResult> courseResult = null;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public List<CourseResult> getCourseResult() {
        return courseResult;
    }

    public void setCourseResult(List<CourseResult> courseResult) {
        this.courseResult = courseResult;
    }

}
