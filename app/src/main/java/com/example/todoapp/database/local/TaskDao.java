package com.example.todoapp.database.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todoapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM table_tasks")
    List<Task> getAllTasks();

    @Insert
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);
}
