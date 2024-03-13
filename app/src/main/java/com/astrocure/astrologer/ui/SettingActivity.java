package com.astrocure.astrologer.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.SettingAdapter;
import com.astrocure.astrologer.databinding.ActivitySettingBinding;
import com.astrocure.astrologer.databinding.DialogLogOutBinding;
import com.astrocure.astrologer.databinding.DialogPasswordCreatedBinding;
import com.astrocure.astrologer.databinding.DialogRequestConfirmationBinding;
import com.astrocure.astrologer.models.DocumentModel;
import com.astrocure.astrologer.utils.SPrefClient;
import com.astrocure.astrologer.viewModel.SettingViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    List<DocumentModel> list;
    String bankRequestId, mobileRequestId, requestStatusId;
    private SettingViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        viewModel.getRequestTypeLiveData().observe(this, data -> {
            data.forEach(datum -> {
                if (datum.getDataName().matches("Bank Details")) {
                    bankRequestId = datum.getId();
                } else if (datum.getDataName().matches("Mobile Number")) {
                    mobileRequestId = datum.getId();
                }
            });
        });

        viewModel.getRequestStatusTypeLiveData().observe(this, data -> {
            data.forEach(datum -> {
                if (datum.getDataName().matches("Request")) {
                    requestStatusId = datum.getId();
                }
            });
        });

        getDocumentData();
        SettingAdapter adapter = new SettingAdapter(getApplicationContext(), list);
        binding.settingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.settingList.setAdapter(adapter);
        adapter.setOnItemActionListener((type, position) -> {
            switch (position) {
                case 0:
                    Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(this, "Share App", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Dialog dialogBank = new Dialog(SettingActivity.this);
                    DialogRequestConfirmationBinding confirmationBinding = DialogRequestConfirmationBinding.inflate(getLayoutInflater());
                    Objects.requireNonNull(dialogBank.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialogBank.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialogBank.setContentView(confirmationBinding.getRoot());
                    confirmationBinding.cancel.setOnClickListener(v1 -> dialogBank.dismiss());
                    confirmationBinding.title.setText("Change Bank Details Request");
                    confirmationBinding.content.setText("Are you sure you want to send the request?");
                    confirmationBinding.ok.setOnClickListener(v -> {
                        viewModel.getRequestSentLiveData(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), bankRequestId, requestStatusId).observe(this, responseModel -> {
                            if (responseModel == null) {
                                Toast.makeText(this, "Your request is already submitted", Toast.LENGTH_SHORT).show();
                                dialogBank.dismiss();
                            } else {
                                Dialog resultDialog = new Dialog(SettingActivity.this);
                                DialogPasswordCreatedBinding resultBinding = DialogPasswordCreatedBinding.inflate(getLayoutInflater());
                                Objects.requireNonNull(resultDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                resultDialog.setContentView(resultBinding.getRoot());
                                resultBinding.textView23.setVisibility(View.GONE);
                                resultBinding.textView22.setText("Your request has been sent successfully. Our support team will get in touch with you within the next 24 hours.");
                                resultBinding.gotoLogin.setText("Okay");
                                resultBinding.gotoLogin.setOnClickListener(v12 -> {
                                    dialogBank.dismiss();
                                    resultDialog.dismiss();
                                });
                                resultDialog.show();
                            }
                        });
                    });
                    dialogBank.show();
                    break;
                case 4:
                    Dialog dialog = new Dialog(SettingActivity.this);
                    DialogRequestConfirmationBinding requestBinding = DialogRequestConfirmationBinding.inflate(getLayoutInflater());
                    Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.setContentView(requestBinding.getRoot());
                    requestBinding.cancel.setOnClickListener(v1 -> dialog.dismiss());
                    requestBinding.title.setText("Change Mobile Number Request");
                    requestBinding.content.setText("Are you sure you want to send the request?");
                    requestBinding.ok.setOnClickListener(v -> {
                        viewModel.getRequestSentLiveData(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), bankRequestId, requestStatusId).observe(this, responseModel -> {
                            if (responseModel == null) {
                                Toast.makeText(this, "Your request is already submitted", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Dialog resultDialog = new Dialog(SettingActivity.this);
                                DialogPasswordCreatedBinding resultBinding = DialogPasswordCreatedBinding.inflate(getLayoutInflater());
                                Objects.requireNonNull(resultDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                resultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                resultDialog.setContentView(resultBinding.getRoot());
                                resultBinding.textView23.setVisibility(View.GONE);
                                resultBinding.textView22.setText("Your request has been sent successfully. Our support team will get in touch with you within the next 24 hours.");
                                resultBinding.gotoLogin.setText("Okay");
                                resultBinding.gotoLogin.setOnClickListener(v12 -> {
                                    dialog.dismiss();
                                    resultDialog.dismiss();
                                });
                                resultDialog.show();
                            }
                        });
                    });
                    dialog.show();
                    break;
                case 5:
                    Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        binding.logout.setOnClickListener(v -> {
            Dialog dialog = new Dialog(SettingActivity.this);
            DialogLogOutBinding logOutBinding = DialogLogOutBinding.inflate(getLayoutInflater());
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setContentView(logOutBinding.getRoot());
            logOutBinding.cancel.setOnClickListener(v1 -> dialog.dismiss());
            logOutBinding.ok.setOnClickListener(v13 -> {
                SPrefClient.deleteAstrologerDetail(getApplicationContext());
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                dialog.dismiss();
                finish();
            });
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