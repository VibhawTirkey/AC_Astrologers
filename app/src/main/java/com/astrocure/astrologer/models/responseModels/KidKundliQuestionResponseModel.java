package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KidKundliQuestionResponseModel {
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
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("questionText")
        @Expose
        private String questionText;
        @SerializedName("colorCode")
        @Expose
        private String colorCode;
        @SerializedName("isActive")
        @Expose
        private boolean isActive;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private long v;

        public String getId() {
            return id;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getColorCode() {
            return colorCode;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getDate() {
            return date;
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
    }

}
