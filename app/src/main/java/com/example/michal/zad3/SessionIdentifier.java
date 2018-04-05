package com.example.michal.zad3;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by michal on 23.03.18.
 */

@Entity
public class SessionIdentifier {

    @PrimaryKey(autoGenerate = true)
    public int id;
}
