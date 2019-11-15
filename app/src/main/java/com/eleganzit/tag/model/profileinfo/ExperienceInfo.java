package com.eleganzit.tag.model.profileinfo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExperienceInfo implements Parcelable {
    @SerializedName("experience_id")
    @Expose
    private Integer experienceId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("current_job_que")
    @Expose
    private String currentJobQue;

    protected ExperienceInfo(Parcel in) {
        if (in.readByte() == 0) {
            experienceId = null;
        } else {
            experienceId = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        employeeName = in.readString();
        designation = in.readString();
        department = in.readString();
        currentJobQue = in.readString();
    }

    public static final Creator<ExperienceInfo> CREATOR = new Creator<ExperienceInfo>() {
        @Override
        public ExperienceInfo createFromParcel(Parcel in) {
            return new ExperienceInfo(in);
        }

        @Override
        public ExperienceInfo[] newArray(int size) {
            return new ExperienceInfo[size];
        }
    };

    public Integer getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Integer experienceId) {
        this.experienceId = experienceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCurrentJobQue() {
        return currentJobQue;
    }

    public void setCurrentJobQue(String currentJobQue) {
        this.currentJobQue = currentJobQue;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (experienceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(experienceId);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(employeeName);
        dest.writeString(designation);
        dest.writeString(department);
        dest.writeString(currentJobQue);
    }
}
