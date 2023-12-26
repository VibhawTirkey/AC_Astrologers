package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTokenRequestModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("firebaseToken")
    @Expose
    private String firebaseToken;

    public UpdateTokenRequestModel(String id, String firebaseToken) {
        this.id = id;
        this.firebaseToken = firebaseToken;
    }
}
