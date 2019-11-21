package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("stream_list")
    @Expose
    private List<StreamList> streamList = null;
    public List<StreamList> getStreamList() {
        return streamList;
    }
    @SerializedName("class_list")
    @Expose
    private List<ClassList> classList = null;
    @SerializedName("stream_list_2")
    @Expose
    private List<StreamList2> streamList2 = null;

    public List<ClassList> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassList> classList) {
        this.classList = classList;
    }

    public List<StreamList2> getStreamList2() {
        return streamList2;
    }

    public void setStreamList2(List<StreamList2> streamList2) {
        this.streamList2 = streamList2;
    }

    public void setStreamList(List<StreamList> streamList) {
        this.streamList = streamList;
    }



    @SerializedName("course_list")
    @Expose
    private List<CourseList> courseList = null;
    public List<CourseList> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseList> courseList) {
        this.courseList = courseList;
    }


    @SerializedName("study_mode")
    @Expose
    private List<StudyMode> studyMode = null;
    public List<StudyMode> getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(List<StudyMode> studyMode) {
        this.studyMode = studyMode;
    }


    @SerializedName("specialization_field_list")
    @Expose
    private List<SpecializationFieldList> specializationFieldList = null;
    public List<SpecializationFieldList> getSpecializationFieldList() {
        return specializationFieldList;
    }

    public void setSpecializationFieldList(List<SpecializationFieldList> specializationFieldList) {
        this.specializationFieldList = specializationFieldList;
    }


}
