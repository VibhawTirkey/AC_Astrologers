package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteRecordingRequestModel {
    @SerializedName("kidsKundaliId")
    @Expose
    private String kidsKundliId;
    @SerializedName("astrologerId")
    @Expose
    private String astrologerId;

    public DeleteRecordingRequestModel(String kidsKundliId, String astrologerId) {
        this.kidsKundliId = kidsKundliId;
        this.astrologerId = astrologerId;
    }
}
