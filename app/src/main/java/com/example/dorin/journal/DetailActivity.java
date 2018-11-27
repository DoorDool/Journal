package com.example.dorin.journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // initialize info from intent
        Intent intent = getIntent();
        String title = (String) intent.getSerializableExtra("title");
        String content = (String) intent.getSerializableExtra("content");
        String mood = (String) intent.getSerializableExtra("mood");
        String timestamp = (String) intent.getSerializableExtra("timestamp");

        // Initialize id's from views
        TextView titleView = this.findViewById(R.id.Title);
        TextView date_moodView = this.findViewById(R.id.Date_time_mood);
        TextView contentView = this.findViewById(R.id.Content);

        // Set info from intent in the views
        titleView.setText(title);
        date_moodView.setText(timestamp);
        contentView.setText(content);

    }

    @Override
    public void onBackPressed() {
        Log.i("string", "button back pressed");
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
