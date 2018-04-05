package com.example.michal.zad3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    boolean initiated = false;
    long sess_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.putExtra(Task.TASK_NUMBER, 0);
                startActivity(intent);
            }
        });

        populateWithInitialData();
    }

    public void populateWithInitialData() {
        if (!initiated) {

            AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
            TaskDao taskDao = db.taskDao();
            AnswerDao answerDao = db.answerDao();
            SessionIdentifierDao sidao = db.sessionIdentifierDao();


            sess_id = sidao.getLatestId();
            TaskProvider.setSess_id(sess_id);
            sidao.insertSessId(new SessionIdentifier());

            String q1 = "Wybierz jedno:";
            Task t1 = new Task(q1,  0, sess_id);
            long t1id = taskDao.insertTask(t1);
            answerDao.insertAnswer(new Answer(t1id, "einz"));
            answerDao.insertAnswer(new Answer(t1id, "zwei"));
            answerDao.insertAnswer(new Answer(t1id, "drei"));

            String q2 = "Costam jeszcze:";
            Task t2 = new Task(q2, 1, sess_id);
            long t2id = taskDao.insertTask(t2);
            answerDao.insertAnswer(new Answer(t2id, "jedna"));
            answerDao.insertAnswer(new Answer(t2id, "druga"));
            answerDao.insertAnswer(new Answer(t2id, "trzecia"));
            answerDao.insertAnswer(new Answer(t2id, "czwarta"));

            String q3 = "Lubisz masło?";
            Task t3 = new Task(q3, 3, sess_id);
            long t3id = taskDao.insertTask(t3);
            answerDao.insertAnswer(new Answer(t3id, "bardzo"));
            answerDao.insertAnswer(new Answer(t3id, "tak"));
            answerDao.insertAnswer(new Answer(t3id, "owszem"));
            answerDao.insertAnswer(new Answer(t3id, "jeszcze jak"));


            initiated = true;
        }
    }
}
