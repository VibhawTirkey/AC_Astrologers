package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeDataRequestModel {
    @SerializedName("astroId")
    @Expose
    private String astroId;
    @SerializedName("requestId")
    @Expose
    private String requestId;
    @SerializedName("requestStatus")
    @Expose
    private String requestStatus;

    public ChangeDataRequestModel(String astroId, String requestId, String requestStatus) {
        this.astroId = astroId;
        this.requestId = requestId;
        this.requestStatus = requestStatus;
    }

    public void setAstroId(String astroId) {
        this.astroId = astroId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}
