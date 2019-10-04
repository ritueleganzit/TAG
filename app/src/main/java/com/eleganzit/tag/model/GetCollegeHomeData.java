package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCollegeHomeData {
    @SerializedName("college_detail")
    @Expose
    private CollegeDetail collegeDetail;
    @SerializedName("course_detail")
    @Expose
    private List<CourseDetail> courseDetail = null;
    @SerializedName("course_count")
    @Expose
    private Integer courseCount;
    @SerializedName("contact_detail")
    @Expose
    private ContactDetail contactDetail;
    @SerializedName("gallery")
    @Expose
    private GalleryData gallery;
    @SerializedName("broucher")
    @Expose
    private String broucher;
    @SerializedName("facilities")
    @Expose
    private List<Object> facilities = null;

    public CollegeDetail getCollegeDetail() {
        return collegeDetail;
    }

    public void setCollegeDetail(CollegeDetail collegeDetail) {
        this.collegeDetail = collegeDetail;
    }

    public List<CourseDetail> getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(List<CourseDetail> courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public GalleryData getGallery() {
        return gallery;
    }

    public void setGallery(GalleryData gallery) {
        this.gallery = gallery;
    }

    public String getBroucher() {
        return broucher;
    }

    public void setBroucher(String broucher) {
        this.broucher = broucher;
    }

    public List<Object> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Object> facilities) {
        this.facilities = facilities;
    }


}
