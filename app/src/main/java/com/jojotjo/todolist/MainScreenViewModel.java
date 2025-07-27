package com.jojotjo.todolist;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainScreenViewModel extends AndroidViewModel {
    private final LiveData<List<Task>> taskList;

    public MainScreenViewModel(@NonNull Application application) {
        super(application);

        AppDataBase appDataBase = AppDataBase.getsInstance(this.getApplication());
        Log.i("View Model", "Retrieving data from database");
        taskList = appDataBase.taskDao().loadAllTask();
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }
}
