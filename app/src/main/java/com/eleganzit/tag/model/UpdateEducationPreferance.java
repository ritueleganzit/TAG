package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateEducationPreferance {

    @SerializedName("preferance_id")
    @Expose
    private String preferance_id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("specialisation")
    @Expose
    private String specialisation;
    @SerializedName("mode_of_study")
    @Expose
    private String modeOfStudy;

    public String getPreferance_id() {
        return preferance_id;
    }

    public void setPreferance_id(String preferance_id) {
        this.preferance_id = preferance_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(String modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

}
