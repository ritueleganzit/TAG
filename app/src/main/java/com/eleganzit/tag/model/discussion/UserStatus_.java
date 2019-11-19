package com.eleganzit.tag.model.discussion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserStatus_ {
    @SerializedName("ans_like_id")
    @Expose
    private Integer ansLikeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("ans_id")
    @Expose
    private Integer ansId;
    @SerializedName("is_like")
    @Expose
    private Integer isLike;
    @SerializedName("is_dislike")
    @Expose
    private Integer isDislike;

    public Integer getAnsLikeId() {
        return ansLikeId;
    }

    public void setAnsLikeId(Integer ansLikeId) {
        this.ansLikeId = ansLikeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
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
