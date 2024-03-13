package com.astrocure.astrologer.ui;

import static com.astrocure.astrologer.utils.AppConstants.aadhar_card;
import static com.astrocure.astrologer.utils.AppConstants.agreement;
import static com.astrocure.astrologer.utils.AppConstants.bank_details;
import static com.astrocure.astrologer.utils.AppConstants.form_16A;
import static com.astrocure.astrologer.utils.AppConstants.kyc_document;
import static com.astrocure.astrologer.utils.AppConstants.pan_card;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.astrologer.databinding.ActivityDocumentViewBinding;
import com.astrocure.astrologer.utils.SPrefClient;
import com.bumptech.glide.Glide;

public class DocumentViewActivity extends AppCompatActivity {
    ActivityDocumentViewBinding binding;
    String viewType, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityDocumentViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        viewType = getIntent().getStringExtra("viewType");

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());


        switch (viewType) {
            case kyc_document:
                binding.toolbar.setTitle(kyc_document);
                break;
            case agreement:
                binding.toolbar.setTitle(agreement);
                break;
            case aadhar_card:
                binding.toolbar.setTitle(aadhar_card);
                Glide.with(getApplicationContext()).load(SPrefClient.getAstrologerDetail(getApplicationContext()).getDocuments().getAadharCard()).into(binding.docImg);
                break;
            case bank_details:
                Glide.with(getApplicationContext()).load(SPrefClient.getAstrologerDetail(getApplicationContext()).getDocuments().getPassbook()).into(binding.docImg);
                binding.toolbar.setTitle(bank_details);
                break;
            case pan_card:
                binding.toolbar.setTitle(pan_card);
                Glide.with(getApplicationContext()).load(SPrefClient.getAstrologerDetail(getApplicationContext()).getDocuments().getAadharCard()).into(binding.docImg);
                break;
            case form_16A:
                Glide.with(getApplicationContext()).load(SPrefClient.getAstrologerDetail(getApplicationContext()).getDocuments().getForm16()).into(binding.docImg);
                binding.toolbar.setTitle(form_16A);
                break;
            default:
                finish();
        }

    }
}