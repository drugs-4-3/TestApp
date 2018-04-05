package com.example.michal.zad3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by michal on 23.03.18.
 */

@Dao
public interface UserAnswerDao {

    @Insert
    void insertUserAnswer(UserAnswer ua);

    @Delete
    void deleteUserAnswer(UserAnswer ua);

    @Query("SELECT chosen FROM UserAnswer WHERE task_id = :task_id")
    int getUserAnswerForTask(int task_id);

    @Query("SELECT COUNT(*) FROM UserAnswer JOIN Task ON UserAnswer.task_id = Task.id AND UserAnswer.chosen = Task.correct_answer WHERE Task.sess_id = :sess_id")
    int getCorrectAnswers(long sess_id);
}
