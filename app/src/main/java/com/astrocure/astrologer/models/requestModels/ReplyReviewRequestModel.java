package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyReviewRequestModel {
    @SerializedName("review_id")
    @Expose
    private String reviewId;
    @SerializedName("reply_message")
    @Expose
    private String replyMessage;
    @SerializedName("edit")
    @Expose
    private long edit;
    @SerializedName("reply_id")
    @Expose
    private String replyId;

    public ReplyReviewRequestModel() {
    }

    public ReplyReviewRequestModel(String reviewId, String replyMessage, long edit, String replyId) {
        this.reviewId = reviewId;
        this.replyMessage = replyMessage;
        this.edit = edit;
        this.replyId = replyId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public void setEdit(long edit) {
        this.edit = edit;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
}
