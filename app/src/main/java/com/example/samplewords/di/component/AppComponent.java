package com.example.samplewords.di.component;

import android.content.Context;

import com.example.samplewords.di.module.AppModule;
import com.example.samplewords.di.module.WordModule;
import com.example.samplewords.ui.main.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = {
        AppModule.class,
        WordModule.class
})
public interface AppComponent {
    void inject(MainActivityViewModel mainActivityViewModel);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Context Context);

        AppComponent build();
    }
}
