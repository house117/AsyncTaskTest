package com.miapp.www.asynctasktest.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo")
    List<Todo> getTodos();

    @Query("SELECT * FROM todo WHERE mId LIKE :uuid")
    Todo getTodo(String uuid);

    @Query("SELECT * FROM todo WHERE title LIKE :title")
    List<Todo>  getTodoTitle(String title);

    @Insert
    void addTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);
}