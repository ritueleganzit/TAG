package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AskQuestion {
    @SerializedName("question_id")
    @Expose
    private Integer questionId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("question_text")
    @Expose
    private String questionText;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

}
