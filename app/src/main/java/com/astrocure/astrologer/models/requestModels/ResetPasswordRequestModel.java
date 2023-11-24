package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequestModel {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("new_password")
    @Expose
    private String newPassword;

    public ResetPasswordRequestModel(String userId, String newPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
    }
}
