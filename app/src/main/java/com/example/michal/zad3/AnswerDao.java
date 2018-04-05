package com.example.michal.zad3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by michal on 23.03.18.
 */

@Dao
public interface AnswerDao {

    @Insert()
    public void insertAnswer(Answer a);

    @Delete
    public void deleteAnswer(Answer a);

    @Query("SELECT * FROM Answer WHERE task_id = :task_id")
    public Answer[] getAnswersForTask(int task_id);
}

