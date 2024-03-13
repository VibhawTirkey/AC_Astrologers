package com.astrocure.astrologer.models.requestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PredictionReplyRequestModel {
    @SerializedName("astrologerId")
    @Expose
    private String astrologerId;
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
    private String answerDateTime;
    @SerializedName("_id")
    @Expose
    private String id;

    public PredictionReplyRequestModel() {
    }

    public PredictionReplyRequestModel(String astrologerId, String title, String hashTag, String question, String answer, String predicationDateTime, String answerDateTime, String id) {
        this.astrologerId = astrologerId;
        this.title = title;
        this.hashTag = hashTag;
        this.question = question;
        this.answer = answer;
        this.predicationDateTime = predicationDateTime;
        this.answerDateTime = answerDateTime;
        this.id = id;
    }

    public void setAstrologerId(String astrologerId) {
        this.astrologerId = astrologerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPredicationDateTime(String predicationDateTime) {
        this.predicationDateTime = predicationDateTime;
    }

    public void setAnswerDateTime(String answerDateTime) {
        this.answerDateTime = answerDateTime;
    }

    public void setId(String id) {
        this.id = id;
    }
}
