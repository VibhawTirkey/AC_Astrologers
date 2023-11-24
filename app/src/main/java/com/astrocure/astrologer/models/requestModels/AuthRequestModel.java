package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthRequestModel {
    @SerializedName("verification_type")
    @Expose
    private String verificationType;
    @SerializedName("otp_id")
    @Expose
    private String otpId;
    @SerializedName("user_otp")
    @Expose
    private String userOtp;

    public AuthRequestModel(String verificationType, String otpId, String userOtp) {
        this.verificationType = verificationType;
        this.otpId = otpId;
        this.userOtp = userOtp;
    }
}
