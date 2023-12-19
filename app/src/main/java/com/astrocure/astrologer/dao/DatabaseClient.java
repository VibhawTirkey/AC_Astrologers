package com.astrocure.astrologer.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.astrocure.astrologer.models.responseModels.BindResponseModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BindResponseModel.class}, version = 1)
public abstract class DatabaseClient extends RoomDatabase {
    public static final String DB_NAME = "bind_db";

    public abstract TaskDaoInterface daoInterface();

    private static volatile DatabaseClient databaseClient;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DatabaseClient getDatabaseClient(final Context context) {
        if (databaseClient == null) {
            synchronized (DatabaseClient.class) {
                if (databaseClient == null) {
                    databaseClient = Room.databaseBuilder(context.getApplicationContext(), DatabaseClient.class, DB_NAME).build();
                }
            }
        }
        return databaseClient;
    }
}
