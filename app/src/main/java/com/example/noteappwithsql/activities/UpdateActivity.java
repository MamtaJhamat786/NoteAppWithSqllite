package com.example.noteappwithsql.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteappwithsql.R;
import com.example.noteappwithsql.sqldatabase.MyDatabaseHelper;

public class UpdateActivity extends AppCompatActivity {
    EditText title, note;
    Button updateButton;
    String note_title, note_info;
    String oldNote;
    String oldTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title = findViewById(R.id.title1);
        note = findViewById(R.id.note1);
        updateButton = findViewById(R.id.buttonUpdate);
        getAndSetIntentData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB= new MyDatabaseHelper(UpdateActivity.this);
               // myDB.updateData(note_title, note_info);
             int noteResult =   myDB.updateNote(oldNote, note.getText()+"");
             int titleResult = myDB.updateTitle(oldTitle, title.getText()+ "");

                if (noteResult == -1 && titleResult == -1){
                    Toast.makeText(getApplicationContext(), " Failed to update", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), " Successfully updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

        void getAndSetIntentData(){
            if (getIntent().hasExtra("title") && getIntent().hasExtra("note")){
                note_title=getIntent().getStringExtra("title");
                note_info= getIntent().getStringExtra("note");
                oldNote = getIntent().getStringExtra("note");
                oldTitle = getIntent().getStringExtra("title");
                // setting intent data
                title.setText(note_title);
                note.setText(note_info);

            }

            else{
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            }

        }
}
