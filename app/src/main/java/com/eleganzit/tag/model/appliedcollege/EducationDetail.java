package com.eleganzit.tag.model.appliedcollege;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationDetail {
    @SerializedName("edu_name")
    @Expose
    private String eduName;
    @SerializedName("passing_year")
    @Expose
    private String passingYear;
    @SerializedName("obtained_marks")
    @Expose
    private String obtainedMarks;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(String obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
