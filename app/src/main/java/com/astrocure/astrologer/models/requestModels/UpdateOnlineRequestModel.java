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
    @SerializedName("availableDate")
    @Expose
    private String availableDate;
    @SerializedName("availableTime")
    @Expose
    private String availableTime;

    public UpdateOnlineRequestModel() {
    }

    public UpdateOnlineRequestModel(boolean online, String astrologerId, String counsellingType, String availableDate, String availableTime) {
        this.online = online;
        this.astrologerId = astrologerId;
        this.counsellingType = counsellingType;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setAstrologerId(String astrologerId) {
        this.astrologerId = astrologerId;
    }

    public void setCounsellingType(String counsellingType) {
        this.counsellingType = counsellingType;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
