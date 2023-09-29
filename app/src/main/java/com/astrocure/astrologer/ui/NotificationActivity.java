package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.NotificationAdapter;
import com.astrocure.astrologer.databinding.ActivityNotificationBinding;
import com.astrocure.astrologer.models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    List<NotificationModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        list = new ArrayList<>();
        list.add(new NotificationModel(R.drawable.ic_wallet_notify, "500 add in your wallet", "Lorem ipsum simupm suem", "2 min ago"));
        list.add(new NotificationModel(R.drawable.ic_wallet_notify, "230 add in your wallet", "Lorem ipsum simupm suem", "5 min ago"));
        list.add(new NotificationModel(R.drawable.ic_wallet_notify, "230 add in your wallet", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "5 min ago"));
        list.add(new NotificationModel(R.drawable.ic_chat_notify, "New Comment", "Lorem ipsum simupm suem", "7 min ago"));
        list.add(new NotificationModel(R.drawable.ic_call_notify, "Ashish Calling you", "Lorem ipsum simupm suem", "9 min ago"));
        list.add(new NotificationModel(R.drawable.ic_chat_request_notify, "Chat Request Got from Ashish", "Lorem ipsum simupm suem", "12 min ago"));
        list.add(new NotificationModel(R.drawable.ic_call_notify, "Parmeet Calling you", "Lorem ipsum simupm suem", "17 min ago"));
        NotificationAdapter adapter = new NotificationAdapter(getApplicationContext(), list);
        binding.notificationList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.notificationList.setAdapter(adapter);
    }
}