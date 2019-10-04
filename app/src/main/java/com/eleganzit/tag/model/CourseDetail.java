package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDetail {
    @SerializedName("list_id")
    @Expose
    private Integer listId;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
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
    private Integer rank;
    @SerializedName("years")
    @Expose
    private String years;
    @SerializedName("course_fees")
    @Expose
    private String courseFees;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
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


}
