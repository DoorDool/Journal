package com.example.dorin.journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {

    String title, content, mood, timestamp;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // initialize info from intent
        Intent intent = getIntent();
        position = (int) intent.getSerializableExtra("position");
        title = (String) intent.getSerializableExtra("title");
        content = (String) intent.getSerializableExtra("content");
        mood = (String) intent.getSerializableExtra("mood");
        timestamp = (String) intent.getSerializableExtra("timestamp");

        // Initialize id's from views
        TextView titleView = this.findViewById(R.id.Title);
        TextView date_moodView = this.findViewById(R.id.Date_time_mood);
        TextView contentView = this.findViewById(R.id.Content);
        contentView.setMovementMethod(new ScrollingMovementMethod());

        // Set info from intent in the views
        titleView.setText(title);
        date_moodView.setText(timestamp + " " + mood);
        contentView.setText(content);
    }

    public void Edit(View view) {
        Intent intent = new Intent(DetailActivity.this, EditActivity.class);
        // give all information to next activity
        intent.putExtra("position", position);
        intent.putExtra("Title", title);
        intent.putExtra("Content", content);
        intent.putExtra("Mood", mood);
        intent.putExtra("Timestamp", timestamp);
        startActivity(intent);
    }

    // when backPressed go to mainActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
