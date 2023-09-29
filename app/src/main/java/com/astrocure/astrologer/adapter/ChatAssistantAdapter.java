package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemReceiveChatAssistBinding;
import com.astrocure.astrologer.databinding.ItemSenderChatAssistBinding;
import com.astrocure.astrologer.models.ChatAssistantModel;

import java.util.List;

public class ChatAssistantAdapter extends RecyclerView.Adapter {
    private static final int TXT_RECEIVE = 1;
    private static final int TXT_SEND = 2;
    Context context;
    List<ChatAssistantModel> chatList;

    public ChatAssistantAdapter(Context context, List<ChatAssistantModel> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSenderChatAssistBinding senderChatAssistBinding = ItemSenderChatAssistBinding.inflate(LayoutInflater.from(context),parent,false);
        ItemReceiveChatAssistBinding receiveChatAssistBinding = ItemReceiveChatAssistBinding.inflate(LayoutInflater.from(context),parent,false);
        switch (viewType){
            case TXT_SEND:
                return new SenderViewHolder(senderChatAssistBinding);
            case TXT_RECEIVE:
                return new ReceiverViewHolder(receiveChatAssistBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatAssistantModel model = chatList.get(position);
        if (model.isAdmin()){
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            viewHolder.binding.txtMsg.setText(model.getTxt_msg());
        }else {
            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.binding.txtMsg.setText(model.getTxt_msg());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).isAdmin()){
            return TXT_RECEIVE;
        }else {
            return TXT_SEND;
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        ItemSenderChatAssistBinding binding;
        public SenderViewHolder(ItemSenderChatAssistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        ItemReceiveChatAssistBinding binding;
        public ReceiverViewHolder(ItemReceiveChatAssistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
