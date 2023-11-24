package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassRequestModel {
    @SerializedName("mobile_no")
    @Expose
    private Long mobileNo;

    public ForgotPassRequestModel(Long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
