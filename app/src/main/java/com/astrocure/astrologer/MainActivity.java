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

        getFirebaseToken();

        subscribeTopic();
    }

    private void subscribeTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("testing").addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Log.i("TAG", "onComplete: Subscribed");
            }else {
                Log.i("TAG", "onComplete: Subscribe Failed");
            }
        });
    }

    private void getFirebaseToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    RetrofitClient.getAppClient().updateFirebaseToken(new UpdateTokenRequestModel(
                            SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), task.getResult()
                    )).enqueue(new Callback<UpdateTokenResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Response<UpdateTokenResponseModel> response) {
                            try {
                                if (response.isSuccessful()) {
                                    Log.i("TAG", "onResponse: Token updated Successfully "+response.body().getData().getFirebaseToken());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Throwable t) {

                        }
                    });
                } else {
                    Log.i("TAG", "onComplete: Unsuccessful");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}