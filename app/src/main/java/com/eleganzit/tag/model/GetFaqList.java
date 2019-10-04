package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFaqList {
    @SerializedName("help_faq_id")
    @Expose
    private Integer helpFaqId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private String answer;

    public Integer getHelpFaqId() {
        return helpFaqId;
    }

    public void setHelpFaqId(Integer helpFaqId) {
        this.helpFaqId = helpFaqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
