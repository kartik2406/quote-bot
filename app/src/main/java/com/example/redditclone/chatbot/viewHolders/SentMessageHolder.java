package com.example.redditclone.chatbot.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.redditclone.chatbot.R;
import com.example.redditclone.chatbot.viewmodels.Message;

/**
 * Created by kartik on 15/11/17.
 */

public class SentMessageHolder extends RecyclerView.ViewHolder {
    private TextView messageBody;
    private TextView messageTime;
    public SentMessageHolder(final View sentMsgView) {
        super(sentMsgView);
        Log.d("SendMsgHolderConstructo", sentMsgView.toString());
        messageBody = (TextView) sentMsgView.findViewById(R.id.text_message_body);
        messageTime = ( TextView ) sentMsgView.findViewById(R.id.text_message_time);
    }

    public void bindData(Message message) {
        Log.d("Bind Data", message.getMessage());
        messageBody.setText(message.getMessage());
        messageTime.setText(message.getTimeStamp());
    }
}
