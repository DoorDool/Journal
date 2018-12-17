package com.example.dorin.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    private Integer id;
    private String title, content, mood, timestamp;

    // constructor
    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {this.id = id;}
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {this.title = title;}
    public String getContent() {
        return content;
    }
    public void setContent(String content) {this.content = content;}
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {this.mood = mood;}
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
}
