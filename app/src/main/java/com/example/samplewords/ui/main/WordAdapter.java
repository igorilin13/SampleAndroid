package com.example.samplewords.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samplewords.R;
import com.example.samplewords.word.WordModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private final List<WordModel> wordModels = new ArrayList<>();

    void setWordsModels(List<WordModel> wordsModels) {
        this.wordModels.clear();
        this.wordModels.addAll(wordsModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_word, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(wordModels.get(position));
    }

    @Override
    public int getItemCount() {
        return wordModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_word)
        TextView wordView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(WordModel wordModel) {
            wordView.setText(wordModel.getWord());
        }
    }
}
