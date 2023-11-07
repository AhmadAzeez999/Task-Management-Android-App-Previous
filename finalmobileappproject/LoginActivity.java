package com.example.finalmobileappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView createAccountLink;
    EditText userName, password;
    Button loginBtn;

    String correct_username = "user";
    String correct_password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String usernameInfo = prefs.getString("username", "No name defined");
        String passwordInfo = prefs.getString("password", "No email defined");

        correct_username = usernameInfo.trim();
        correct_password = passwordInfo;

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        createAccountLink = findViewById(R.id.signUpLink);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(userName.getText().toString()))
                    userName.setError("Please enter username");
                else if (TextUtils.isEmpty(password.getText().toString()))
                    password.setError("Please enter password");
                else if (!userName.getText().toString().trim().equalsIgnoreCase(correct_username))
                    userName.setError("Invalid username");
                else if (userName.getText().toString().trim().equalsIgnoreCase(correct_username))
                {
                    if(password.getText().toString().equals(correct_password))
                    {
                        Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else
                    {
                        password.setError("Invalid password");
                    }
                }
                else
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();

            }
        });

        createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountPageActivity.class);

                startActivity(intent);
            }
        });
    }
}