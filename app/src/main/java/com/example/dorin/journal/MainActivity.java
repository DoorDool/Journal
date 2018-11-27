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
        ListView list = findViewById(R.id.listView);
        list.setOnItemClickListener(new listClickListener());
        list.setOnItemLongClickListener(new listLongClickListener());

        // make EntryDatabase
        db = EntryDatabase.getInstance(getApplicationContext());

        // example
        //String title = "First";
        //String content = "I had a lovely day";
        //String mood = "great";
        //JournalEntry example = new JournalEntry(title, content, mood);
        //Log.i("string", "this is an example: " + example);
        //EntryDatabase.getInstance(this).insert(example);

        // select all entries in entries database
        Cursor cursor = EntryDatabase.selectAll(db);
        // make list for screen
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        list.setAdapter(adapter);
    }

    // method for make a new entry
    public void create_new (View view) {

        Intent input = new Intent (this, InputActivity.class);
        startActivity(input);
    }

    private class listClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // cursor over items in entries
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            // save variables in intent
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("mood", mood);
            intent.putExtra("timestamp", timestamp);
            startActivity(intent);
        }
    }

    private class listLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
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

    @Override
    public void onBackPressed() {
        Log.i("string", "button back pressed");
        finishAffinity();
        System.exit(0);
    }

}
