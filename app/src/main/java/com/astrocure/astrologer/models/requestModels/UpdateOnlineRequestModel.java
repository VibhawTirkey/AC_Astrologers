package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOnlineRequestModel {
    @SerializedName("online")
    @Expose
    private boolean online;
    @SerializedName("astrologerId")
    @Expose
    private String astrologerId;
    @SerializedName("counsellingType")
    @Expose
    private String counsellingType;

    public UpdateOnlineRequestModel(boolean online, String astrologerId, String counsellingType) {
        this.online = online;
        this.astrologerId = astrologerId;
        this.counsellingType = counsellingType;
    }
}
