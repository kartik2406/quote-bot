package com.example.redditclone.chatbot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kartik on 15/11/17.
 */

public class MessageDateFormat {
    private static  final String DATE_FORMAT = "hh:mm";
    DateFormat dateFormat;
    private static MessageDateFormat messageDateFormat = null;

    private MessageDateFormat() {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    public static  MessageDateFormat getInstance(){
        if(messageDateFormat != null){
            return  messageDateFormat;
        } else {
            messageDateFormat = new MessageDateFormat();
            return messageDateFormat;
        }
    }

    public  String getFormattedDate(){
        return dateFormat.format(new Date());
    }
}
