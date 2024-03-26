package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class KidsKundliInfoResponseModel implements Serializable {
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

    public static class Datum implements Serializable {

        @SerializedName("astrologerRecordingComplete")
        @Expose
        private List<Object> astrologerRecordingComplete;
        @SerializedName("_id")
        @Expose
        private String _id;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("fatherName")
        @Expose
        private String fatherName;
        @SerializedName("motherName")
        @Expose
        private String motherName;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("tob")
        @Expose
        private String tob;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("language")
        @Expose
        private Language language;
        @SerializedName("gender")
        @Expose
        private Gender gender;
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
        @SerializedName("astrologerRecordings")
        @Expose
        private List<String> astrologerRecordings;
        @SerializedName("isKundaliAvailable")
        @Expose
        private boolean isKundaliAvailable;
        @SerializedName("isAstrologerRecordingAvailable")
        @Expose
        private boolean isAstrologerRecordingAvailable;
        @SerializedName("assignedAstrologers")
        @Expose
        private List<AssignedAstrologer> assignedAstrologers;
        @SerializedName("amount")
        @Expose
        private long amount;
        @SerializedName("recordings")
        @Expose
        private List<Recording> recordings;
        @SerializedName("id")
        @Expose
        private String id;

        public List<Object> getAstrologerRecordingComplete() {
            return astrologerRecordingComplete;
        }

        public String get_id() {
            return _id;
        }

        public String getUserId() {
            return userId;
        }

        public String getFullName() {
            return fullName;
        }

        public String getFatherName() {
            return fatherName;
        }

        public String getMotherName() {
            return motherName;
        }

        public String getDob() {
            return dob;
        }

        public String getTob() {
            return tob;
        }

        public Location getLocation() {
            return location;
        }

        public State getState() {
            return state;
        }

        public Language getLanguage() {
            return language;
        }

        public Gender getGender() {
            return gender;
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

        public List<String> getAstrologerRecordings() {
            return astrologerRecordings;
        }

        public boolean isKundaliAvailable() {
            return isKundaliAvailable;
        }

        public boolean isAstrologerRecordingAvailable() {
            return isAstrologerRecordingAvailable;
        }

        public List<AssignedAstrologer> getAssignedAstrologers() {
            return assignedAstrologers;
        }

        public long getAmount() {
            return amount;
        }

        public List<Recording> getRecordings() {
            return recordings;
        }

        public String getId() {
            return id;
        }

        public static class AssignedAstrologer implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("profileUrl")
            @Expose
            private String profileUrl;
            @SerializedName("rating")
            @Expose
            private long rating;
            @SerializedName("id")
            @Expose
            private String id;

            public String get_id() {
                return _id;
            }

            public String getUserName() {
                return userName;
            }

            public String getProfileUrl() {
                return profileUrl;
            }

            public long getRating() {
                return rating;
            }

            public String getId() {
                return id;
            }
        }

        public static class Gender implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("dataName")
            @Expose
            private String dataName;

            public String get_id() {
                return _id;
            }

            public String getDataName() {
                return dataName;
            }
        }

        public static class Language implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("dataName")
            @Expose
            private String dataName;

            public String get_id() {
                return _id;
            }

            public String getDataName() {
                return dataName;
            }
        }

        public static class Location implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("cityName")
            @Expose
            private String cityName;

            public String get_id() {
                return _id;
            }

            public String getCityName() {
                return cityName;
            }
        }

        public static class Recording implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("astrologerId")
            @Expose
            private String astrologerId;
            @SerializedName("kidsKundaliId")
            @Expose
            private String kidsKundaliId;
            @SerializedName("questionId")
            @Expose
            private String questionId;
            @SerializedName("recordingUrl")
            @Expose
            private List<String> recordingUrl;
            @SerializedName("isActive")
            @Expose
            private boolean isActive;

            public String get_id() {
                return _id;
            }

            public String getAstrologerId() {
                return astrologerId;
            }

            public String getKidsKundaliId() {
                return kidsKundaliId;
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
        }

        public static class State implements Serializable {
            @SerializedName("_id")
            @Expose
            private String _id;
            @SerializedName("stateName")
            @Expose
            private String stateName;

            public String get_id() {
                return _id;
            }

            public String getStateName() {
                return stateName;
            }
        }
    }
}
