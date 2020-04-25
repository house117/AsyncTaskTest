package com.miapp.www.asynctasktest;
import android.annotation.SuppressLint;
import android.content.Context;

import com.miapp.www.asynctasktest.DataBase.Post;
import com.miapp.www.asynctasktest.DataBase.PostDao;
import com.miapp.www.asynctasktest.DataBase.PostDataBase;

import java.util.ArrayList;

import androidx.room.Room;

public class PostDaoImplement {

    @SuppressLint("StaticFieldLeak")
    private static PostDaoImplement postDaoImplement;

    private PostDao mPostDao;

    private PostDaoImplement(Context context) {
        Context appContext = context.getApplicationContext();
        PostDataBase database = Room.databaseBuilder(appContext, PostDataBase.class, "post")
                .allowMainThreadQueries().build();
        mPostDao = database.getPostDao();
    }

    public static PostDaoImplement get(Context context) {
        if (postDaoImplement == null) {
            postDaoImplement = new PostDaoImplement(context);
        }
        return postDaoImplement;
    }

    public ArrayList<Post> getPosts() {
        return (ArrayList<Post>)mPostDao.getPosts();
    }

    public Post getPost(String id) {
        return mPostDao.getPost(id);
    }

    public void addPost(Post post) {
        mPostDao.addPost(post);
    }

    public void updatePost(Post post) {
        mPostDao.updatePost(post);
    }

    public void deletePost(Post post) {
        mPostDao.deletePost(post);
    }

}
