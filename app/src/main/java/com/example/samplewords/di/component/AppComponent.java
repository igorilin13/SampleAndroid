package com.example.samplewords.di.component;

import android.content.Context;

import com.example.samplewords.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = AppModule.class)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Context Context);

        AppComponent build();
    }
}
