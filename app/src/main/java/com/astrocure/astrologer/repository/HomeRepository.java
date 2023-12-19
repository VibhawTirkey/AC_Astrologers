package com.astrocure.astrologer.repository;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.astrocure.astrologer.MainApplication;
import com.astrocure.astrologer.dao.DatabaseClient;
import com.astrocure.astrologer.dao.TaskDaoInterface;
import com.astrocure.astrologer.models.responseModels.BindResponseModel;

public class HomeRepository {
    private final String DB_NAME = "bind_data";
    DatabaseClient databaseClient;
    TaskDaoInterface taskDaoInterface;

    public HomeRepository(Application application) {
        databaseClient = DatabaseClient.getDatabaseClient(application);
        taskDaoInterface = databaseClient.daoInterface();
    }
}
