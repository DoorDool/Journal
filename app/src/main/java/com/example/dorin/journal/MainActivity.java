package com.example.dorin.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public EntryDatabase db;
    public EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize list
        ListView list = findViewById(R.id.listView);
        // set click and longclick listener on list of entries
        list.setOnItemClickListener(new listClickListener());
        list.setOnItemLongClickListener(new listLongClickListener());

        // make EntryDatabase
        db = EntryDatabase.getInstance(getApplicationContext());
        // select all entries in entries database
        Cursor cursor = EntryDatabase.selectAll(db);
        // make list for screen
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        list.setAdapter(adapter);
    }

    // method for make a new entry
    public void CreateNew (View view) {
        Intent input = new Intent (this, InputActivity.class);
        startActivity(input);
    }

    // method for click on entry in list
    private class listClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // get the right entry in database and get all information of it
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            // go to detailActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            // save variables in intent
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("mood", mood);
            intent.putExtra("timestamp", timestamp);
            intent.putExtra("position", id);
            startActivity(intent);
        }
    }

    // method for long click on entry in list
    private class listLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            // get that entry in database and delete it
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            final long id = cursor.getLong(cursor.getColumnIndex("_id"));
            EntryDatabase.delete(id);
            updateData();
            return false;
        }
    }

    private void updateData() {
        Cursor newCursor = EntryDatabase.selectAll(db);
        adapter.swapCursor(newCursor);
    }

    protected void onResume() {
        super.onResume();
        updateData();
    }

    // when backPressed exit the app
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

}
