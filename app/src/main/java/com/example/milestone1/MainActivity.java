package com.example.milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String usernameKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.milestone1", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            String user = sharedPreferences.getString(usernameKey, "");
            goToActivity2(user);
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    public void loginFunction(View view) {
        //get user and pass
        EditText enteredUser = (EditText) findViewById(R.id.username);
        EditText enteredPass = (EditText) findViewById(R.id.password);

        //convert to string
        String user = enteredUser.getText().toString();
        String pass = enteredPass.getText().toString();

        //edit sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.milestone1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", user).apply();
        String username = sharedPreferences.getString("username", "");

        goToActivity2(username);
    }

    public void goToActivity2(String username) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", username);
        startActivity(intent);
    }
}