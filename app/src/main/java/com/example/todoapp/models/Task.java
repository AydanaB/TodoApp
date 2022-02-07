package com.example.todoapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;

    public Task(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
