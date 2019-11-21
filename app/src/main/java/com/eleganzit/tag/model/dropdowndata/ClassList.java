package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassList {
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }


}
