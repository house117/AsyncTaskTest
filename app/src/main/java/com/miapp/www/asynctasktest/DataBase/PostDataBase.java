package com.miapp.www.asynctasktest.DataBase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.miapp.www.asynctasktest.DataBase.Post;

@Database(entities = {Post.class}, version = 1)
public abstract class PostDataBase extends RoomDatabase {

    public abstract PostDao getPostDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `todo` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }


    };*/
}
