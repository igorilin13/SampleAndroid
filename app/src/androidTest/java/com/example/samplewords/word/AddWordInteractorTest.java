package com.example.samplewords.word;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddWordInteractorTest {
    @Mock
    WordDao wordDao;

    private AddWordInteractor addWordInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        addWordInteractor = new AddWordInteractor(wordDao);
    }

    @Test
    public void testEmptyWord() throws InterruptedException {
        verifyEmptyWord(null);
        verifyEmptyWord("");
        verifyEmptyWord("   \t\n    ");
    }

    private void verifyEmptyWord(String word) throws InterruptedException {
        addWordInteractor
                .add(word)
                .test()
                .await()
                .assertError(IllegalArgumentException.class);

        verifyNoMoreInteractions(wordDao);
    }

    @Test
    public void testAddSingleWord() throws InterruptedException {
        verifyAddWordSuccess("Hello", "Hello");
    }

    @Test
    public void testAddSentence() throws InterruptedException {
        verifyAddWordSuccess("Thisisasentence", " This is a sentence ");
    }

    private void verifyAddWordSuccess(String expected, String input) throws InterruptedException {
        addWordInteractor
                .add(input)
                .test()
                .await()
                .assertComplete();

        verify(wordDao, only()).insert(new WordModel(expected));
    }
}
