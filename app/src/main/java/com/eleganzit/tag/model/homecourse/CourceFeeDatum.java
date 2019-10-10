package com.eleganzit.tag.model.homecourse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourceFeeDatum {
    @SerializedName("college_id")
    @Expose
    private String collegeId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("datastream")
    @Expose
    private List<Datastream> datastream = null;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Datastream> getDatastream() {
        return datastream;
    }

    public void setDatastream(List<Datastream> datastream) {
        this.datastream = datastream;
    }

}
