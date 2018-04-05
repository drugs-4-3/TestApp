package com.example.michal.zad3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by michal on 23.03.18.
 */

@Dao
public interface SessionIdentifierDao {

    @Insert
    void insertSessId(SessionIdentifier s);

    @Query("SELECT id FROM SessionIdentifier ORDER BY id DESC LIMIT 1")
    long getLatestId();
}
