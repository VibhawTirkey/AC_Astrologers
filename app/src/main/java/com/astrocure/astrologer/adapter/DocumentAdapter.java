package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemDocumentNameBinding;
import com.astrocure.astrologer.models.DocumentModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {
    Context context;
    List<DocumentModel> list;

    public DocumentAdapter(Context context, List<DocumentModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDocumentNameBinding binding = ItemDocumentNameBinding.inflate(LayoutInflater.from(context), parent, false);
        return new DocumentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        holder.binding.documentName.setText(list.get(position).getDocName());
        Glide.with(context).load(list.get(position).getImg()).into(holder.binding.documentImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {
        ItemDocumentNameBinding binding;

        public DocumentViewHolder(ItemDocumentNameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
