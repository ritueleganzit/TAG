package com.eleganzit.tag.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Education implements Parcelable {
    @SerializedName("education_id")
    @Expose
    private String educationId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("cource_level")
    @Expose
    private String courceLevel;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("cource_year")
    @Expose
    private String courceYear;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    protected Education(Parcel in) {
        educationId = in.readString();
        userId = in.readString();
        courceLevel = in.readString();
        schoolName = in.readString();
        courceYear = in.readString();
        board = in.readString();
        subject = in.readString();
        marks = in.readString();
        createdDate = in.readString();
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourceLevel() {
        return courceLevel;
    }

    public void setCourceLevel(String courceLevel) {
        this.courceLevel = courceLevel;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourceYear() {
        return courceYear;
    }

    public void setCourceYear(String courceYear) {
        this.courceYear = courceYear;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(educationId);
        dest.writeString(userId);
        dest.writeString(courceLevel);
        dest.writeString(schoolName);
        dest.writeString(courceYear);
        dest.writeString(board);
        dest.writeString(subject);
        dest.writeString(marks);
        dest.writeString(createdDate);
    }
}
