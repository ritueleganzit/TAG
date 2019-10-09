package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollegeResult {

    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("college_city")
    @Expose
    private String collegeCity;
    @SerializedName("college_state")
    @Expose
    private String collegeState;
    @SerializedName("college_country")
    @Expose
    private String collegeCountry;
    @SerializedName("college_image")
    @Expose
    private String collegeImage;
    @SerializedName("isFeatured")
    @Expose
    private Integer isFeatured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("isUniversity")
    @Expose
    private Integer isUniversity;
    @SerializedName("isApproval")
    @Expose
    private Integer isApproval;
    @SerializedName("exam_accepted")
    @Expose
    private String examAccepted;
    @SerializedName("fees")
    @Expose
    private String fees;
    @SerializedName("accreditation")
    @Expose
    private String accreditation;
    @SerializedName("placement")
    @Expose
    private String placement;
    @SerializedName("ownership")
    @Expose
    private String ownership;
    @SerializedName("college_location")
    @Expose
    private String collegeLocation;
    @SerializedName("broucher")
    @Expose
    private String broucher;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("years")
    @Expose
    private String years;
    @SerializedName("students_no")
    @Expose
    private String studentsNo;
    @SerializedName("college_type")
    @Expose
    private String collegeType;
    @SerializedName("ratings")
    @Expose
    private Double ratings;
    @SerializedName("admission")
    @Expose
    private String admission;
    @SerializedName("college_desc")
    @Expose
    private String collegeDesc;
    @SerializedName("university_name")
    @Expose
    private String universityName;
    @SerializedName("isTop")
    @Expose
    private Integer isTop;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("approved_by")
    @Expose
    private String approvedBy;

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

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public String getCollegeState() {
        return collegeState;
    }

    public void setCollegeState(String collegeState) {
        this.collegeState = collegeState;
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

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getIsUniversity() {
        return isUniversity;
    }

    public void setIsUniversity(Integer isUniversity) {
        this.isUniversity = isUniversity;
    }

    public Integer getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Integer isApproval) {
        this.isApproval = isApproval;
    }

    public String getExamAccepted() {
        return examAccepted;
    }

    public void setExamAccepted(String examAccepted) {
        this.examAccepted = examAccepted;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
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

    public String getCollegeLocation() {
        return collegeLocation;
    }

    public void setCollegeLocation(String collegeLocation) {
        this.collegeLocation = collegeLocation;
    }

    public String getBroucher() {
        return broucher;
    }

    public void setBroucher(String broucher) {
        this.broucher = broucher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getStudentsNo() {
        return studentsNo;
    }

    public void setStudentsNo(String studentsNo) {
        this.studentsNo = studentsNo;
    }

    public String getCollegeType() {
        return collegeType;
    }

    public void setCollegeType(String collegeType) {
        this.collegeType = collegeType;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getCollegeDesc() {
        return collegeDesc;
    }

    public void setCollegeDesc(String collegeDesc) {
        this.collegeDesc = collegeDesc;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }


}
