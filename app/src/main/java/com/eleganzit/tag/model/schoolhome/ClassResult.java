package com.eleganzit.tag.model.schoolhome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassResult {
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("broucher")
    @Expose
    private String broucher;
    @SerializedName("admission")
    @Expose
    private String admission;
    @SerializedName("accreditation")
    @Expose
    private String accreditation;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("study_mode")
    @Expose
    private String studyMode;
    @SerializedName("approval")
    @Expose
    private Integer approval;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("academic")
    @Expose
    private String academic;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("board_category")
    @Expose
    private String boardCategory;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBroucher() {
        return broucher;
    }

    public void setBroucher(String broucher) {
        this.broucher = broucher;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getAccreditation() {
        return accreditation;
    }

    public void setAccreditation(String accreditation) {
        this.accreditation = accreditation;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(String studyMode) {
        this.studyMode = studyMode;
    }

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }


}
