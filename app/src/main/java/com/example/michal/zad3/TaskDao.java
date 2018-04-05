package com.example.michal.zad3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by michal on 21.03.18.
 */

@Dao
public interface TaskDao {

    @Insert
    long insertTask(Task t);

    @Delete
    void deleteTask(Task t);

    @Query("SELECT * FROM Task WHERE id = :id")
    Task getTask(int id);

    @Query("SELECT * FROM Task WHERE sess_id = :sess_id")
    Task[] getAllTasks(long sess_id);
}
