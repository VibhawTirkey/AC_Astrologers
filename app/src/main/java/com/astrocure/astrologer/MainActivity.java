package com.astrocure.astrologer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;
import com.astrocure.astrologer.ui.DashboardActivity;
import com.astrocure.astrologer.ui.LoginActivity;
import com.astrocure.astrologer.utils.SPrefClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);


        if (SPrefClient.getAstrologerDetail(getApplicationContext()) != null) {
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}