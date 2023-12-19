package com.astrocure.astrologer.models.responseModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BindResponseModel {
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

    @Entity
    public static class Datum {
        @PrimaryKey
        @SerializedName("_id")
        @Expose
        private String id;
        @ColumnInfo(name = "dataName")
        @SerializedName("dataName")
        @Expose
        private String dataName;
        @ColumnInfo(name = "isActive")
        @SerializedName("isActive")
        @Expose
        private boolean isActive;
        @ColumnInfo(name = "date")
        @SerializedName("date")
        @Expose
        private String date;
        @ColumnInfo(name = "__v")
        @SerializedName("__v")
        @Expose
        private long v;

        public String getId() {
            return id;
        }

        public String getDataName() {
            return dataName;
        }

        public boolean isActive() {
            return isActive;
        }

        public String getDate() {
            return date;
        }

        public long getV() {
            return v;
        }
    }
}
