package com.example.finalmobileappproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    public int remindersCount = 0;
    public int tasksCount = 0;

    View view;
    TextView nameTextView, emailTextView;

    Button logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences prefs = getContext().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined");
        String email = prefs.getString("email", "No email defined");

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutBtn = view.findViewById(R.id.logout_btn);
        nameTextView = view.findViewById(R.id.name);
        emailTextView = view.findViewById(R.id.email);

        nameTextView.setText(name);
        emailTextView.setText(email);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        
        return view;
    }
}