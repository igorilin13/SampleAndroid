package com.example.samplewords.ui.util;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UiUtils {

    public static void initLinearRecycler(Context context, RecyclerView recyclerView,
                                          RecyclerView.Adapter adapter, boolean addDivider) {
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        if (addDivider) {
            int orientation = layoutManager.getOrientation();
            recyclerView.addItemDecoration(new DividerItemDecoration(context, orientation));
        }
    }
}
