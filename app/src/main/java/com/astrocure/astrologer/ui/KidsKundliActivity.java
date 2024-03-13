package com.astrocure.astrologer.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.adapter.KidsRecordingAdapter;
import com.astrocure.astrologer.adapter.KundliQueNumAdapter;
import com.astrocure.astrologer.databinding.ActivityKidsKundliBinding;

public class KidsKundliActivity extends AppCompatActivity {
    ActivityKidsKundliBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityKidsKundliBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        KundliQueNumAdapter numAdapter = new KundliQueNumAdapter(getApplicationContext());
        binding.questionNumList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6, LinearLayoutManager.VERTICAL, false));
        binding.questionNumList.setAdapter(numAdapter);

        KidsRecordingAdapter recordingAdapter = new KidsRecordingAdapter(getApplicationContext());
        binding.recordingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.recordingList.setAdapter(recordingAdapter);
    }
}