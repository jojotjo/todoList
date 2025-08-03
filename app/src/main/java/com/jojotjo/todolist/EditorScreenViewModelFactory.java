package com.jojotjo.todolist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditorScreenViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDataBase appDataBase;
    private final int id;

    public EditorScreenViewModelFactory(AppDataBase db,int taskId){
        appDataBase=db;
        id=taskId;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new EditorScreenViewModel(appDataBase,id);
    }
}
