package com.astrocure.astrologer.models;

public class ChatAssistantModel {
    String txt_msg;
    String time;
    boolean isAdmin;

    public ChatAssistantModel(String txt_msg, String time, boolean isAdmin) {
        this.txt_msg = txt_msg;
        this.time = time;
        this.isAdmin = isAdmin;
    }

    public String getTxt_msg() {
        return txt_msg;
    }

    public void setTxt_msg(String txt_msg) {
        this.txt_msg = txt_msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
