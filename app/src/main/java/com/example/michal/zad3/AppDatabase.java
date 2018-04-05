package com.example.michal.zad3;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by michal on 21.03.18.
 */


@Database(
        entities = {
                Task.class,
                Answer.class,
                UserAnswer.class,
                SessionIdentifier.class
        },
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE = null;

    public abstract TaskDao taskDao();
    public abstract AnswerDao answerDao();
    public abstract UserAnswerDao userAnswerDao();
    public abstract SessionIdentifierDao sessionIdentifierDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.
                    databaseBuilder(
                            context,
                            AppDatabase.class,
                            "task-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }
}