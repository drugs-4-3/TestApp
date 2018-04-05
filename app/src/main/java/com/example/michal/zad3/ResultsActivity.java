package com.example.michal.zad3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView tv;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        db = AppDatabase.getAppDatabase(getApplicationContext());
        tv = (TextView) findViewById(R.id.results_tv);

        int correct_answers = db.userAnswerDao().getCorrectAnswers(TaskProvider.getSess_id());
        int number_of_tasks = db.taskDao().getAllTasks(TaskProvider.getSess_id()).length;
        double correct_percentage = ((double) correct_answers / (double) number_of_tasks)*100;

        StringBuffer sb = new StringBuffer();
        sb.append("WYNIK:\n\n");
        sb.append("Poprawne odpowiedzi: " + correct_answers + "\n");
        sb.append("Liczba pytań: " + number_of_tasks + "\n");
        tv.setText(sb.toString() + String.format("Procent poprawnych: %.2f %s %n", correct_percentage, "%"));
    }
}
