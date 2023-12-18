package com.astrocure.astrologer.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.astrocure.astrologer.models.requestModels.LoginRequestModel;
import com.astrocure.astrologer.models.responseModels.AstrologerResponseModel;
import com.google.gson.Gson;

public class SPrefClient {
    public static String USER_CRED = "userCredentials";
    public static String USER_DATA = "userDetails";

    public static void setUserCredential(Context context, LoginRequestModel loginRequestModel) {
        SharedPreferences sp = context.getSharedPreferences(USER_CRED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_CRED, new Gson().toJson(loginRequestModel));
        editor.apply();
//        AppTempData.otpResponseModel = result;
    }

    public static LoginRequestModel getUserCredential(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_CRED, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(USER_CRED, null), LoginRequestModel.class);
    }

    public static void deleteUserCredential(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_CRED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void setAstrologerDetail(Context context, AstrologerResponseModel.Data astrologerResponseModel) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_DATA, new Gson().toJson(astrologerResponseModel));
        editor.apply();
//        AppTempData.otpResponseModel = result;
    }

    public static AstrologerResponseModel.Data getAstrologerDetail(Context context) {
        SharedPreferences sp = context.getSharedPreferences(USER_DATA, MODE_PRIVATE);
        return new Gson().fromJson(sp.getString(USER_DATA, null), AstrologerResponseModel.Data.class);
    }

    public static void deleteAstrologerDetail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
