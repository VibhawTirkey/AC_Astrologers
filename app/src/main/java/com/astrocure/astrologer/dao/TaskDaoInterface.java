package com.astrocure.astrologer.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.astrocure.astrologer.models.responseModels.BindResponseModel;

import java.util.List;

@Dao
public interface TaskDaoInterface {
    @Insert
    void insertLanguage(List<BindResponseModel.Datum> languageList);

}
