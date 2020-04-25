package com.miapp.www.asynctasktest.DataBase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface PostDao {

    @Query("SELECT * FROM post")
    List<Post> getPosts();

    @Query("SELECT * FROM post WHERE mId LIKE :uuid")
    Post getPost(String uuid);

    @Query("SELECT * FROM post WHERE title LIKE :title")
    List<Post>  getPostTitle(String title);

    @Insert
    void addPost(Post post);

    @Delete
    void deletePost(Post post);

    @Update
    void updatePost(Post post);
}
