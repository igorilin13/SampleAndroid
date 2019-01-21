package com.example.samplewords.di;

import android.app.Application;

import com.example.samplewords.di.component.AppComponent;
import com.example.samplewords.di.component.DaggerAppComponent;

public class Injector {
    private static AppComponent appComponent;

    public static void init(Application app) {
        appComponent = DaggerAppComponent
                .builder()
                .application(app)
                .build();
    }

    public static AppComponent appComponent() {
        return appComponent;
    }
}
