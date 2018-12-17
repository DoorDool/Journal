package com.example.dorin.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    // constructor
    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get and set titleView, timestampView and moodView
        TextView titleView = view.findViewById(R.id.Title);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        titleView.setText(title);
        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        TextView timestampView = view.findViewById(R.id.Date_time);
        timestampView.setText(timestamp);
        TextView moodView = view.findViewById(R.id.Mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        moodView.setText(mood);
        ImageView image = view.findViewById(R.id.moodImage);
        // check what te mood is for the right image
        if (mood!= null) {
            switch (mood) {
                case "bad":
                    image.setImageResource(R.drawable.badsmiley);
                    break;
                case "sad":
                    image.setImageResource(R.drawable.sadsmiley);
                    break;
                case "happy":
                    image.setImageResource(R.drawable.happysmiley);
                    break;
                case "great":
                    image.setImageResource(R.drawable.greatsmiley);
                    break;
            }
        }
    }

}
