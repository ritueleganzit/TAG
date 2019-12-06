package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolInfo {
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("board_name")
    @Expose
    private String boardName;
    @SerializedName("school_city")
    @Expose
    private String schoolCity;
    @SerializedName("school_state")
    @Expose
    private String schoolState;
    @SerializedName("school_image")
    @Expose
    private String schoolImage;
    @SerializedName("isFeatured")
    @Expose
    private Integer isFeatured;
    @SerializedName("student_no")
    @Expose
    private Integer studentNo;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;
    @SerializedName("admission")
    @Expose
    private String admission;
    @SerializedName("ratings")
    @Expose
    private Integer ratings;
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
    private Integer rank;
    @SerializedName("ownership")
    @Expose
    private String ownership;
    @SerializedName("approval")
    @Expose
    private Integer approval;
    @SerializedName("accreditation")
    @Expose
    private String accreditation;
    @SerializedName("facilities")
    @Expose
    private String facilities;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("approved_by")
    @Expose
    private String approvedBy;
    @SerializedName("school_location")
    @Expose
    private String schoolLocation;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("brochure_url")
    @Expose
    private String brochureUrl;
    @SerializedName("admission_eleg_criteria")
    @Expose
    private String admissionElegCriteria;
    @SerializedName("school_overview")
    @Expose
    private String schoolOverview;
    @SerializedName("scholarship")
    @Expose
    private String scholarship;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("school_desc")
    @Expose
    private String schoolDesc;
    @SerializedName("web")
    @Expose

    private String web;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("school_mobile")
    @Expose
    private String schoolMobile;
    @SerializedName("school_map")
    @Expose
    private String schoolMap;
    @SerializedName("isApproved")
    @Expose
    private Integer isApproved;
    @SerializedName("isRejected")
    @Expose
    private Integer isRejected;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("application_charge")
    @Expose
    private String applicationCharge;
    @SerializedName("service_charge")
    @Expose
    private String serviceCharge;
    @SerializedName("facility_id")
    @Expose
    private String facilityId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(String schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolImage() {
        return schoolImage;
    }

    public void setSchoolImage(String schoolImage) {
        this.schoolImage = schoolImage;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Integer studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrochureUrl() {
        return brochureUrl;
    }

    public void setBrochureUrl(String brochureUrl) {
        this.brochureUrl = brochureUrl;
    }

    public String getAdmissionElegCriteria() {
        return admissionElegCriteria;
    }

    public void setAdmissionElegCriteria(String admissionElegCriteria) {
        this.admissionElegCriteria = admissionElegCriteria;
    }

    public String getSchoolOverview() {
        return schoolOverview;
    }

    public void setSchoolOverview(String schoolOverview) {
        this.schoolOverview = schoolOverview;
    }

    public String getScholarship() {
        return scholarship;
    }

    public void setScholarship(String scholarship) {
        this.scholarship = scholarship;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolDesc() {
        return schoolDesc;
    }

    public void setSchoolDesc(String schoolDesc) {
        this.schoolDesc = schoolDesc;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchoolMobile() {
        return schoolMobile;
    }

    public void setSchoolMobile(String schoolMobile) {
        this.schoolMobile = schoolMobile;
    }

    public String getSchoolMap() {
        return schoolMap;
    }

    public void setSchoolMap(String schoolMap) {
        this.schoolMap = schoolMap;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(Integer isRejected) {
        this.isRejected = isRejected;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getApplicationCharge() {
        return applicationCharge;
    }

    public void setApplicationCharge(String applicationCharge) {
        this.applicationCharge = applicationCharge;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
}
