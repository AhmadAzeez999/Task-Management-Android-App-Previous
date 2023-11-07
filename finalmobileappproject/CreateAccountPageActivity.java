package com.example.finalmobileappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountPageActivity extends AppCompatActivity {
    EditText username, password, name, email;
    Button createAccountBtn;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_page);

        name = findViewById(R.id.nameInput);
        username = findViewById(R.id.userNameInput);
        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.newPassword);
        createAccountBtn = findViewById(R.id.create_acc_btn);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String nameInput = name.getText().toString();
                    String emailInput = email.getText().toString();
                    String usernameInput = username.getText().toString();
                    String passwordInput = password.getText().toString();

                    if(nameInput.isEmpty() || emailInput.isEmpty() || usernameInput.isEmpty() || passwordInput.isEmpty())
                    {
                        Toast.makeText(CreateAccountPageActivity.this, "Please fill all empty spaces", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(CreateAccountPageActivity.this, LoginActivity.class);

                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("name", nameInput);
                        editor.putString("email", emailInput);
                        editor.putString("username", usernameInput);
                        editor.putString("password", passwordInput);
                        editor.apply();

                        startActivity(intent);
                    }
                }
        });

    }
}