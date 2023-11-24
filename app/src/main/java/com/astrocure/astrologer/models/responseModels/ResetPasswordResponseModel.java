package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResetPasswordResponseModel {
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("alert")
    @Expose
    public String alert;
    @SerializedName("data")
    @Expose
    public List<Object> data;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("status_code")
    @Expose
    public Long statusCode;

    public String getMessage() {
        return message;
    }

    public String getAlert() {
        return alert;
    }

    public List<Object> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public Long getStatusCode() {
        return statusCode;
    }
}
