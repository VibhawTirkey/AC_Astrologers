package com.astrocure.astrologer.models;

public class AstrologerChatModel {
    boolean isAstrologer = true;
    String text_msg;
    String time;

    public AstrologerChatModel(boolean isAstrologer, String text_msg, String time) {
        this.isAstrologer = isAstrologer;
        this.text_msg = text_msg;
        this.time = time;
    }

    public boolean isAstrologer() {
        return isAstrologer;
    }

    public void setAstrologer(boolean astrologer) {
        isAstrologer = astrologer;
    }

    public String getText_msg() {
        return text_msg;
    }

    public void setText_msg(String text_msg) {
        this.text_msg = text_msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
