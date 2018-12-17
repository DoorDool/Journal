package com.example.dorin.journal;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    //constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // method for making a database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL for making table entries
        String sqlCreate = "CREATE TABLE entries (_id INTEGER PRIMARY KEY, title TEXT, content TEXT, mood TEXT, timestamp DATETIME DEFAULT( datetime('now', 'localtime')));";
        db.execSQL(sqlCreate);
    }

    // method for upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL for drop table
        db.execSQL("DROP TABLE IF EXISTS entries;");
        onCreate(db);
    }

    public static EntryDatabase getInstance(Context context) {
        // if database exist return instance
        if (EntryDatabase.instance != null) {
            return EntryDatabase.instance;
        }
        // else make database
        else {
            EntryDatabase.instance = new EntryDatabase(context, "entries", null, 1);
            return EntryDatabase.instance;
        }
    }

    // method for select all entries in database
    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase db = instance.getWritableDatabase();
        // SQL select all entries from entries database
        return db.rawQuery("SELECT * FROM entries", null);
    }

    // method for insert an entry in the database
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        // calling getters from entry class
        values.put("title", entry.getTitle());
        values.put("mood", entry.getMood());
        values.put("content", entry.getContent());
        // insert entry in database
        db.insert("entries", null, values);
    }

    // method for deleting an entry in the database
    static void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete("entries", "_id = " + id, null);
    }
}
