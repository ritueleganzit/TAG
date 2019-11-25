package com.eleganzit.tag.model.newhome;

import com.eleganzit.tag.model.CollegeInfo;
import com.eleganzit.tag.model.CourceFee;
import com.eleganzit.tag.model.FacilityData;
import com.eleganzit.tag.model.GalleryData;
import com.eleganzit.tag.model.homegallery.Event;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("college_info")
    @Expose
    private List<CollegeInfo> collegeInfo = null;



 @SerializedName("cource_detail")
    @Expose
    private List<CourceDetail> cource_detail = null;


 @SerializedName("gallery")
    @Expose
    private List<Event> gallery = null;




 @SerializedName("facility")
    @Expose
    private List<FacilityData> facility = null;

    public List<CollegeInfo> getCollegeInfo() {
        return collegeInfo;
    }

    public void setCollegeInfo(List<CollegeInfo> collegeInfo) {
        this.collegeInfo = collegeInfo;
    }

    public List<CourceDetail> getCource_detail() {
        return cource_detail;
    }

    public void setCource_and_fee(List<CourceDetail> cource_and_fee) {
        this.cource_detail = cource_and_fee;
    }

    public List<Event> getGallery() {
        return gallery;
    }

    public void setGallery(List<Event> gallery) {
        this.gallery = gallery;
    }

    public List<FacilityData> getFacility() {
        return facility;
    }

    public void setFacility(List<FacilityData> facility) {
        this.facility = facility;
    }
}
