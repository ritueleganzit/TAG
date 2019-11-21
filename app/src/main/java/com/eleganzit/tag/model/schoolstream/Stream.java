package com.eleganzit.tag.model.schoolstream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stream {
    @SerializedName("stream_id")
    @Expose
    private Integer streamId;
    @SerializedName("stream_name")
    @Expose
    private String streamName;
    @SerializedName("assigned_class")
    @Expose
    private String assignedClass;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(String assignedClass) {
        this.assignedClass = assignedClass;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }


}
