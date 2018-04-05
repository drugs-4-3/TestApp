package com.example.michal.zad3;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 16.03.18.
 */

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    int id;

    public int correct_answer;

    public String question;

    long sess_id;

    @Ignore
    public static final String TASK_NUMBER = "com.example.michal.zad3.TASK_NUMBER";


    public Task(String question, int correct_answer, long sess_id) {
        this.id = id;
        this.question = question;
        this.correct_answer = correct_answer;
        this.sess_id = sess_id;
    }
}