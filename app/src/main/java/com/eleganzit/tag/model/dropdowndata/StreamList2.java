package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreamList2 {
    @SerializedName("stream_id")
    @Expose
    private Integer streamId;
    @SerializedName("stream_name")
    @Expose
    private String streamName;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

}
