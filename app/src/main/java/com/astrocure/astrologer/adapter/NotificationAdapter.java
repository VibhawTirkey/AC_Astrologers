package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemNotificationLayoutBinding;
import com.astrocure.astrologer.models.NotificationModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    Context context;
    List<NotificationModel> notificationModelList;

    public NotificationAdapter(Context context, List<NotificationModel> notificationModelList) {
        this.context = context;
        this.notificationModelList = notificationModelList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationLayoutBinding binding = ItemNotificationLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel model = notificationModelList.get(position);
        Glide.with(context).load(model.getDrawable()).into(holder.binding.notifyImg);
        holder.binding.title.setText(model.getTitle());
        holder.binding.description.setText(model.getDescription());
        holder.binding.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        ItemNotificationLayoutBinding binding;

        public NotificationViewHolder(ItemNotificationLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
