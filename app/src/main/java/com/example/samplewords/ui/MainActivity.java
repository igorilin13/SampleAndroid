package com.example.samplewords.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.samplewords.R;
import com.example.samplewords.ui.base.BaseActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
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
