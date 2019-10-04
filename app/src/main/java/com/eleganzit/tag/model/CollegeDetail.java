package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollegeDetail {
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("university_name")
    @Expose
    private String universityName;
    @SerializedName("isUniversity")
    @Expose
    private Integer isUniversity;
    @SerializedName("college_city")
    @Expose
    private String collegeCity;
    @SerializedName("college_country")
    @Expose
    private String collegeCountry;
    @SerializedName("college_image")
    @Expose
    private String collegeImage;
    @SerializedName("accreditation")
    @Expose
    private String accreditation;
    @SerializedName("approved_by")
    @Expose
    private String approvedBy;
    @SerializedName("placement")
    @Expose
    private String placement;
    @SerializedName("ownership")
    @Expose
    private String ownership;
    @SerializedName("ratings")
    @Expose
    private Double ratings;
    @SerializedName("course_duration")
    @Expose
    private String courseDuration;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;
    @SerializedName("rank")
    @Expose
    private String rank;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Integer getIsUniversity() {
        return isUniversity;
    }

    public void setIsUniversity(Integer isUniversity) {
        this.isUniversity = isUniversity;
    }

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public String getCollegeCountry() {
        return collegeCountry;
    }

    public void setCollegeCountry(String collegeCountry) {
        this.collegeCountry = collegeCountry;
    }

    public String getCollegeImage() {
        return collegeImage;
    }

    public void setCollegeImage(String collegeImage) {
        this.collegeImage = collegeImage;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
