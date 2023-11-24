package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
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

        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("profileUrl")
        @Expose
        private String profileUrl;
        @SerializedName("token")
        @Expose
        private String token;

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getProfileUrl() {
            return profileUrl;
        }

        public String getToken() {
            return token;
        }
    }
}
