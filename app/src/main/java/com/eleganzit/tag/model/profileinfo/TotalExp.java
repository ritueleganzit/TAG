package com.eleganzit.tag.model.profileinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalExp {
    @SerializedName("total_exp")
    @Expose
    private String totalExp;

    public String getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(String totalExp) {
        this.totalExp = totalExp;
    }


}
