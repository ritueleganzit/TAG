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

    @SerializedName("experience_info")
    @Expose
    private List<ExperienceInfo> experienceInfo = null;
    @SerializedName("preference_info")
    @Expose
    private List<PreferenceInfo> preferenceInfo = null;

    protected ProfileInfoData(Parcel in) {
        educationDetail = in.createTypedArrayList(EducationDetail.CREATOR);
        experienceInfo = in.createTypedArrayList(ExperienceInfo.CREATOR);
        preferenceInfo = in.createTypedArrayList(PreferenceInfo.CREATOR);
    }

    public static final Creator<ProfileInfoData> CREATOR = new Creator<ProfileInfoData>() {
        @Override
        public ProfileInfoData createFromParcel(Parcel in) {
            return new ProfileInfoData(in);
        }

        @Override
        public ProfileInfoData[] newArray(int size) {
            return new ProfileInfoData[size];
        }
    };

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
    @SerializedName("total_exp")
    @Expose
    private String totalExp;

    public String getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(String totalExp) {
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
        dest.writeTypedList(educationDetail);
        dest.writeTypedList(experienceInfo);
        dest.writeTypedList(preferenceInfo);
    }
}
