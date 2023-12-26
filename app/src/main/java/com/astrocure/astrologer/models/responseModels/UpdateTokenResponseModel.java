package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateTokenResponseModel {
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

        @SerializedName("documents")
        @Expose
        private Documents documents;
        @SerializedName("permissions")
        @Expose
        private Permissions permissions;
        @SerializedName("_id")
        @Expose
        private String uid;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("mobileNo")
        @Expose
        private long mobileNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("profileUrl")
        @Expose
        private String profileUrl;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("tob")
        @Expose
        private String tob;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("profileCreated")
        @Expose
        private boolean profileCreated;
        @SerializedName("memberType")
        @Expose
        private String memberType;
        @SerializedName("zodiacId")
        @Expose
        private String zodiacId;
        @SerializedName("walletAmount")
        @Expose
        private long walletAmount;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private long v;
        @SerializedName("emailVerified")
        @Expose
        private boolean emailVerified;
        @SerializedName("firebaseToken")
        @Expose
        private String firebaseToken;
        @SerializedName("platform")
        @Expose
        private String platform;
        @SerializedName("rating")
        @Expose
        private long rating;
        @SerializedName("id")
        @Expose
        private String id;

        public Documents getDocuments() {
            return documents;
        }

        public Permissions getPermissions() {
            return permissions;
        }

        public String getUid() {
            return uid;
        }

        public String getUserName() {
            return userName;
        }

        public long getMobileNo() {
            return mobileNo;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getProfileUrl() {
            return profileUrl;
        }

        public String getDob() {
            return dob;
        }

        public String getTob() {
            return tob;
        }

        public String getLocation() {
            return location;
        }

        public String getLanguage() {
            return language;
        }

        public String getGender() {
            return gender;
        }

        public boolean isProfileCreated() {
            return profileCreated;
        }

        public String getMemberType() {
            return memberType;
        }

        public String getZodiacId() {
            return zodiacId;
        }

        public long getWalletAmount() {
            return walletAmount;
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

        public boolean isEmailVerified() {
            return emailVerified;
        }

        public String getFirebaseToken() {
            return firebaseToken;
        }

        public String getPlatform() {
            return platform;
        }

        public long getRating() {
            return rating;
        }

        public String getId() {
            return id;
        }

        public static class Documents {

            @SerializedName("experience")
            @Expose
            private List<Object> experience;
            @SerializedName("marksheets")
            @Expose
            private List<Object> marksheets;

            public List<Object> getExperience() {
                return experience;
            }

            public List<Object> getMarksheets() {
                return marksheets;
            }
        }

        public static class Permissions {

            @SerializedName("accessList")
            @Expose
            private List<Object> accessList;

            public List<Object> getAccessList() {
                return accessList;
            }
        }
    }
}
