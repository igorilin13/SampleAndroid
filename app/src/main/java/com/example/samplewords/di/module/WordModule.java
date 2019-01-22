package com.example.samplewords.di.module;

import com.example.samplewords.app.AppDatabase;
import com.example.samplewords.word.WordDao;
import com.example.samplewords.word.WordInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WordModule {
    @Singleton
    @Provides
    static WordDao wordDao(AppDatabase appDatabase) {
        return appDatabase.wordDao();
    }

    @Singleton
    @Provides
    static WordInteractor wordInteractor(WordDao wordDao) {
        return new WordInteractor(wordDao);
    }
}
