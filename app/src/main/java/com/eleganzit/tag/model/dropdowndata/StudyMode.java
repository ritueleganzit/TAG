package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyMode {
    @SerializedName("mode_name")
    @Expose
    private String modeName;

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
}
