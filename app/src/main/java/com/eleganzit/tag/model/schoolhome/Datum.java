package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {
    @SerializedName("school_gallery_category_id")
    @Expose
    private Integer schoolGalleryCategoryId;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("gallery_data")
    @Expose
    private List<Object> galleryData = null;

    public Integer getSchoolGalleryCategoryId() {
        return schoolGalleryCategoryId;
    }

    public void setSchoolGalleryCategoryId(Integer schoolGalleryCategoryId) {
        this.schoolGalleryCategoryId = schoolGalleryCategoryId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public List<Object> getGalleryData() {
        return galleryData;
    }

    public void setGalleryData(List<Object> galleryData) {
        this.galleryData = galleryData;
    }

}
