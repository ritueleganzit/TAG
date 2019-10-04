package com.eleganzit.tag.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preferancedata implements Parcelable {

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
    private String mode_of_study;
    @SerializedName("created_date")
    @Expose
    private String created_date;


    protected Preferancedata(Parcel in) {
        preferance_id = in.readString();
        userId = in.readString();
        stream = in.readString();
        course = in.readString();
        specialisation = in.readString();
        mode_of_study = in.readString();
        created_date = in.readString();
    }

    public static final Creator<Preferancedata> CREATOR = new Creator<Preferancedata>() {
        @Override
        public Preferancedata createFromParcel(Parcel in) {
            return new Preferancedata(in);
        }

        @Override
        public Preferancedata[] newArray(int size) {
            return new Preferancedata[size];
        }
    };

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

    public String getMode_of_study() {
        return mode_of_study;
    }

    public void setMode_of_study(String mode_of_study) {
        this.mode_of_study = mode_of_study;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(preferance_id);
        dest.writeString(userId);
        dest.writeString(stream);
        dest.writeString(course);
        dest.writeString(specialisation);
        dest.writeString(mode_of_study);
        dest.writeString(created_date);
    }
}
