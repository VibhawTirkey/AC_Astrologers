package com.astrocure.astrologer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ItemSessionLogBinding;
import com.astrocure.astrologer.models.responseModels.SessionLogResponseModel;
import com.astrocure.astrologer.utils.AppConstants;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SessionLogAdapter extends RecyclerView.Adapter<SessionLogAdapter.SessionViewHolder> {
    private Context context;
    private List<SessionLogResponseModel.Datum> sessionList;

    public SessionLogAdapter(Context context, List<SessionLogResponseModel.Datum> sessionList) {
        this.context = context;
        this.sessionList = sessionList;
    }

    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSessionLogBinding binding = ItemSessionLogBinding.inflate(LayoutInflater.from(context), parent, false);
        return new SessionViewHolder(binding);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        SessionLogResponseModel.Datum log = sessionList.get(position);
        Glide.with(context).load(log.getCounsellingType().matches("call") ? R.drawable.ic_call_home : R.drawable.ic_chat_home).into(holder.binding.typeImg);
        holder.binding.mode.setText(log.getCounsellingType());
        try {
            Date loginTime = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(log.getStartDateTime());
            Date logoutTime = new SimpleDateFormat(AppConstants.SERVER_TIME_FORMAT).parse(log.getEndDateTime());
            assert loginTime != null;
            holder.binding.date.setText(new SimpleDateFormat("dd MMM yy").format(loginTime));
            holder.binding.loginTime.setText(new SimpleDateFormat("dd MMM yy, hh:mm a").format(loginTime));
            assert logoutTime != null;
            holder.binding.logoutTime.setText(new SimpleDateFormat("dd MMM yy, hh:mm a").format(logoutTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    public static class SessionViewHolder extends RecyclerView.ViewHolder {
        ItemSessionLogBinding binding;

        public SessionViewHolder(ItemSessionLogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
