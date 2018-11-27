package com.example.dorin.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleView = view.findViewById(R.id.Title);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        titleView.setText(title);

        TextView moodView = view.findViewById(R.id.Mood);
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        moodView.setText(mood);

        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        TextView timestampView = view.findViewById(R.id.Date_time);
        timestampView.setText(timestamp);

    }

}
