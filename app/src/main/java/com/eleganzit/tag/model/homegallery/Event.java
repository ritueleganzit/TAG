package com.eleganzit.tag.model.homegallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("college_gallery_id")
    @Expose
    private Integer collegeGalleryId;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public Integer getCollegeGalleryId() {
        return collegeGalleryId;
    }

    public void setCollegeGalleryId(Integer collegeGalleryId) {
        this.collegeGalleryId = collegeGalleryId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
