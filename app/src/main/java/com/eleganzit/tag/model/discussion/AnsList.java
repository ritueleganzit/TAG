package com.eleganzit.tag.model.discussion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnsList implements Parcelable {
    @SerializedName("ans_id")
    @Expose
    private Integer ansId;
    @SerializedName("question_id")
    @Expose
    private Integer questionId;
    @SerializedName("submited_by")
    @Expose
    private Integer submitedBy;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("ans_text")
    @Expose
    private String ansText;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("ans_like_count")
    @Expose
    private Integer ansLikeCount;
    @SerializedName("ans_dislike_count")
    @Expose
    private Integer ansDislikeCount;
    @SerializedName("user_status")
    @Expose
    private List<UserStatus_> userStatus = null;
    @SerializedName("reply_list")
    @Expose
    private List<ReplyList> replyList = null;

    protected AnsList(Parcel in) {
        if (in.readByte() == 0) {
            ansId = null;
        } else {
            ansId = in.readInt();
        }
        if (in.readByte() == 0) {
            questionId = null;
        } else {
            questionId = in.readInt();
        }
        if (in.readByte() == 0) {
            submitedBy = null;
        } else {
            submitedBy = in.readInt();
        }
        if (in.readByte() == 0) {
            parentId = null;
        } else {
            parentId = in.readInt();
        }
        ansText = in.readString();
        createdDate = in.readString();
        if (in.readByte() == 0) {
            isActive = null;
        } else {
            isActive = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        photo = in.readString();
        if (in.readByte() == 0) {
            ansLikeCount = null;
        } else {
            ansLikeCount = in.readInt();
        }
        if (in.readByte() == 0) {
            ansDislikeCount = null;
        } else {
            ansDislikeCount = in.readInt();
        }
    }

    public static final Creator<AnsList> CREATOR = new Creator<AnsList>() {
        @Override
        public AnsList createFromParcel(Parcel in) {
            return new AnsList(in);
        }

        @Override
        public AnsList[] newArray(int size) {
            return new AnsList[size];
        }
    };

    public Integer getAnsId() {
        return ansId;
    }

    public void setAnsId(Integer ansId) {
        this.ansId = ansId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSubmitedBy() {
        return submitedBy;
    }

    public void setSubmitedBy(Integer submitedBy) {
        this.submitedBy = submitedBy;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getAnsLikeCount() {
        return ansLikeCount;
    }

    public void setAnsLikeCount(Integer ansLikeCount) {
        this.ansLikeCount = ansLikeCount;
    }

    public Integer getAnsDislikeCount() {
        return ansDislikeCount;
    }

    public void setAnsDislikeCount(Integer ansDislikeCount) {
        this.ansDislikeCount = ansDislikeCount;
    }

    public List<UserStatus_> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(List<UserStatus_> userStatus) {
        this.userStatus = userStatus;
    }

    public List<ReplyList> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyList> replyList) {
        this.replyList = replyList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (ansId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ansId);
        }
        if (questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(questionId);
        }
        if (submitedBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(submitedBy);
        }
        if (parentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(parentId);
        }
        dest.writeString(ansText);
        dest.writeString(createdDate);
        if (isActive == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(isActive);
        }
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(photo);
        if (ansLikeCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ansLikeCount);
        }
        if (ansDislikeCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ansDislikeCount);
        }
    }
}
