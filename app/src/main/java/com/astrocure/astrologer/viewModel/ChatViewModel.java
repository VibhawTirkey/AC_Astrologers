package com.astrocure.astrologer.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.astrocure.astrologer.MainApplication;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatViewModel extends AndroidViewModel {
    private Socket socket;

    public ChatViewModel(@NonNull Application application) {
        super(application);
    }

    public void openSocket() {
        MainApplication application = getApplication();
        socket = application.getSocket();
        socket.connect();
        socket.on("connect", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("TAG", "connect: "+args);
            }
        });
    }
}
