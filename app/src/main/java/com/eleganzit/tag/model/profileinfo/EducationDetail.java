package com.eleganzit.tag.model.profileinfo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationDetail implements Parcelable {


    @SerializedName("details_id")
    @Expose
    private Integer detailsId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("course_level")
    @Expose
    private String courseLevel;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("completion_year")
    @Expose
    private String completionYear;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("education_college_id")
    @Expose
    private Integer educationCollegeId;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("specialization")
    @Expose
    private String specialization;

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEducationCollegeId() {
        return educationCollegeId;
    }

    public void setEducationCollegeId(Integer educationCollegeId) {
        this.educationCollegeId = educationCollegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
