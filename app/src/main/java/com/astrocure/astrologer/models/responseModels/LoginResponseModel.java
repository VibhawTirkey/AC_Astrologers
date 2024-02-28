package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseModel {
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

        @SerializedName("bankDetails")
        @Expose
        private BankDetails bankDetails;
        @SerializedName("documents")
        @Expose
        private Documents documents;
        @SerializedName("experienceInfo")
        @Expose
        private ExperienceInfo experienceInfo;
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
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("language")
        @Expose
        private List<String> language;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("profileCreated")
        @Expose
        private boolean profileCreated;
        @SerializedName("memberType")
        @Expose
        private String memberType;
        @SerializedName("walletAmount")
        @Expose
        private long walletAmount;
        @SerializedName("platform")
        @Expose
        private String platform;
        @SerializedName("emailVerified")
        @Expose
        private boolean emailVerified;
        @SerializedName("rating")
        @Expose
        private long rating;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private long v;
        @SerializedName("firebaseToken")
        @Expose
        private String firebaseToken;
        @SerializedName("forcePasswordReset")
        @Expose
        private boolean forcePasswordReset;
        @SerializedName("id")
        @Expose
        private String id;

        public boolean isForcePasswordReset() {
            return forcePasswordReset;
        }
        public BankDetails getBankDetails() {
            return bankDetails;
        }

        public Documents getDocuments() {
            return documents;
        }

        public ExperienceInfo getExperienceInfo() {
            return experienceInfo;
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

        public String getLocation() {
            return location;
        }

        public String getState() {
            return state;
        }

        public List<String> getLanguage() {
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

        public long getWalletAmount() {
            return walletAmount;
        }

        public String getPlatform() {
            return platform;
        }

        public boolean isEmailVerified() {
            return emailVerified;
        }

        public long getRating() {
            return rating;
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

        public String getFirebaseToken() {
            return firebaseToken;
        }

        public String getId() {
            return id;
        }

        public static class ExperienceInfo {

            @SerializedName("expertise")
            @Expose
            private List<String> expertise;
            @SerializedName("experience")
            @Expose
            private String experience;
            @SerializedName("dateOfJoining")
            @Expose
            private String dateOfJoining;
            @SerializedName("callCharges")
            @Expose
            private long callCharges;
            @SerializedName("chatCharges")
            @Expose
            private long chatCharges;
            @SerializedName("instantCharges")
            @Expose
            private long instantCharges;
            @SerializedName("callAvailablity")
            @Expose
            private boolean callAvailability;
            @SerializedName("chatAvilablity")
            @Expose
            private boolean chatAvailability;
            @SerializedName("personalInfo")
            @Expose
            private String personalInfo;

            public List<String> getExpertise() {
                return expertise;
            }

            public String getExperience() {
                return experience;
            }

            public String getDateOfJoining() {
                return dateOfJoining;
            }

            public long getCallCharges() {
                return callCharges;
            }

            public long getChatCharges() {
                return chatCharges;
            }

            public long getInstantCharges() {
                return instantCharges;
            }

            public boolean isCallAvailability() {
                return callAvailability;
            }

            public boolean isChatAvailability() {
                return chatAvailability;
            }

            public String getPersonalInfo() {
                return personalInfo;
            }
        }

        public static class BankDetails {

            @SerializedName("accountNumber")
            @Expose
            private long accountNumber;
            @SerializedName("ifscCode")
            @Expose
            private String ifscCode;
            @SerializedName("bankName")
            @Expose
            private String bankName;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("branch")
            @Expose
            private String branch;

            public long getAccountNumber() {
                return accountNumber;
            }

            public String getIfscCode() {
                return ifscCode;
            }

            public String getBankName() {
                return bankName;
            }

            public String getCity() {
                return city;
            }

            public String getBranch() {
                return branch;
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

        public static class Documents {

            @SerializedName("aadharCard")
            @Expose
            private String aadharCard;
            @SerializedName("panCard")
            @Expose
            private String panCard;
            @SerializedName("experience")
            @Expose
            private List<String> experience;
            @SerializedName("marksheets")
            @Expose
            private List<String> marksheets;
            @SerializedName("passbook")
            @Expose
            private String passbook;
            @SerializedName("form16")
            @Expose
            private String form16;
            @SerializedName("qualificationCeritificate")
            @Expose
            private String qualificationCeritificate;

            public String getAadharCard() {
                return aadharCard;
            }

            public String getPanCard() {
                return panCard;
            }

            public List<String> getExperience() {
                return experience;
            }

            public List<String> getMarksheets() {
                return marksheets;
            }

            public String getPassbook() {
                return passbook;
            }

            public String getForm16() {
                return form16;
            }

            public String getQualificationCeritificate() {
                return qualificationCeritificate;
            }
        }
    }
}
