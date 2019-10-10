package com.eleganzit.tag.model.homecourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datastream {
    @SerializedName("list_id")
    @Expose
    private String listId;
    @SerializedName("college_id")
    @Expose
    private String collegeId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;
    @SerializedName("accredition")
    @Expose
    private String accredition;
    @SerializedName("course_demand")
    @Expose
    private String courseDemand;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;
    @SerializedName("approval")
    @Expose
    private String approval;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("years")
    @Expose
    private String years;
    @SerializedName("course_fees")
    @Expose
    private String courseFees;
    @SerializedName("course_rating")
    @Expose
    private String courseRating;
    @SerializedName("cource_admission")
    @Expose
    private String courceAdmission;
    @SerializedName("cource_offer")
    @Expose
    private String courceOffer;
    @SerializedName("cource_accredition")
    @Expose
    private String courceAccredition;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
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

    public String getAccredition() {
        return accredition;
    }

    public void setAccredition(String accredition) {
        this.accredition = accredition;
    }

    public String getCourseDemand() {
        return courseDemand;
    }

    public void setCourseDemand(String courseDemand) {
        this.courseDemand = courseDemand;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(String courseFees) {
        this.courseFees = courseFees;
    }

    public String getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(String courseRating) {
        this.courseRating = courseRating;
    }

    public String getCourceAdmission() {
        return courceAdmission;
    }

    public void setCourceAdmission(String courceAdmission) {
        this.courceAdmission = courceAdmission;
    }

    public String getCourceOffer() {
        return courceOffer;
    }

    public void setCourceOffer(String courceOffer) {
        this.courceOffer = courceOffer;
    }

    public String getCourceAccredition() {
        return courceAccredition;
    }

    public void setCourceAccredition(String courceAccredition) {
        this.courceAccredition = courceAccredition;
    }

}
