package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageCounsellingRequestModel {
    @SerializedName("astrologerId")
    @Expose
    private String astrologerId;
    @SerializedName("secondaryCounsellingType")
    @Expose
    private String secondaryCounsellingType;
    @SerializedName("status")
    @Expose
    private long status;

    public ManageCounsellingRequestModel(String astrologerId, String secondaryCounsellingType, long status) {
        this.astrologerId = astrologerId;
        this.secondaryCounsellingType = secondaryCounsellingType;
        this.status = status;
    }
}
