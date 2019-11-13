package com.eleganzit.tag.model.profileinfo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferenceInfo implements Parcelable {
    @SerializedName("preference_id")
    @Expose
    private Integer preferenceId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("specialization")
    @Expose
    private String specialization;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;

    protected PreferenceInfo(Parcel in) {
        if (in.readByte() == 0) {
            preferenceId = null;
        } else {
            preferenceId = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        stream = in.readString();
        course = in.readString();
        specialization = in.readString();
        studyMode = in.readString();
    }

    public static final Creator<PreferenceInfo> CREATOR = new Creator<PreferenceInfo>() {
        @Override
        public PreferenceInfo createFromParcel(Parcel in) {
            return new PreferenceInfo(in);
        }

        @Override
        public PreferenceInfo[] newArray(int size) {
            return new PreferenceInfo[size];
        }
    };

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (preferenceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(preferenceId);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(stream);
        dest.writeString(course);
        dest.writeString(specialization);
        dest.writeString(studyMode);
    }
}
