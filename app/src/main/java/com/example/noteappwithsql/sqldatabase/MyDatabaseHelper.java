package com.example.noteappwithsql.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteApplication.db";
    private static final int DATABASE_VERSION=1;
    Context context;

    private static final String TABLE_NAME = "my_library";
    private static final String TITLE= "note_title";
    private static final String NOTE ="note_info";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + TITLE + " TEXT, " +
                        NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNote(String title, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(NOTE, note);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if (db != null){
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    public int updateTitle(String oldTitle, String newTitle){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, newTitle);
       // contentValues.put(NOTE, note);
        String[] arg = {oldTitle};
        int result= db.update(TABLE_NAME, contentValues, TITLE+" =?", arg);
        return result;
    }

    public int updateNote(String oldNote, String newNote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE, newNote);
        String[] arg = {oldNote};
        int result = db.update(TABLE_NAME, contentValues,NOTE+" =?", arg);
        return  result;
    }
}
