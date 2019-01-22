package com.example.samplewords.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samplewords.R;
import com.example.samplewords.ui.base.BaseActivity;
import com.example.samplewords.ui.util.UiUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_words)
    RecyclerView wordsRecycler;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        WordAdapter wordAdapter = new WordAdapter();
        UiUtils.initLinearRecycler(this, wordsRecycler, wordAdapter, true);

        viewModel.getWordsData().observe(this, wordAdapter::setWordModels);
        viewModel.getAddWordSuccessEvent().observe(this, this::showAddWordSuccess);
    }

    private void showAddWordSuccess(String addedWord) {
        String text = getString(R.string.add_word_success, addedWord);
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.fab_add_word)
    public void showAddNewWord() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_new_word, null);

        new AlertDialog.Builder(this)
                .setTitle(R.string.title_add_word)
                .setView(dialogView)
                .setNegativeButton(R.string.btn_negative_add_word, null)
                .setPositiveButton(R.string.btn_positive_add_word, (dialog, which) -> {
                    EditText newWordView = dialogView.findViewById(R.id.et_new_word);
                    viewModel.addWord(newWordView.getText().toString());
                })
                .show();
    }
}
