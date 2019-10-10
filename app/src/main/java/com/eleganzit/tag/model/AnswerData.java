package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerData {
    @SerializedName("ans_id")
    @Expose
    private String ansId;
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("submited_by")
    @Expose
    private String submitedBy;
    @SerializedName("ans_text")
    @Expose
    private String ansText;
    @SerializedName("created_date")
    @Expose
    private String createdDate;

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSubmitedBy() {
        return submitedBy;
    }

    public void setSubmitedBy(String submitedBy) {
        this.submitedBy = submitedBy;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
