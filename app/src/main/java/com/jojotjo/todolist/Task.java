package com.jojotjo.todolist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
//import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.Date;

@Entity(tableName="task")
public class Task {
    @PrimaryKey(autoGenerate=true)
    private int id;
    private String description;
    private int priority;
    @ColumnInfo(name="update_at")
    private Date updateAt;

    public Task(String description,int priority,Date updateAt){
        this.description=description;
        this.priority=priority;
        this.updateAt=updateAt;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public int getPriority(){
        return priority;
    }

    public Date getUpdateAt(){
        return updateAt;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setPriority(int priority){
        this.priority=priority;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


}
