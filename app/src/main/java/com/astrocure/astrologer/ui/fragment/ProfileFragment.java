package com.astrocure.astrologer.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.FragmentProfileBinding;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.ProfileViewModel;
import com.bumptech.glide.Glide;

import java.util.StringJoiner;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;
    SideNavigationCallback navigationCallback;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            navigationCallback = (SideNavigationCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + "implementation failed");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.toolbar.setNavigationOnClickListener(v -> navigationCallback.callBackAction(SideNavigationCallback.OPEN_DRAWER));
        Glide.with(requireContext()).load(SPrefClient.getAstrologerDetail(requireContext()).getProfileUrl()).centerCrop().into(binding.profileImage);

        binding.name.setText(SPrefClient.getAstrologerDetail(requireContext()).getUserName());
        binding.nameInfo.setText(SPrefClient.getAstrologerDetail(requireContext()).getUserName());
        binding.contactNumber.setText("+91 " + SPrefClient.getAstrologerDetail(requireContext()).getMobileNo());
        binding.emailId.setText(SPrefClient.getAstrologerDetail(requireContext()).getEmail());

        for (String languageId : SPrefClient.getAstrologerDetail(requireContext()).getLanguage()) {
            viewModel.getLanguageById(languageId);
        }
        for (String expertiseId : SPrefClient.getAstrologerDetail(requireContext()).getExperienceInfo().getExpertise()) {
            viewModel.getExpertiseById(expertiseId);
        }

        StringJoiner joinerLang = new StringJoiner(", ");
        viewModel.getLanguageLiveData().observe(getViewLifecycleOwner(), s -> {
            joinerLang.add(s);
            binding.language.setText(joinerLang.toString());
        });


        StringJoiner joinerExp = new StringJoiner(", ");
        viewModel.getExpertiseLiveData().observe(getViewLifecycleOwner(), s -> {
            joinerExp.add(s);
            binding.expertise.setText(joinerExp.toString());
        });


        return binding.getRoot();
    }
}