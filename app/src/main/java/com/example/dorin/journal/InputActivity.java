package com.example.dorin.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
    }

    public void submit (View view) {

        // initialize EditText
        EditText titleEditText = findViewById(R.id.inputTitle);
        EditText moodEditText = findViewById(R.id.inputMood);
        EditText contentEditText = findViewById(R.id.inputContent);

        // get string from input
        String title = titleEditText.getText().toString();
        String mood = moodEditText.getText().toString();
        String content = contentEditText.getText().toString();

        // make a new entry
        JournalEntry entry = new JournalEntry(title, content, mood);
        // insert this entry in database
        EntryDatabase.getInstance(this).insert(entry);

        // go to main_activity_screen
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Log.i("string", "button back pressed");
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
