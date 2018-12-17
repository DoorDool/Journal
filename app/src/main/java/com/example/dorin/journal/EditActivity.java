package com.example.dorin.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    String title, content, mood, timestamp;
    ImageButton button_bad, button_sad, button_happy, button_great;
    int position;
    EditText inputTitle, inputContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        // initialize info from intent
        Intent intent = getIntent();
        position = (int) intent.getSerializableExtra("position");
        title = (String) intent.getSerializableExtra("Title");
        content = (String) intent.getSerializableExtra("Content");
        mood = (String) intent.getSerializableExtra("Mood");
        timestamp = (String) intent.getSerializableExtra("Timestamp");
        // set all textViews
        inputTitle = findViewById(R.id.inputTitle);
        inputTitle.setText(title);
        inputContent = findViewById(R.id.inputContent);
        inputContent.setText(content);
    }

    public void Save(View view) {
        // delete old entry
        EntryDatabase.delete(position);
        // add new entry
        title = inputTitle.getText().toString();
        content = inputContent.getText().toString();
        JournalEntry entry = new JournalEntry(title, content, mood);
        EntryDatabase.getInstance(this).insert(entry);
        // give information for DetailActivity
        Intent intent = new Intent(EditActivity.this, DetailActivity.class);
        intent.putExtra("position", 1);
        intent.putExtra("title", title);
        intent.putExtra("mood", mood);
        intent.putExtra("content", content);
        intent.putExtra("timestamp", timestamp);
        startActivity(intent);
    }

    public void MoodEdit(View view) {
        // check which moodButton is clicked on and set variable mood
        ImageButton button = (ImageButton) view;
        switch(button.getId()) {
            case R.id.button_bad:
                mood = "bad";
                break;
            case R.id.button_sad:
                mood = "sad";
                break;
            case R.id.button_happy:
                mood = "happy";
                break;
            case R.id.button_great:
                mood = "great";
                break;
        }
        // make toast to let user know what te mood is
        Toast.makeText(EditActivity.this, "mood is: " + mood, Toast.LENGTH_SHORT).show();
    }

    // when backPressed go to DetailActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditActivity.this, DetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("title", title);
        intent.putExtra("mood", mood);
        intent.putExtra("content", content);
        intent.putExtra("timestamp", timestamp);
        startActivity(intent);
    }


}
