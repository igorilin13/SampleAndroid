package com.example.samplewords.word;

import android.text.TextUtils;

import com.example.samplewords.dbg.Dbg;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class AddWordInteractor {
    private static final String TAG = GetWordsInteractor.class.getSimpleName();

    private final WordDao wordDao;

    public AddWordInteractor(WordDao wordDao) {
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
}
