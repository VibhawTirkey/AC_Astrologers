package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewListRequestModel {
    @SerializedName("astrologer_id")
    @Expose
    private String astrologerId;
    @SerializedName("filter")
    @Expose
    private long filter;
    @SerializedName("filter_by")
    @Expose
    private long filterBy;

    public ReviewListRequestModel(String astrologerId, long filter, long filterBy) {
        this.astrologerId = astrologerId;
        this.filter = filter;
        this.filterBy = filterBy;
    }
}
