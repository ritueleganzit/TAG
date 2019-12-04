package com.eleganzit.tag.model.schoolstream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }


}
