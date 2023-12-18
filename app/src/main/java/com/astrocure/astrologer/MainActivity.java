package com.astrocure.astrologer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.astrocure.astrologer.ui.DashboardActivity;
import com.astrocure.astrologer.ui.LoginActivity;
import com.astrocure.astrologer.utils.SPrefClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SPrefClient.getAstrologerDetail(getApplicationContext())!=null){
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }
}