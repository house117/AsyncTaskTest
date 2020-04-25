package com.miapp.www.asynctasktest.DataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "todo")
public class Todo {
    @PrimaryKey
    @NonNull
    private String mId;

    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "iTodo")
    private String iTodo;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "completed")
    private String completed;

    public Todo() {
        mId = UUID.randomUUID().toString();
    }

    @NonNull
    public String getMId() {
        return mId;
    }

    public void setMId(@NonNull String _id) {
        mId = _id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getITodo() {
        return iTodo;
    }

    public void setITodo(String iTodo) {
        this.iTodo = iTodo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
