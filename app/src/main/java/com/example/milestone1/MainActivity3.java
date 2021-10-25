package com.example.milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText editText = (EditText) findViewById(R.id.notes);
        Intent intent = getIntent();
        this.noteid = intent.getIntExtra("noteid", noteid);


        if(noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveFunction(View view) {
        EditText editText = (EditText) findViewById(R.id.notes);
        String content = editText.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper helper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.milestone1", Context.MODE_PRIVATE);
        String usernameKey = "username";
        String username = sharedPreferences.getString(usernameKey, "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            helper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            helper.updateNotes(title, date, content, username);
        }

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}