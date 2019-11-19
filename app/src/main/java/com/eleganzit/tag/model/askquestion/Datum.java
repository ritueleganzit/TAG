package com.eleganzit.tag.model.askquestion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {
    @SerializedName("question_id")
    @Expose
    private Integer questionId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("question_text")
    @Expose
    private String questionText;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("ans_list")
    @Expose
    private List<AnsList> ansList = null;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<AnsList> getAnsList() {
        return ansList;
    }

    public void setAnsList(List<AnsList> ansList) {
        this.ansList = ansList;
    }

}
