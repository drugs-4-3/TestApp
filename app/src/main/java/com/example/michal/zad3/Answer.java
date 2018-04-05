package com.example.michal.zad3;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by michal on 23.03.18.
 */

@Entity
public class Answer {

    @PrimaryKey(autoGenerate = true)
    int id;

    long task_id;

    String text;

    public Answer(long task_id, String text) {
        this.task_id = task_id;
        this.text = text;
    }

}
