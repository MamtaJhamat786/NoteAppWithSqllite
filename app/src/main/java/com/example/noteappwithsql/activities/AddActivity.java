package com.example.noteappwithsql.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteappwithsql.R;
import com.example.noteappwithsql.sqldatabase.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {
    EditText title;
    EditText note;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title= findViewById(R.id.title);
        note=  findViewById(R.id.note);
        buttonAdd= findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB= new MyDatabaseHelper(AddActivity.this);
                myDB.addNote(title.getText().toString().trim(), note.getText().toString().trim());
            }
        });
    }
}
