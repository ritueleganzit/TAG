package com.eleganzit.tag.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryData {
    @SerializedName("event_details")
    @Expose
    private List<EventDetail> eventDetails = null;
    @SerializedName("infrastructure_details")
    @Expose
    private List<InfrastructureDetail> infrastructureDetails = null;
    @SerializedName("video_details")
    @Expose
    private List<VideoDetail> videoDetails = null;

    public List<EventDetail> getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(List<EventDetail> eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<InfrastructureDetail> getInfrastructureDetails() {
        return infrastructureDetails;
    }

    public void setInfrastructureDetails(List<InfrastructureDetail> infrastructureDetails) {
        this.infrastructureDetails = infrastructureDetails;
    }

    public List<VideoDetail> getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(List<VideoDetail> videoDetails) {
        this.videoDetails = videoDetails;
    }



}
