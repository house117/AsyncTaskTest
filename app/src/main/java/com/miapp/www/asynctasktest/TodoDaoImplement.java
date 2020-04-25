package com.miapp.www.asynctasktest;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.DataBase.PostDataBase;
import com.miapp.www.asynctasktest.DataBase.Todo;
import com.miapp.www.asynctasktest.DataBase.TodoDao;
import com.miapp.www.asynctasktest.DataBase.TodoDataBase;

import java.util.ArrayList;

public class TodoDaoImplement {
    @SuppressLint("StaticFieldLeak")
    private static TodoDaoImplement todoDaoImplement;

    private TodoDao mTodoDao;

    private TodoDaoImplement(Context context) {
        Context appContext = context.getApplicationContext();
        TodoDataBase database = Room.databaseBuilder(appContext, TodoDataBase.class, "Todo")
                .allowMainThreadQueries().build();
        mTodoDao = database.getTodoDao();
    }

    public static TodoDaoImplement get(Context context) {
        if (todoDaoImplement == null) {
            todoDaoImplement = new TodoDaoImplement(context);
        }
        return todoDaoImplement;
    }

    public ArrayList<Todo> getTodos() {
        return (ArrayList<Todo>) mTodoDao.getTodos();
    }

    public Todo getTodo(String id) {
        return mTodoDao.getTodo(id);
    }

    public void addTodo(Todo todo) {
        mTodoDao.addTodo(todo);
    }

    public void updateTodo(Todo todo) {
        mTodoDao.updateTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        mTodoDao.deleteTodo(todo);
    }
}
