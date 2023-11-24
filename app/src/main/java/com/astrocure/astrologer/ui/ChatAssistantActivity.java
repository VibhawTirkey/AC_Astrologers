package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.ChatAssistantAdapter;
import com.astrocure.astrologer.databinding.ActivityChatAssistantBinding;
import com.astrocure.astrologer.models.ChatAssistantModel;

import java.util.ArrayList;
import java.util.List;

public class ChatAssistantActivity extends AppCompatActivity {
    ActivityChatAssistantBinding binding;
    List<ChatAssistantModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatAssistantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getChatData();
        ChatAssistantAdapter adapter = new ChatAssistantAdapter(getApplicationContext(), modelList);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(modelList.size());
    }

    private void getChatData() {
        modelList = new ArrayList<>();
        modelList.add(new ChatAssistantModel("We have received your request. Our customer support executive will get in touch with you within 24 hours.", "10:00 AM", true));
        modelList.add(new ChatAssistantModel("Hi Narendra Modi,\n" +
                "Thank you for contacting AstroCure!\nWe apologize for the inconvenience caused to you. With regard to your query, Ticket No:13821276, We would like to inform you that your issue has been forwarded to our concerned team for the required action to be taken, so we request\n" +
                "you to wait till 25/09/2023 for your issue to get\n" +
                "resolved.\n" +
                "Hope this helps. Please feel free to contact us\n" +
                "if you need further clarification.\n" +
                "\n" +
                "Regards,\n" +
                "AstroCure Support", "10:00 AM", true));
        modelList.add(new ChatAssistantModel("Dear AstroCure Support,\n" +
                "Thank you for your prompt response. I appreciate your attention to my query regarding Ticket No: 13821276.\n" +
                "I understand that my issue has been forwarded to your concerned team for resolution, and I will patiently wait until 25/09/2023 for it to be addressed. Your assistance is much appreciated.\n" +
                "Should I have any further questions or require additional clarification, I will not hesitate to reach out to your support team.\n" +
                "Once again, thank you for your assistance.\n" +
                "\n" +
                "Best regards, \n" +
                "Ambika Sharma", "10:01 AM", false));
        modelList.add(new ChatAssistantModel("Hello", "10:11 AM", false));
        modelList.add(new ChatAssistantModel("Hello", "10:11 AM", true));
        modelList.add(new ChatAssistantModel("We have received your request. Our customer support executive will get in touch with you within 24 hours.", "10:00 AM", true));
        modelList.add(new ChatAssistantModel("We have received your request. Our customer support executive will get in touch with you within 24 hours.", "10:00 AM", false));

    }
}