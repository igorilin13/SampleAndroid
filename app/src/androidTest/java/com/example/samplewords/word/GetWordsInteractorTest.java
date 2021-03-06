package com.example.samplewords.word;

import android.content.Context;

import com.example.samplewords.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import io.reactivex.Flowable;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4ClassRunner.class)
public class GetWordsInteractorTest {
    @Mock
    WordDao wordDao;

    private GetWordsInteractor getWordsInteractor;
    private List<WordModel> expectedSampleWords;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        expectedSampleWords = createSampleWords(context);
        getWordsInteractor = new GetWordsInteractor(context, wordDao);
    }

    private List<WordModel> createSampleWords(Context context) {
        String[] words = context.getResources().getStringArray(R.array.sample_words);

        List<WordModel> models = new ArrayList<>();
        for (String word : words) {
            models.add(new WordModel(word));
        }

        return models;
    }

    @Test
    public void testGetWordsEmpty() {
        when(wordDao.getWords()).thenReturn(Flowable.just(Collections.emptyList()));

        verifyGetWords(expectedSampleWords);
    }

    @Test
    public void testGetWordsInserted() {
        List<WordModel> insertedWordModels = new ArrayList<>();
        insertedWordModels.add(new WordModel("Apple"));
        insertedWordModels.add(new WordModel("Orange"));
        insertedWordModels.add(new WordModel("Banana"));

        when(wordDao.getWords()).thenReturn(Flowable.just(insertedWordModels));

        verifyGetWords(insertedWordModels);
    }

    @Test
    public void testGetWordsDbException() {
        when(wordDao.getWords()).thenReturn(Flowable.error(new RuntimeException()));

        verifyGetWords(expectedSampleWords);
    }

    private void verifyGetWords(List<WordModel> expected) {
        getWordsInteractor
                .get()
                .test()
                .assertValue(expected);

        Mockito.verify(wordDao, only()).getWords();
    }
}