package com.example.redditclone.chatbot.viewmodels;

/**
 * Created by kartik on 15/11/17.
 */

public class Message {
    String message;
    String user;
    String timeStamp;

    public Message(String message, String user, String timeStamp) {
        this.message = message;
        this.user = user;
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
