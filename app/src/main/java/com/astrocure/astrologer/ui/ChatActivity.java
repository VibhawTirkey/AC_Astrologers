package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.AstrologerChatAdapter;
import com.astrocure.astrologer.databinding.ActivityChatBinding;
import com.astrocure.astrologer.models.AstrologerChatModel;
import com.astrocure.astrologer.utils.AppConstants;
import com.astrocure.astrologer.viewModel.ChatViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private List<AstrologerChatModel> chatModels;
    public ChatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewModel.openSocket();

        Glide.with(getApplicationContext()).load(AppConstants.PROFILE_IMG).into(binding.profileImage);

        setChatData();
        AstrologerChatAdapter adapter = new AstrologerChatAdapter(getApplicationContext(), chatModels);
        binding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(adapter);
        binding.chatList.smoothScrollToPosition(chatModels.size());
    }

    private void setChatData() {
        chatModels = new ArrayList<>();
        chatModels.add(new AstrologerChatModel(false, "Hi Narendra Modi Below are my details:\nName: Yash Tiwari\nDOB: 1995-07-20\nTOB: 13:05.05\nPOB: Bangalore, Karnataka", "10:13 am"));
        chatModels.add(new AstrologerChatModel(true, "Astrologer is analysing your details\ncan ask your question in the meantime", "10:13 am"));
        chatModels.add(new AstrologerChatModel(false, "Hi there! I've been having some troubles in my love life recently. Any insights you can offer, astrologer?", "10:13 am"));
        chatModels.add(new AstrologerChatModel(true, "Hello! I'd be glad to help. Can you provide me with your birthdate and the birthdate of your partner?", "10:13 am"));
        chatModels.add(new AstrologerChatModel(false, "oh ok", "10:13 am"));
        chatModels.add(new AstrologerChatModel(true, "oh ok", "10:13 am"));
        chatModels.add(new AstrologerChatModel(true, "Astrologer is analysing your details\ncan ask your question in the meantime", "10:13 am"));
        chatModels.add(new AstrologerChatModel(false, "Hi there! I've been having some troubles in my love life recently. Any insights you can offer, astrologer?", "10:13 am"));
        chatModels.add(new AstrologerChatModel(true, "Hello! I'd be glad to help. Can you provide me with your birthdate and the birthdate of your partner?", "10:13 am"));
        chatModels.add(new AstrologerChatModel(false, "oh ok", "10:13 am"));
    }
}