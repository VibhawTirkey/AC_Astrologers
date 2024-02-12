package com.astrocure.astrologer.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.databinding.ActivityProfileBinding;
import com.astrocure.astrologer.utils.AppUtilMethods;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.ProfileViewModel;
import com.bumptech.glide.Glide;

import java.util.StringJoiner;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private ProfileViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.close.setOnClickListener(v -> onBackPressed());
        Glide.with(getApplicationContext()).load(SPrefClient.getAstrologerDetail(getApplicationContext()).getProfileUrl()).into(binding.profileImage);

        binding.name.setText(SPrefClient.getAstrologerDetail(getApplicationContext()).getUserName());
        binding.bio.setText(SPrefClient.getAstrologerDetail(getApplicationContext()).getExperienceInfo().getPersonalInfo());

        viewModel.getExperienceById(SPrefClient.getAstrologerDetail(getApplicationContext()).getExperienceInfo().getExperience());
        for (String languageId : SPrefClient.getAstrologerDetail(getApplicationContext()).getLanguage()) {
            viewModel.getLanguageById(languageId);
        }
        for (String expertiseId : SPrefClient.getAstrologerDetail(getApplicationContext()).getExperienceInfo().getExpertise()) {
            viewModel.getExpertiseById(expertiseId);
        }

        StringJoiner joinerLang = new StringJoiner(", ");
        viewModel.getLanguageLiveData().observe(this, s -> {
            joinerLang.add(s);
            binding.language.setText(joinerLang.toString());
        });


        StringJoiner joinerExp = new StringJoiner(", ");
        viewModel.getExpertiseLiveData().observe(this, s -> {
            joinerExp.add(s);
            binding.expertise.setText(joinerExp.toString());
        });
        viewModel.getExperienceLiveData().observe(this, s -> {
            binding.experience.setText(s);
        });

        binding.chatPrice.setText(SPrefClient.getAstrologerDetail(getApplicationContext()).getExperienceInfo().getChatCharges() + "/min");
        binding.callPrice.setText(SPrefClient.getAstrologerDetail(getApplicationContext()).getExperienceInfo().getCallCharges() + "/min");
        binding.rating.setText(AppUtilMethods.prettyCount(SPrefClient.getAstrologerDetail(getApplicationContext()).getRating()));
    }
}