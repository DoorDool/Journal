package com.example.dorin.journal;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    //constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE entries (_id INTEGER PRIMARY KEY, title TEXT, content TEXT, timestamp DATETIME DEFAULT( datetime('now', 'localtime')));";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries;");
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance != null) {
            return EntryDatabase.instance;
        }
        else {
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
            return EntryDatabase.instance;
        }
    }
}
