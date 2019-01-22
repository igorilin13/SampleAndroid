package com.example.samplewords.di.module;

import android.content.Context;

import com.example.samplewords.app.AppDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    static AppDatabase appDatabase(Context context) {
        return Room
                .databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
