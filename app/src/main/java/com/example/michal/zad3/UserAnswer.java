package com.example.michal.zad3;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by michal on 23.03.18.
 */

@Entity
public class UserAnswer {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int chosen;

    public int task_id;


    public UserAnswer(int chosen, int task_id) {
        this.chosen = chosen;
        this.task_id = task_id;
    }
}
