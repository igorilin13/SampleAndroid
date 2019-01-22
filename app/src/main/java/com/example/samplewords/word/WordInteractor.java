package com.example.samplewords.word;

import android.content.Context;
import android.text.TextUtils;

import com.example.samplewords.R;
import com.example.samplewords.dbg.Dbg;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class WordInteractor {
    private static final String TAG = WordInteractor.class.getSimpleName();

    private final Context context;
    private final WordDao wordDao;

    public WordInteractor(Context context, WordDao wordDao) {
        this.context = context;
        this.wordDao = wordDao;
    }

    public Completable add(String newWord) {
        return Completable
                .fromAction(() -> insert(newWord))
                .subscribeOn(Schedulers.io());
    }

    private void insert(String newWord) {
        if (newWord != null) {
            newWord = newWord.replaceAll("\\s","");
        }

        if (TextUtils.isEmpty(newWord)) {
            throw new IllegalArgumentException("Empty words are not allowed");
        }

        if (Dbg.DEBUG) {
            Dbg.d(TAG, "Inserting new word: %s", newWord);
        }
        wordDao.insert(new WordModel(newWord));
    }

    public Flowable<List<WordModel>> getWords() {
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
