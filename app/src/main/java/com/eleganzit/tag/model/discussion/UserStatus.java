package com.eleganzit.tag.model.discussion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserStatus {
    @SerializedName("ques_like_id")
    @Expose
    private Integer quesLikeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("question_id")
    @Expose
    private Integer questionId;
    @SerializedName("is_like")
    @Expose
    private Integer isLike;
    @SerializedName("is_dislike")
    @Expose
    private Integer isDislike;

    public Integer getQuesLikeId() {
        return quesLikeId;
    }

    public void setQuesLikeId(Integer quesLikeId) {
        this.quesLikeId = quesLikeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public Integer getIsDislike() {
        return isDislike;
    }

    public void setIsDislike(Integer isDislike) {
        this.isDislike = isDislike;
    }

}
