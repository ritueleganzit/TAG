package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionData {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("question_text")
    @Expose
    private String questionText;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("answer_data")
    @Expose
    private List<AnswerData> answerData = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<AnswerData> getAnswerData() {
        return answerData;
    }

    public void setAnswerData(List<AnswerData> answerData) {
        this.answerData = answerData;
    }

}
