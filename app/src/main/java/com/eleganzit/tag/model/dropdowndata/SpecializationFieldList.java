package com.eleganzit.tag.model.dropdowndata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecializationFieldList {
    @SerializedName("spe_field_name")
    @Expose
    private String speFieldName;

    public String getSpeFieldName() {
        return speFieldName;
    }

    public void setSpeFieldName(String speFieldName) {
        this.speFieldName = speFieldName;
    }

}
