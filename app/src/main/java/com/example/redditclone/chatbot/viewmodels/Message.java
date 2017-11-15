package com.example.redditclone.chatbot.viewmodels;

/**
 * Created by kartik on 15/11/17.
 */

public class Message {
    String message;
    long createdAt;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
