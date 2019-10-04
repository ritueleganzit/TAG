package com.eleganzit.tag.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workdata implements Parcelable {

    @SerializedName("work_id")
    @Expose
    private String work_id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("employee_name")
    @Expose
    private String employee_name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("current_job")
    @Expose
    private String current_job;
    @SerializedName("created_date")
    @Expose
    private String created_date;


    protected Workdata(Parcel in) {
        work_id = in.readString();
        userId = in.readString();
        employee_name = in.readString();
        designation = in.readString();
        department = in.readString();
        current_job = in.readString();
        created_date = in.readString();
    }

    public static final Creator<Workdata> CREATOR = new Creator<Workdata>() {
        @Override
        public Workdata createFromParcel(Parcel in) {
            return new Workdata(in);
        }

        @Override
        public Workdata[] newArray(int size) {
            return new Workdata[size];
        }
    };

    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
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

    public String getCurrent_job() {
        return current_job;
    }

    public void setCurrent_job(String current_job) {
        this.current_job = current_job;
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
        dest.writeString(work_id);
        dest.writeString(userId);
        dest.writeString(employee_name);
        dest.writeString(designation);
        dest.writeString(department);
        dest.writeString(current_job);
        dest.writeString(created_date);
    }
}
