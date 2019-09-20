package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddEducationData {

    @SerializedName("education_id")
    @Expose
    private Integer educationId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cource_level")
    @Expose
    private String courceLevel;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("cource_year")
    @Expose
    private String courceYear;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("marks")
    @Expose
    private String marks;

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourceLevel() {
        return courceLevel;
    }

    public void setCourceLevel(String courceLevel) {
        this.courceLevel = courceLevel;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourceYear() {
        return courceYear;
    }

    public void setCourceYear(String courceYear) {
        this.courceYear = courceYear;
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


}
