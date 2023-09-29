package com.astrocure.astrologer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ItemHelpQuestionLayoutBinding;
import com.astrocure.astrologer.models.HelpQuestionModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class HelpQueryAdapter extends RecyclerView.Adapter<HelpQueryAdapter.HelpViewHolder> {
    Context context;
    List<HelpQuestionModel> questionModelList;

    public HelpQueryAdapter(Context context, List<HelpQuestionModel> questionModelList) {
        this.context = context;
        this.questionModelList = questionModelList;
    }

    @NonNull
    @Override
    public HelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHelpQuestionLayoutBinding binding = ItemHelpQuestionLayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HelpViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpViewHolder holder, int position) {
        HelpQuestionModel model = questionModelList.get(position);
        holder.binding.title.setText(model.getQuestion());
        holder.binding.answer.setText(model.getAnswer());
        holder.binding.expand.setOnClickListener(v -> {
            if (holder.binding.answer.getVisibility() == View.VISIBLE){
                holder.binding.answer.setVisibility(View.GONE);
                Glide.with(context).load(R.drawable.ic_add).into(holder.binding.expand);
            }else {
                holder.binding.answer.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.ic_subtract).into(holder.binding.expand);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionModelList.size();
    }

    public class HelpViewHolder extends RecyclerView.ViewHolder {
        ItemHelpQuestionLayoutBinding binding;
        public HelpViewHolder(ItemHelpQuestionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
