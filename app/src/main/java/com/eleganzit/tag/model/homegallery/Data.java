package com.eleganzit.tag.model.homegallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("infrastructure")
    @Expose
    private List<Infrastructure> infrastructure = null;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Infrastructure> getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(List<Infrastructure> infrastructure) {
        this.infrastructure = infrastructure;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
