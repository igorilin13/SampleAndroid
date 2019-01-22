package com.example.samplewords.word;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface WordDao {
    @Insert
    void insert(WordModel wordModel);
}
