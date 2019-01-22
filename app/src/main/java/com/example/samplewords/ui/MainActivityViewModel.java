package com.example.samplewords.ui;

import android.app.Application;

import com.example.samplewords.di.Injector;
import com.example.samplewords.ui.util.SingleLiveEvent;
import com.example.samplewords.word.WordInteractor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends AndroidViewModel {
    @Inject
    WordInteractor wordInteractor;

    private final SingleLiveEvent<String> addWordSuccessEvent;

    private final CompositeDisposable disposables;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        Injector.appComponent().inject(this);

        addWordSuccessEvent = new SingleLiveEvent<>();

        disposables = new CompositeDisposable();
    }

    void addWord(String newWord) {
        disposables.add(
                wordInteractor
                        .add(newWord)
                        .subscribe(() -> addWordSuccessEvent.postValue(newWord), e -> {
                        })
        );
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
