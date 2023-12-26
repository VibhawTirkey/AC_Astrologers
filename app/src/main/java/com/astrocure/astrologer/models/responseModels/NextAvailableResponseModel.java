package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NextAvailableResponseModel {
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

    @SerializedName("status_code")
    @Expose
    private long statusCode;
    public static class Data {

        @SerializedName("counsellingType")
        @Expose
        private String counsellingType;
        @SerializedName("astrologerId")
        @Expose
        private String astrologerId;
        @SerializedName("nextAvailability")
        @Expose
        private String nextAvailability;
        @SerializedName("nextAvailableDate")
        @Expose
        private String nextAvailableDate;
        @SerializedName("nextAvailableTime")
        @Expose
        private String nextAvailableTime;
        @SerializedName("_id")
        @Expose
        private String uid;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private long v;
        @SerializedName("id")
        @Expose
        private String id;

        public String getCounsellingType() {
            return counsellingType;
        }

        public String getAstrologerId() {
            return astrologerId;
        }

        public String getNextAvailability() {
            return nextAvailability;
        }

        public String getNextAvailableDate() {
            return nextAvailableDate;
        }

        public String getNextAvailableTime() {
            return nextAvailableTime;
        }

        public String getUid() {
            return uid;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public long getV() {
            return v;
        }

        public String getId() {
            return id;
        }
    }
}
