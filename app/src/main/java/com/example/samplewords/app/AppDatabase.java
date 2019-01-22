package com.example.samplewords.app;

import com.example.samplewords.word.WordDao;
import com.example.samplewords.word.WordModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = WordModel.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "appdb";

    public abstract WordDao wordDao();
}
