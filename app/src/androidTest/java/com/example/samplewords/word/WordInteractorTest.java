package com.example.samplewords.word;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

@RunWith(AndroidJUnit4ClassRunner.class)
public class WordInteractorTest {
    @Mock
    WordDao wordDao;

    private WordInteractor wordInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        wordInteractor = new WordInteractor(wordDao);
    }

    @Test
    public void testEmptyWord() throws InterruptedException {
        verifyEmptyWord(null);
        verifyEmptyWord("");
        verifyEmptyWord("   \t\n    ");
    }

    private void verifyEmptyWord(String word) throws InterruptedException {
        wordInteractor
                .add(word)
                .test()
                .await()
                .assertError(IllegalArgumentException.class);

        Mockito.verifyNoMoreInteractions(wordDao);
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
        wordInteractor
                .add(input)
                .test()
                .await()
                .assertComplete();

        Mockito.verify(wordDao, Mockito.only()).insert(new WordModel(expected));
    }
}