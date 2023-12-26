package com.astrocure.astrologer.models.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CounsellingDetailResponseModel {
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

        @SerializedName("initialAvailabilitySelection")
        @Expose
        private InitialAvailabilitySelection initialAvailabilitySelection;
        @SerializedName("currentAvailabilityStatus")
        @Expose
        private CurrentAvailabilityStatus currentAvailabilityStatus;
        @SerializedName("nextAvailability")
        @Expose
        private List<Object> nextAvailability;

        public InitialAvailabilitySelection getInitialAvailabilitySelection() {
            return initialAvailabilitySelection;
        }

        public CurrentAvailabilityStatus getCurrentAvailabilityStatus() {
            return currentAvailabilityStatus;
        }

        public List<Object> getNextAvailability() {
            return nextAvailability;
        }

        public static class InitialAvailabilitySelection {

            @SerializedName("primaryCounselling")
            @Expose
            private String primaryCounselling;
            @SerializedName("secondaryCounselling")
            @Expose
            private String secondaryCounselling;

            public String getPrimaryCounselling() {
                return primaryCounselling;
            }

            public String getSecondaryCounselling() {
                return secondaryCounselling;
            }
        }

        public static class CurrentAvailabilityStatus {

            @SerializedName("callAvailability")
            @Expose
            private boolean callAvailability;
            @SerializedName("chatAvilability")
            @Expose
            private boolean chatAvailability;

            public boolean isCallAvailability() {
                return callAvailability;
            }

            public boolean isChatAvailability() {
                return chatAvailability;
            }
        }
    }
}
