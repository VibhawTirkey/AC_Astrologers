package com.astrocure.astrologer.ui;

import static com.astrocure.astrologer.utils.AppConstants.aadhar_card;
import static com.astrocure.astrologer.utils.AppConstants.agreement;
import static com.astrocure.astrologer.utils.AppConstants.bank_details;
import static com.astrocure.astrologer.utils.AppConstants.form_16A;
import static com.astrocure.astrologer.utils.AppConstants.kyc_document;
import static com.astrocure.astrologer.utils.AppConstants.pan_card;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.adapter.DocumentAdapter;
import com.astrocure.astrologer.databinding.ActivityDocumentationBinding;
import com.astrocure.astrologer.models.DocumentModel;

import java.util.ArrayList;
import java.util.List;

public class DocumentationActivity extends AppCompatActivity {
    ActivityDocumentationBinding binding;
    List<DocumentModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocumentationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getDocumentData();
        DocumentAdapter adapter = new DocumentAdapter(getApplicationContext(), list);
        binding.documentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        binding.documentList.setAdapter(adapter);
        adapter.setOnItemActionListener((type, position) -> {
            Intent intent = new Intent(getApplicationContext(), DocumentViewActivity.class);
            switch (position) {
                case 0:
                    intent.putExtra("viewType", kyc_document);
                    startActivity(intent);
                    break;
                case 1:
                    intent.putExtra("viewType", agreement);
                    startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("viewType", aadhar_card);
                    startActivity(intent);
                    break;
                case 3:
                    intent.putExtra("viewType", bank_details);
                    startActivity(intent);
                    break;
                case 4:
                    intent.putExtra("viewType", pan_card);
                    startActivity(intent);
                    break;
                case 5:
                    intent.putExtra("viewType", form_16A);
                    startActivity(intent);
                    break;
                default:
                    finish();
            }
        });
    }

    private void getDocumentData() {
        list = new ArrayList<>();
        list.add(new DocumentModel(R.drawable.ic_kyc_documentation, getString(R.string.doc_kyc_document)));
        list.add(new DocumentModel(R.drawable.ic_agreement_documentation, getString(R.string.doc_agreement)));
        list.add(new DocumentModel(R.drawable.ic_aadhar_card_documentation, getString(R.string.doc_aadhar_card)));
        list.add(new DocumentModel(R.drawable.ic_bank_details_documentation, getString(R.string.doc_bank_details)));
        list.add(new DocumentModel(R.drawable.ic_pan_card_documentation, getString(R.string.doc_pan_card)));
        list.add(new DocumentModel(R.drawable.ic_form_16a_documentation, getString(R.string.doc_form_16a_download)));
    }
}