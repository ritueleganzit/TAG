package com.eleganzit.tag.model.appliedcollege;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadDetail {
    @SerializedName("doc_name")
    @Expose
    private String docName;
    @SerializedName("doc_url")
    @Expose
    private String docUrl;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
}
