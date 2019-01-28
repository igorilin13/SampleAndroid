package com.example.samplewords.word;

import android.content.Context;

import com.example.samplewords.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class GetWordsInteractor {
    private final Context context;
    private final WordDao wordDao;

    public GetWordsInteractor(Context context, WordDao wordDao) {
        this.context = context;
        this.wordDao = wordDao;
    }

    public Flowable<List<WordModel>> get() {
        return wordDao
                .getWords()
                .map(words -> words.isEmpty() ? createSampleWords() : words)
                .onErrorReturnItem(createSampleWords());
    }

    private List<WordModel> createSampleWords() {
        String[] words = context.getResources().getStringArray(R.array.sample_words);

        List<WordModel> models = new ArrayList<>();
        for (String word : words) {
            models.add(new WordModel(word));
        }

        return models;
    }
}
