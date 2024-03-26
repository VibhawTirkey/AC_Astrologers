package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadAudioResponseModel {
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
        @SerializedName("astrologerId")
        @Expose
        private String astrologerId;
        @SerializedName("kidsKundaliId")
        @Expose
        private String kidsKundliId;
        @SerializedName("questionId")
        @Expose
        private String questionId;
        @SerializedName("recordingUrl")
        @Expose
        private List<String> recordingUrl;
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

        public String getAstrologerId() {
            return astrologerId;
        }

        public String getKidsKundliId() {
            return kidsKundliId;
        }

        public String getQuestionId() {
            return questionId;
        }

        public List<String> getRecordingUrl() {
            return recordingUrl;
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
