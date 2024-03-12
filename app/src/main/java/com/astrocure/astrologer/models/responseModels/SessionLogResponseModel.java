package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SessionLogResponseModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("alert")
    @Expose
    private String alert;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
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

    public List<Datum> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public static class Datum {
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("counsellingType")
        @Expose
        private String counsellingType;
        @SerializedName("online")
        @Expose
        private boolean online;
        @SerializedName("startDateTime")
        @Expose
        private String startDateTime;
        @SerializedName("endDateTime")
        @Expose
        private String endDateTime;
        @SerializedName("id")
        @Expose
        private String id;

        public String getUid() {
            return uid;
        }

        public String getCounsellingType() {
            return counsellingType;
        }

        public boolean isOnline() {
            return online;
        }

        public String getStartDateTime() {
            return startDateTime;
        }

        public String getEndDateTime() {
            return endDateTime;
        }

        public String getId() {
            return id;
        }
    }
}
