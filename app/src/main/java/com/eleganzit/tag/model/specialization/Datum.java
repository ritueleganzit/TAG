package com.eleganzit.tag.model.specialization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("specialization_id")
    @Expose
    private Integer specializationId;
    @SerializedName("specialization_name")
    @Expose
    private String specializationName;
    @SerializedName("cource_name")
    @Expose
    private String courceName;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("eligibility")
    @Expose
    private String eligibility;
    @SerializedName("cirrculum")
    @Expose
    private String cirrculum;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("guide")
    @Expose
    private String guide;
    @SerializedName("required_skill")
    @Expose
    private String requiredSkill;
    @SerializedName("job_profile_content")
    @Expose
    private String jobProfileContent;
    @SerializedName("recruiter_content")
    @Expose
    private String recruiterContent;
    @SerializedName("recruiter_id")
    @Expose
    private String recruiterId;

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getCirrculum() {
        return cirrculum;
    }

    public void setCirrculum(String cirrculum) {
        this.cirrculum = cirrculum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public String getJobProfileContent() {
        return jobProfileContent;
    }

    public void setJobProfileContent(String jobProfileContent) {
        this.jobProfileContent = jobProfileContent;
    }

    public String getRecruiterContent() {
        return recruiterContent;
    }

    public void setRecruiterContent(String recruiterContent) {
        this.recruiterContent = recruiterContent;
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }


}
