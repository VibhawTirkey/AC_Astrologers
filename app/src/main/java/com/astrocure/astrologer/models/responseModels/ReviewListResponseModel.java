package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewListResponseModel {
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
        @SerializedName("recipient")
        @Expose
        private String recipient;
        @SerializedName("rating")
        @Expose
        private long rating;
        @SerializedName("review")
        @Expose
        private String review;
        @SerializedName("created_by")
        @Expose
        private CreatedBy createdBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private long v;

        public String getId() {
            return id;
        }

        public String getRecipient() {
            return recipient;
        }

        public long getRating() {
            return rating;
        }

        public String getReview() {
            return review;
        }

        public CreatedBy getCreatedBy() {
            return createdBy;
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

        public static class CreatedBy {

            @SerializedName("_id")
            @Expose
            private String uid;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("profileUrl")
            @Expose
            private String profileUrl;
            @SerializedName("zodiacId")
            @Expose
            private ZodiacId zodiacId;
            @SerializedName("id")
            @Expose
            private String id;

            public String getUid() {
                return uid;
            }

            public String getUserName() {
                return userName;
            }

            public String getProfileUrl() {
                return profileUrl;
            }

            public ZodiacId getZodiacId() {
                return zodiacId;
            }

            public String getId() {
                return id;
            }

            public static class ZodiacId {

                @SerializedName("zodiacImage")
                @Expose
                private String zodiacImage;

                public String getZodiacImage() {
                    return zodiacImage;
                }
            }
        }
    }
}
