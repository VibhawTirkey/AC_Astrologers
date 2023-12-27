package com.astrocure.astrologer.ui;

import static com.astrocure.astrologer.service.AppFirebaseMessagingService.NOTIFICATION_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.view.WindowManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.databinding.ActivityChatRequestBinding;

public class ChatRequestActivity extends AppCompatActivity {

    private ActivityChatRequestBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID);
        NotificationManagerCompat.from(this).cancel(1);
    }
}