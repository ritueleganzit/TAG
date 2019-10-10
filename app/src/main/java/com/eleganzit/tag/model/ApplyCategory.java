package com.eleganzit.tag.model;

import java.io.Serializable;

public class ApplyCategory implements Serializable {
    String applied_course_name,applied_specialization_name;

    public ApplyCategory() {
    }

    public String getApplied_course_name() {
        return applied_course_name;
    }

    public void setApplied_course_name(String applied_course_name) {
        this.applied_course_name = applied_course_name;
    }

    public String getApplied_specialization_name() {
        return applied_specialization_name;
    }

    public void setApplied_specialization_name(String applied_specialization_name) {
        this.applied_specialization_name = applied_specialization_name;
    }
}
