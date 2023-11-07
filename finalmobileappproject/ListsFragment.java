package com.example.finalmobileappproject;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ListsFragment extends Fragment {

    View todolistView;
    RecyclerView recyclerView;
    ImageView imageView;

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle bundleRecyclerViewState;

    int count = 0;

    List<String> items = new LinkedList<>();
    TODOListAdapter adapter =  new TODOListAdapter(items);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        todolistView = inflater.inflate(R.layout.fragment_todolist, container, false);

        recyclerView = todolistView.findViewById(R.id.recyclerViewTODOList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        todolistView.findViewById(R.id.create_todoList).setOnClickListener(view -> {
            items.add("");
            count++;
            adapter.notifyItemInserted(items.size()-1);
        });

        // restore RecyclerView state
        if (bundleRecyclerViewState != null) {
            Parcelable listState = bundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }

        return todolistView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}