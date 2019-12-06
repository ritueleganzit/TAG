package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("school_info")
    @Expose
    private List<SchoolInfo> schoolInfo = null;
    @SerializedName("class_detail")
    @Expose
    private List<ClassDetail> classDetail = null;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;
    @SerializedName("facility")
    @Expose
    private List<Facilities> facility = null;

    public List<SchoolInfo> getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(List<SchoolInfo> schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public List<ClassDetail> getClassDetail() {
        return classDetail;
    }

    public void setClassDetail(List<ClassDetail> classDetail) {
        this.classDetail = classDetail;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public List<Facilities> getFacility() {
        return facility;
    }

    public void setFacility(List<Facilities> facility) {
        this.facility = facility;
    }

}
