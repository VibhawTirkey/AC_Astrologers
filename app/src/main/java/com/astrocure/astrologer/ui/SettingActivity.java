package com.astrocure.astrologer.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.SettingAdapter;
import com.astrocure.astrologer.databinding.ActivitySettingBinding;
import com.astrocure.astrologer.databinding.DialogLogOutBinding;
import com.astrocure.astrologer.models.DocumentModel;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    List<DocumentModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getDocumentData();
        SettingAdapter adapter = new SettingAdapter(getApplicationContext(), list);
        binding.settingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.settingList.setAdapter(adapter);
        adapter.setOnItemActionListener(type -> {
            switch (type) {
                case "Change Password":
                    Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                case "Share App":
                    Toast.makeText(this, "Share App", Toast.LENGTH_SHORT).show();
                    break;
                case "About us":
                    Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                    break;
                case "Change Bank Details Request":
                    Toast.makeText(this, "Change Bank Details Request", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        binding.logout.setOnClickListener(v -> {
            Dialog dialog = new Dialog(SettingActivity.this);
            DialogLogOutBinding logOutBinding = DialogLogOutBinding.inflate(getLayoutInflater());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(logOutBinding.getRoot());
            dialog.show();
        });
    }

    private void getDocumentData() {
        list = new ArrayList<>();
        list.add(new DocumentModel(R.drawable.ic_password_setting, "Change Password"));
        list.add(new DocumentModel(R.drawable.ic_share_setting, "Share App"));
        list.add(new DocumentModel(R.drawable.ic_about_us_setting, "About us"));
        list.add(new DocumentModel(R.drawable.ic_bank_setting, "Change Bank Details Request"));
        list.add(new DocumentModel(R.drawable.ic_change_mobile_setting, "Change Mobile Number Request"));
        list.add(new DocumentModel(R.drawable.ic_terms_and_condition_setting, "Terms and Conditions"));
    }
}