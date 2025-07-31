package com.jojotjo.todolist;

import android.util.Log;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import androidx.annotation.NonNull;

public class EditorScreenViewModel extends ViewModel {
    private LiveData<Task> task;
    public EditorScreenViewModel(AppDataBase appDataBase,int id){
        task = appDataBase.taskDao().getTaskById(id);
        Log.i(" Editor View Model"," Loading a task");

    }

    public LiveData<Task> getTask() {
        return task;
    }

}
