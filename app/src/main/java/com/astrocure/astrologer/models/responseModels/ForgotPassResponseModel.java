package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPassResponseModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_code")
    @Expose
    private Long statusCode;

    public String getMessage() {
        return message;
    }

    public String getAlert() {
        return alert;
    }

    public Data getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public class Data {

        @SerializedName("otpId")
        @Expose
        private String otpId;
        @SerializedName("otp")
        @Expose
        private String otp;

        public String getOtpId() {
            return otpId;
        }

        public String getOtp() {
            return otp;
        }
    }
}
