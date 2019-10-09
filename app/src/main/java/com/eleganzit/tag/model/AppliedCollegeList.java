package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppliedCollegeList {
    @SerializedName("application_id")
    @Expose
    private Integer applicationId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ratings")
    @Expose
    private double ratings;
    @SerializedName("application_approved")
    @Expose
    private Integer applicationApproved;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("college_city")
    @Expose
    private String collegeCity;
    @SerializedName("course_rating")
    @Expose
    private Integer courseRating;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public Integer getApplicationApproved() {
        return applicationApproved;
    }

    public void setApplicationApproved(Integer applicationApproved) {
        this.applicationApproved = applicationApproved;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public Integer getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(Integer courseRating) {
        this.courseRating = courseRating;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

}
