package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PredictionQuestionResponseModel {
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
        private String uid;
        @SerializedName("astrologerId")
        @Expose
        private AstrologerId astrologerId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("hashTag")
        @Expose
        private String hashTag;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("predicationDateTime")
        @Expose
        private String predicationDateTime;
        @SerializedName("answerDateTime")
        @Expose
        private Object answerDateTime;
        @SerializedName("isActive")
        @Expose
        private boolean isActive;
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

        public String getUid() {
            return uid;
        }

        public AstrologerId getAstrologerId() {
            return astrologerId;
        }

        public String getTitle() {
            return title;
        }

        public String getHashTag() {
            return hashTag;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public String getPredicationDateTime() {
            return predicationDateTime;
        }

        public Object getAnswerDateTime() {
            return answerDateTime;
        }

        public boolean isActive() {
            return isActive;
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

        public static class AstrologerId {
            @SerializedName("_id")
            @Expose
            private String uid;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("mobileNo")
            @Expose
            private long mobileNo;
            @SerializedName("id")
            @Expose
            private String id;

            public String getUid() {
                return uid;
            }

            public String getUserName() {
                return userName;
            }

            public long getMobileNo() {
                return mobileNo;
            }

            public String getId() {
                return id;
            }
        }
    }
}
