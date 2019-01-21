package com.example.samplewords.ui;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    void addWord(String newWord) {
        if (!TextUtils.isEmpty(newWord)) {

        }
    }
}
