package com.example.redditclone.chatbot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditclone.chatbot.R;
import com.example.redditclone.chatbot.viewHolders.SentMessageHolder;
import com.example.redditclone.chatbot.viewmodels.Message;

import java.util.List;

/**
 * Created by kartik on 15/11/17.
 */

public class MessageListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Message> messageList;

    public MessageListAdapter(List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SentMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SentMessageHolder) holder).bindData(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.sent_message;
    }

    public void addMessage(Message message){
        messageList.add(message);
        notifyDataSetChanged();
    }
}
