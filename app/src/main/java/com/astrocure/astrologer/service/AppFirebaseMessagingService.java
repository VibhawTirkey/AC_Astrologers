package com.astrocure.astrologer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.astrocure.astrologer.R;
import com.astrocure.astrologer.models.requestModels.UpdateTokenRequestModel;
import com.astrocure.astrologer.models.responseModels.UpdateTokenResponseModel;
import com.astrocure.astrologer.network.RetrofitClient;
import com.astrocure.astrologer.utils.SPrefClient;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppFirebaseMessagingService extends FirebaseMessagingService {
    public static String CHANNEL_ID = "111";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        createNotification(Objects.requireNonNull(message.getNotification()).getTitle(), message.getNotification().getBody());
    }

    private void createNotification(String title, String body) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH);

//        PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),1,intent,0);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentText(body)
                    .setContentTitle(title)
                    //                .setContentIntent(pendingIntent)
//                    .addAction(android.R.drawable.sym_action_chat, "Title", pendingIntent)
                    .setChannelId(CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_app_logo)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(1, notification);
        }


    }

    private void sendRegistrationToServer(String token) {
        RetrofitClient.getAppClient().updateFirebaseToken(new UpdateTokenRequestModel(SPrefClient.getAstrologerDetail(getApplicationContext()).getId(), token)).enqueue(new Callback<UpdateTokenResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Response<UpdateTokenResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.i("TAG", "onResponse: Firebase token updated successfully");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UpdateTokenResponseModel> call, @NonNull Throwable t) {

            }
        });
    }
}