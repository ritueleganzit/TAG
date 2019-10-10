package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryData {
    @SerializedName("college_gallery_id")
    @Expose
    private String collegeGalleryId;
    @SerializedName("college_id")
    @Expose
    private String collegeId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public String getCollegeGalleryId() {
        return collegeGalleryId;
    }

    public void setCollegeGalleryId(String collegeGalleryId) {
        this.collegeGalleryId = collegeGalleryId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
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
