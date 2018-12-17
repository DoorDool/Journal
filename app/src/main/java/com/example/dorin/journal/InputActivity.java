package com.example.dorin.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    String mood;
    ImageButton button_bad, button_sad, button_happy, button_great;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
    }

    public void Submit (View view) {

        // initialize EditText
        EditText titleEditText = findViewById(R.id.inputTitle);
        EditText contentEditText = findViewById(R.id.inputContent);

        // get string from input
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();

        // make a new entry
        JournalEntry entry = new JournalEntry(title, content, mood);
        // insert this entry in database
        EntryDatabase.getInstance(this).insert(entry);

        // go to main_activity_screen with entry
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        intent.putExtra("entry", entry);
        startActivity(intent);
    }

    public void MoodClick(View view) {
        // check which button is clicked on and set mood string
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
        // toast for let user know what the mood is
        Toast.makeText(InputActivity.this, "mood is: " + mood, Toast.LENGTH_SHORT).show();
    }

    // when backPressed go to mainActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
