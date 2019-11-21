package com.eleganzit.tag.model.coursedetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseDetails {
    @SerializedName("cource_id")
    @Expose
    private Integer courceId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("cource_description")
    @Expose
    private String courceDescription;
    @SerializedName("cource_details")
    @Expose
    private String courceDetails;
    @SerializedName("isTrending")
    @Expose
    private Integer isTrending;
    @SerializedName("available_seat")
    @Expose
    private Integer availableSeat;
    @SerializedName("course_category")
    @Expose
    private String courseCategory;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("eligibility")
    @Expose
    private String eligibility;
    @SerializedName("course_accreditation")
    @Expose
    private String courseAccreditation;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("specifications")
    @Expose
    private String specifications;
    @SerializedName("required_skill")
    @Expose
    private String requiredSkill;
    @SerializedName("eligibility_criteria")
    @Expose
    private String eligibilityCriteria;
    @SerializedName("course_curriculum")
    @Expose
    private String courseCurriculum;
    @SerializedName("job_profile_content")
    @Expose
    private String jobProfileContent;
    @SerializedName("course_icon")
    @Expose
    private String courseIcon;
    @SerializedName("course_guide")
    @Expose
    private String courseGuide;
    @SerializedName("area_of_interest")
    @Expose
    private Integer areaOfInterest;
    @SerializedName("exam_accepted")
    @Expose
    private String examAccepted;
    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("recruiter_id")
    @Expose
    private String recruiterId;
    @SerializedName("recruiter_content")
    @Expose
    private String recruiterContent;

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

    public String getCourceDescription() {
        return courceDescription;
    }

    public void setCourceDescription(String courceDescription) {
        this.courceDescription = courceDescription;
    }

    public String getCourceDetails() {
        return courceDetails;
    }

    public void setCourceDetails(String courceDetails) {
        this.courceDetails = courceDetails;
    }

    public Integer getIsTrending() {
        return isTrending;
    }

    public void setIsTrending(Integer isTrending) {
        this.isTrending = isTrending;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getCourseAccreditation() {
        return courseAccreditation;
    }

    public void setCourseAccreditation(String courseAccreditation) {
        this.courseAccreditation = courseAccreditation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public String getCourseCurriculum() {
        return courseCurriculum;
    }

    public void setCourseCurriculum(String courseCurriculum) {
        this.courseCurriculum = courseCurriculum;
    }

    public String getJobProfileContent() {
        return jobProfileContent;
    }

    public void setJobProfileContent(String jobProfileContent) {
        this.jobProfileContent = jobProfileContent;
    }

    public String getCourseIcon() {
        return courseIcon;
    }

    public void setCourseIcon(String courseIcon) {
        this.courseIcon = courseIcon;
    }

    public String getCourseGuide() {
        return courseGuide;
    }

    public void setCourseGuide(String courseGuide) {
        this.courseGuide = courseGuide;
    }

    public Integer getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(Integer areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    public String getExamAccepted() {
        return examAccepted;
    }

    public void setExamAccepted(String examAccepted) {
        this.examAccepted = examAccepted;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getRecruiterContent() {
        return recruiterContent;
    }

    public void setRecruiterContent(String recruiterContent) {
        this.recruiterContent = recruiterContent;
    }

}
