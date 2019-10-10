package com.eleganzit.tag.model.appliedcollege;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompeteExamDetail {
    @SerializedName("exam_name")
    @Expose
    private String examName;
    @SerializedName("exam_marks")
    @Expose
    private String examMarks;
    @SerializedName("exam_rank")
    @Expose
    private String examRank;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamMarks() {
        return examMarks;
    }

    public void setExamMarks(String examMarks) {
        this.examMarks = examMarks;
    }

    public String getExamRank() {
        return examRank;
    }

    public void setExamRank(String examRank) {
        this.examRank = examRank;
    }
}
