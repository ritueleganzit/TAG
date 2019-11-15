package com.eleganzit.tag.model.addeducation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnDatum {
    @SerializedName("course_level")
    @Expose
    private String courseLevel;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("completion_year")
    @Expose
    private String completionYear;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("marks")
    @Expose
    private String marks;

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }


}
