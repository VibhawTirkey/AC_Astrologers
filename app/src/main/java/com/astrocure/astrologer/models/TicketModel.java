package com.astrocure.astrologer.models;

public class TicketModel {
    String topic;
    String ticketId;
    boolean isClosed;
    String date;
    String time;

    public TicketModel(String topic, String ticketId, boolean isClosed, String date, String time) {
        this.topic = topic;
        this.ticketId = ticketId;
        this.isClosed = isClosed;
        this.date = date;
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
