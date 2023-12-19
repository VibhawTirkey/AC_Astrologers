package com.astrocure.astrologer.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.Date;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> greetTextLiveData = new MutableLiveData<>();

    public HomeViewModel() {
        greetingText();
    }

    public void greetingText() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 12 && hour < 17) {
            greetTextLiveData.postValue("Good Afternoon");
        } else if (hour >= 17 && hour < 21) {
            greetTextLiveData.postValue("Good Evening");
        } else if (hour >= 21 /*&& hour < 24*/) {
            greetTextLiveData.postValue("Good Night");
        } else {
            greetTextLiveData.postValue("Good Morning");
        }
    }

    public MutableLiveData<String> getGreetTextLiveData() {
        return greetTextLiveData;
    }
}
