package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemSettingNameBinding;
import com.astrocure.astrologer.models.DocumentModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {
    Context context;
    List<DocumentModel> list;
    private OnItemActionListener onItemActionListener;

    public SettingAdapter(Context context, List<DocumentModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSettingNameBinding binding = ItemSettingNameBinding.inflate(LayoutInflater.from(context), parent, false);
        return new SettingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        holder.binding.documentName.setText(list.get(position).getDocName());
        Glide.with(context).load(list.get(position).getImg()).into(holder.binding.settingImg);
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.itemClick(list.get(position).getDocName(), position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    public interface OnItemActionListener {
        void itemClick(String type, int position);
    }

    public static class SettingViewHolder extends RecyclerView.ViewHolder {
        ItemSettingNameBinding binding;

        public SettingViewHolder(ItemSettingNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
