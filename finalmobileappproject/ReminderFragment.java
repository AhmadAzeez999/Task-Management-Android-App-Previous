package com.example.finalmobileappproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReminderFragment extends Fragment {

    FloatingActionButton addReminderBtn;
    RecyclerView recyclerview;
    ArrayList<Model> theDataHolder = new ArrayList<Model>();                                               //Array list to add reminders and display in recyclerview
    MyReminderAdapter adapter;

    View mainView;

    ProfileFragment profileFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_reminder, container, false);

        recyclerview = mainView.findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        addReminderBtn = mainView.findViewById(R.id.create_reminder); //Floating action button to change activity
        addReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ReminderActivity.class);
                startActivity(intent);
            }
        });

        //Cursor To Load data From the database
        Cursor cursor = new DBManager(mainView.getContext()).readAllReminders();
        while (cursor.moveToNext()) {
            Model model = new Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            theDataHolder.add(model);
        }

        adapter = new MyReminderAdapter(theDataHolder);
        recyclerview.setAdapter(adapter);

        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        theDataHolder.clear();
    }
}