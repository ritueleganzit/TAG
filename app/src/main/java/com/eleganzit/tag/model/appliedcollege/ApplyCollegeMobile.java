package com.eleganzit.tag.model.appliedcollege;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplyCollegeMobile {
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("course_list_id")
    @Expose
    private Integer courseListId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("d_o_b")
    @Expose
    private String dOB;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("father_occupation")
    @Expose
    private String fatherOccupation;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("mother_name")
    @Expose
    private String motherName;
    @SerializedName("mother_occupation")
    @Expose
    private String motherOccupation;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("permanent_address")
    @Expose
    private String permanentAddress;
    @SerializedName("temporary_address")
    @Expose
    private String temporaryAddress;
    @SerializedName("for_NRI")
    @Expose
    private String forNRI;
    @SerializedName("course_detail")
    @Expose
    private List<CourseDetail> courseDetail = null;
    @SerializedName("education_detail")
    @Expose
    private List<EducationDetail> educationDetail = null;
    @SerializedName("compete_exam_detail")
    @Expose
    private List<CompeteExamDetail> competeExamDetail = null;
    @SerializedName("upload_detail")
    @Expose
    private List<UploadDetail> uploadDetail = null;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getCourseListId() {
        return courseListId;
    }

    public void setCourseListId(Integer courseListId) {
        this.courseListId = courseListId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getForNRI() {
        return forNRI;
    }

    public void setForNRI(String forNRI) {
        this.forNRI = forNRI;
    }

    public List<CourseDetail> getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(List<CourseDetail> courseDetail) {
        this.courseDetail = courseDetail;
    }

    public List<EducationDetail> getEducationDetail() {
        return educationDetail;
    }

    public void setEducationDetail(List<EducationDetail> educationDetail) {
        this.educationDetail = educationDetail;
    }

    public List<CompeteExamDetail> getCompeteExamDetail() {
        return competeExamDetail;
    }

    public void setCompeteExamDetail(List<CompeteExamDetail> competeExamDetail) {
        this.competeExamDetail = competeExamDetail;
    }

    public List<UploadDetail> getUploadDetail() {
        return uploadDetail;
    }

    public void setUploadDetail(List<UploadDetail> uploadDetail) {
        this.uploadDetail = uploadDetail;
    }

}
