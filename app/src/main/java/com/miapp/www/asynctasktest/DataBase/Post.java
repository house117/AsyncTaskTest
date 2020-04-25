package com.miapp.www.asynctasktest.DataBase;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post")
public class Post {

    @PrimaryKey
    @NonNull
    private String mId;

    @ColumnInfo(name = "userId")
    private String userId;

    @ColumnInfo(name = "iPost")
    private String iPost;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    public Post() {
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

    public void setUserId(String _userId) {
        userId = _userId;
    }

    public String getIPost() {
        return iPost;
    }

    public void setIPost(String _iPost) {
        iPost = _iPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String _title) {
        title = _title;
    }

    public String getBody() {
        return title;
    }

    public void setBody(String _body) {
        body = _body;
    }
}
