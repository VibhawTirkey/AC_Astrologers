package com.astrocure.astrologer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.astrologer.MainApplication;
import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.dao.TaskDaoInterface;
import com.astrocure.astrologer.databinding.FragmentProfileBinding;
import com.astrocure.astrologer.utils.AppConstants;
import com.astrocure.astrologer.utils.SPrefClient;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    SideNavigationCallback navigationCallback;

    TaskDaoInterface daoInterface =

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        binding.toolbar.setNavigationOnClickListener(v -> navigationCallback.callBackAction(SideNavigationCallback.OPEN_DRAWER));
        Glide.with(getContext()).load(AppConstants.PROFILE_IMG).centerCrop().into(binding.profileImage);

        binding.name.setText(SPrefClient.getAstrologerDetail(requireContext()).getUserName());
        binding.nameInfo.setText(SPrefClient.getAstrologerDetail(requireContext()).getUserName());
        binding.contactNumber.setText(String.valueOf("+91 "+SPrefClient.getAstrologerDetail(requireContext()).getMobileNo()));
        binding.emailId.setText(SPrefClient.getAstrologerDetail(requireContext()).getEmail());

        return binding.getRoot();
    }
}