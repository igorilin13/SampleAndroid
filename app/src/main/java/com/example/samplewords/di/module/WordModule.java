package com.example.samplewords.di.module;

import android.content.Context;

import com.example.samplewords.app.AppDatabase;
import com.example.samplewords.word.AddWordInteractor;
import com.example.samplewords.word.GetWordsInteractor;
import com.example.samplewords.word.WordDao;

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
    static GetWordsInteractor getWordsInteractor(Context context, WordDao wordDao) {
        return new GetWordsInteractor(context, wordDao);
    }

    @Singleton
    @Provides
    static AddWordInteractor addWordInteractor(WordDao wordDao) {
        return new AddWordInteractor(wordDao);
    }
}
