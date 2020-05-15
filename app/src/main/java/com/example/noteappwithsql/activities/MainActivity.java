package com.example.noteappwithsql.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteappwithsql.R;
import com.example.noteappwithsql.recyclerviewAdapter.CustomAdapter;
import com.example.noteappwithsql.sqldatabase.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    MyDatabaseHelper myDB;
    ArrayList<String> title, note;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new MyDatabaseHelper(MainActivity.this);
        title=new ArrayList<>();
        note= new ArrayList<>();
        storeDatainArrays();
        recyclerView= findViewById(R.id.recyclerView2);

        addButton= findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                
            }
        });
        customAdapter= new CustomAdapter(MainActivity.this, title, note);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    void storeDatainArrays(){
        Cursor cursor=myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                title.add(cursor.getString(0));
                note.add(cursor.getString(1));

            }
        }
    }

}
