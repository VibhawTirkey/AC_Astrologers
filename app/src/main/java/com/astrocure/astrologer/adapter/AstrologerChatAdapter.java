package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemReceiveChatLayoutBinding;
import com.astrocure.astrologer.databinding.ItemSenderChatLayoutBinding;
import com.astrocure.astrologer.models.AstrologerChatModel;

import java.util.List;

public class AstrologerChatAdapter extends RecyclerView.Adapter {
    private static final int SEND_TEXT = 1;
    private static final int RECEIVE_TEXT = 2;
    Context context;
    List<AstrologerChatModel> chatModels;

    public AstrologerChatAdapter(Context context, List<AstrologerChatModel> chatModels) {
        this.context = context;
        this.chatModels = chatModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSenderChatLayoutBinding senderChatLayoutBinding = ItemSenderChatLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        ItemReceiveChatLayoutBinding receiveChatLayoutBinding = ItemReceiveChatLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        switch (viewType){
            case SEND_TEXT:
                return new SenderViewHolder(senderChatLayoutBinding);
            case RECEIVE_TEXT:
                return new ReceiverViewHolder(receiveChatLayoutBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AstrologerChatModel model = chatModels.get(position);
        if (model.isAstrologer()){
            SenderViewHolder viewHolder = (SenderViewHolder) holder;
            viewHolder.binding.message.setText(model.getText_msg());
            viewHolder.binding.time.setText(model.getTime());
        }else {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            viewHolder.binding.message.setText(model.getText_msg());
            viewHolder.binding.time.setText(model.getTime());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatModels.get(position).isAstrologer()){
            return SEND_TEXT;
        }else {
            return RECEIVE_TEXT;
        }
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        ItemSenderChatLayoutBinding binding;

        public SenderViewHolder(ItemSenderChatLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        ItemReceiveChatLayoutBinding binding;

        public ReceiverViewHolder(ItemReceiveChatLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
