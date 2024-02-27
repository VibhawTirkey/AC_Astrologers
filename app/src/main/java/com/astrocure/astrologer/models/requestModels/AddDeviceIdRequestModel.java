package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDeviceIdRequestModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    public AddDeviceIdRequestModel(String id, String deviceId) {
        this.id = id;
        this.deviceId = deviceId;
    }
}
