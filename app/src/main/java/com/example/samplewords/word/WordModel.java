package com.example.samplewords.word;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class WordModel {
    @PrimaryKey(autoGenerate = true)
    private final int id;

    @NonNull
    private final String word;

    @Ignore
    public WordModel(@NonNull String word) {
        this(0, word);
    }

    WordModel(int id, @NonNull String word) {
        this.id = id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WordModel wordModel = (WordModel) o;

        if (id != wordModel.id) {
            return false;
        }
        return word.equals(wordModel.word);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + word.hashCode();
        return result;
    }
}
