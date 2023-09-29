package com.astrocure.astrologer.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.astrocure.astrologer.callback.SideNavigationCallback;
import com.astrocure.astrologer.databinding.FragmentProfileBinding;
import com.astrocure.astrologer.utils.AppConstants;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        binding.toolbar.setNavigationOnClickListener(v -> navigationCallback.callBackAction(SideNavigationCallback.OPEN_DRAWER));
        Glide.with(getContext()).load(AppConstants.PROFILE_IMG).into(binding.profileImage);

        return binding.getRoot();
    }
}