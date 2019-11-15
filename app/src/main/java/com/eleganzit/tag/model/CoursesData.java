package com.eleganzit.tag.model;

public class CoursesData {

    String title,course_overview,course_specialization,course_eligibility;

    int id;

    public CoursesData(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public CoursesData(int id, String title, String course_overview, String course_specialization, String course_eligibility) {
        this.id = id;
        this.title = title;
        this.course_overview = course_overview;
        this.course_specialization = course_specialization;
        this.course_eligibility = course_eligibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse_overview() {
        return course_overview;
    }

    public void setCourse_overview(String course_overview) {
        this.course_overview = course_overview;
    }

    public String getCourse_specialization() {
        return course_specialization;
    }

    public void setCourse_specialization(String course_specialization) {
        this.course_specialization = course_specialization;
    }

    public String getCourse_eligibility() {
        return course_eligibility;
    }

    public void setCourse_eligibility(String course_eligibility) {
        this.course_eligibility = course_eligibility;
    }
}
