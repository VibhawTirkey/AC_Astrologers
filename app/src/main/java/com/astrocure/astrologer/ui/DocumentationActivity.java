package com.astrocure.astrologer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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
        DocumentAdapter adapter = new DocumentAdapter(getApplicationContext(),list);
        binding.documentList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        binding.documentList.setAdapter(adapter);
    }

    private void getDocumentData() {
        list = new ArrayList<>();
        list.add(new DocumentModel(R.drawable.ic_kyc_documentation,"Kyc Document"));
        list.add(new DocumentModel(R.drawable.ic_agreement_documentation,"Agreement"));
        list.add(new DocumentModel(R.drawable.ic_aadhar_card_documentation,"Aadhar Card"));
        list.add(new DocumentModel(R.drawable.ic_bank_details_documentation,"Bank Details"));
        list.add(new DocumentModel(R.drawable.ic_pan_card_documentation,"Pan Card"));
        list.add(new DocumentModel(R.drawable.ic_form_16a_documentation,"Form 16A Download"));
    }
}