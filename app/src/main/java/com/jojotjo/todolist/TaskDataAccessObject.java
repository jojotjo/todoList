package com.jojotjo.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDataAccessObject {

    @Query("SELECT * FROM task ORDER BY priority")
    LiveData<List<Task>> loadAllTask();

    @Insert
    void insertTask(Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Task task);


    @Delete
    void deleteTask(Task task);


    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<Task> getTaskById(int id);
}
