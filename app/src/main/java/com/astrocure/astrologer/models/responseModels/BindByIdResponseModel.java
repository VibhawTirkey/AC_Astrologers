package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BindByIdResponseModel {
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
    private long statusCode;

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

    public long getStatusCode() {
        return statusCode;
    }

    public static class Data {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("dataName")
        @Expose
        private String dataName;
        @SerializedName("isActive")
        @Expose
        private boolean isActive;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("__v")
        @Expose
        private long v;

        public String getId() {
            return id;
        }

        public String getDataName() {
            return dataName;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getDate() {
            return date;
        }

        public long getV() {
            return v;
        }
    }
}
