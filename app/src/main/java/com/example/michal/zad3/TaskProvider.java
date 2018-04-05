package com.example.michal.zad3;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by michal on 20.03.18.
 */

public class TaskProvider {

    private static ArrayList<Task> taskList = null;
    private Context context;
    private static long sess_id = -1;


    public TaskProvider(Context context) {
        this.context = context;
    }

//    public static ArrayList<Task> getTaskList() {
//
//        if (taskList == null) {
//            taskList = new ArrayList<>();
//
//            String q1 = "Wybierz jedno:";
//            String[] answ1 = {"jedno", "drugie", "trzecie", "czwarte", "piate", "szoste", "siudme"};
//            taskList.add(new Task(1, q1, 0));
//
//            String q2 = "Costam jeszcze:";
//            String[] answ2 = {"einz", "zwei", "drei"};
//            taskList.add(new Task(2, q2, 1));
//        }
//        return taskList;
//    }

    public Task[] getTasks() {

        return AppDatabase.getAppDatabase(context).taskDao().getAllTasks(TaskProvider.getSess_id());
    }

    public Answer[] getAnswers(Task t) {
        return AppDatabase.getAppDatabase(context).answerDao().getAnswersForTask(t.id);
    }

    public static void setSess_id(long id) {
        if (sess_id == -1) {
            sess_id = id;
        }
    }

    public static long getSess_id() {
        return sess_id;
    }
}
