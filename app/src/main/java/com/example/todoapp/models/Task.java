package com.example.todoapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
