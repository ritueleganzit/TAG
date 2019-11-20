package com.eleganzit.tag.model.discussion;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum implements Parcelable {
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
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("like_count")
    @Expose
    private Integer likeCount;
    @SerializedName("dislike_count")
    @Expose
    private Integer dislikeList;
    @SerializedName("user_status")
    @Expose
    private List<UserStatus> userStatus = null;
    @SerializedName("ans_list")
    @Expose
    private List<AnsList> ansList = null;

    protected Datum(Parcel in) {
        if (in.readByte() == 0) {
            questionId = null;
        } else {
            questionId = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        questionText = in.readString();
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
            likeCount = null;
        } else {
            likeCount = in.readInt();
        }
        if (in.readByte() == 0) {
            dislikeList = null;
        } else {
            dislikeList = in.readInt();
        }
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeList() {
        return dislikeList;
    }

    public void setDislikeList(Integer dislikeList) {
        this.dislikeList = dislikeList;
    }

    public List<UserStatus> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(List<UserStatus> userStatus) {
        this.userStatus = userStatus;
    }

    public List<AnsList> getAnsList() {
        return ansList;
    }

    public void setAnsList(List<AnsList> ansList) {
        this.ansList = ansList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(questionId);
        }
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(questionText);
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
        if (likeCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likeCount);
        }
        if (dislikeList == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dislikeList);
        }
    }
}
