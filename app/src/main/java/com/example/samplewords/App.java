package com.example.samplewords;

import android.app.Application;

import com.example.samplewords.di.Injector;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Injector.init(this);
    }
}
