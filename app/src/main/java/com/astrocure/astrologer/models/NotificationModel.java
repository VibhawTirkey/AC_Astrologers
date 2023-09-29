package com.astrocure.astrologer.models;

public class NotificationModel {
    int drawable;
    String title;
    String description;
    String time;

    public NotificationModel(int drawable, String title, String description, String time) {
        this.drawable = drawable;
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
