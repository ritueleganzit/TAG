package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassDetail {
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("class_count")
    @Expose
    private Integer classCount;
    @SerializedName("class_result")
    @Expose
    private List<ClassResult> classResult = null;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public List<ClassResult> getClassResult() {
        return classResult;
    }

    public void setClassResult(List<ClassResult> classResult) {
        this.classResult = classResult;
    }
}
