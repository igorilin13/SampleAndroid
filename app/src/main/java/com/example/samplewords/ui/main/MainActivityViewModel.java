package com.example.samplewords.ui.main;

import android.app.Application;

import com.example.samplewords.dbg.Dbg;
import com.example.samplewords.di.Injector;
import com.example.samplewords.ui.util.SingleLiveEvent;
import com.example.samplewords.word.AddWordInteractor;
import com.example.samplewords.word.GetWordsInteractor;
import com.example.samplewords.word.WordModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends AndroidViewModel {
    @Inject
    AddWordInteractor addWordInteractor;

    @Inject
    GetWordsInteractor getWordsInteractor;

    private final LiveData<List<WordModel>> wordsData;
    private final SingleLiveEvent<String> addWordSuccessEvent;

    private final CompositeDisposable disposables;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        Injector.appComponent().inject(this);

        wordsData = LiveDataReactiveStreams.fromPublisher(getWordsInteractor.get());
        addWordSuccessEvent = new SingleLiveEvent<>();

        disposables = new CompositeDisposable();
    }

    void addWord(String newWord) {
        disposables.add(
                addWordInteractor
                        .add(newWord)
                        .subscribe(() -> addWordSuccessEvent.postValue(newWord), e -> {
                            if (Dbg.DEBUG) {
                                Dbg.exception(e);
                            }
                        })
        );
    }

    LiveData<List<WordModel>> getWordsData() {
        return wordsData;
    }

    SingleLiveEvent<String> getAddWordSuccessEvent() {
        return addWordSuccessEvent;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
