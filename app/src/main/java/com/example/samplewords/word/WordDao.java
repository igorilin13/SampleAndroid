package com.example.samplewords.word;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface WordDao {
    @Insert
    void insert(WordModel wordModel);

    @Query("SELECT * FROM WordModel")
    Flowable<List<WordModel>> getWords();
}
