package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadAudioRequestModel {
    @SerializedName("astrologerId")
    @Expose
    private String astrologerId;
    @SerializedName("kidsKundaliId")
    @Expose
    private String kidsKundliId;
    @SerializedName("questionId")
    @Expose
    private String questionId;
    @SerializedName("recordingUrl")
    @Expose
    private String recordingUrl;

    public UploadAudioRequestModel() {
    }

    public UploadAudioRequestModel(String astrologerId, String kidsKundliId, String questionId, String recordingUrl) {
        this.astrologerId = astrologerId;
        this.kidsKundliId = kidsKundliId;
        this.questionId = questionId;
        this.recordingUrl = recordingUrl;
    }

    public void setAstrologerId(String astrologerId) {
        this.astrologerId = astrologerId;
    }

    public void setKidsKundliId(String kidsKundliId) {
        this.kidsKundliId = kidsKundliId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }
}
