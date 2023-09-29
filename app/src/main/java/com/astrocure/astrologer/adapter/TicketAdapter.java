package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.databinding.ItemViewTicketBinding;
import com.astrocure.astrologer.models.TicketModel;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    Context context;
    List<TicketModel> ticketModels;

    public TicketAdapter(Context context, List<TicketModel> ticketModels) {
        this.context = context;
        this.ticketModels = ticketModels;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewTicketBinding binding = ItemViewTicketBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TicketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        TicketModel model = ticketModels.get(position);
        holder.binding.title.setText(model.getTopic());
        holder.binding.ticketId.setText(model.getTicketId());
        if (model.isClosed()){
            holder.binding.status.setText("Closed");
            holder.binding.status.setTextColor(Color.parseColor("#F11111"));
        }else {
            holder.binding.status.setText("On Progress");
            holder.binding.status.setTextColor(Color.parseColor("#088123"));
        }
        holder.binding.date.setText(model.getDate());
        holder.binding.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return ticketModels.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ItemViewTicketBinding binding;

        public TicketViewHolder(ItemViewTicketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
