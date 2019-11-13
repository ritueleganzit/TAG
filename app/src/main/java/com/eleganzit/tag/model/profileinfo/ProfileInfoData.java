package com.eleganzit.tag.model.profileinfo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileInfoData implements Parcelable {
    @SerializedName("personal_info")
    @Expose
    private PersonalInfo personalInfo;
    @SerializedName("education_detail")
    @Expose
    private List<EducationDetail> educationDetail = null;
    @SerializedName("total_exp")
    @Expose
    private List<TotalExp> totalExp = null;
    @SerializedName("experience_info")
    @Expose
    private List<ExperienceInfo> experienceInfo = null;
    @SerializedName("preference_info")
    @Expose
    private List<PreferenceInfo> preferenceInfo = null;

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<EducationDetail> getEducationDetail() {
        return educationDetail;
    }

    public void setEducationDetail(List<EducationDetail> educationDetail) {
        this.educationDetail = educationDetail;
    }

    public List<TotalExp> getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(List<TotalExp> totalExp) {
        this.totalExp = totalExp;
    }

    public List<ExperienceInfo> getExperienceInfo() {
        return experienceInfo;
    }

    public void setExperienceInfo(List<ExperienceInfo> experienceInfo) {
        this.experienceInfo = experienceInfo;
    }

    public List<PreferenceInfo> getPreferenceInfo() {
        return preferenceInfo;
    }

    public void setPreferenceInfo(List<PreferenceInfo> preferenceInfo) {
        this.preferenceInfo = preferenceInfo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
