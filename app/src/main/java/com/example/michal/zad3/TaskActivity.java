package com.example.michal.zad3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {

    Button backButton;
    Button forwardButton;
    Button enterButton;
    TextView textView;
    GridView answersGrid;
    TaskProvider taskProvider;

    int task_number = -1;
    int selected_answer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskProvider = new TaskProvider(getApplicationContext());

        backButton = (Button) findViewById(R.id.back_button);
        forwardButton = (Button) findViewById(R.id.forward_button);
        textView = (TextView) findViewById(R.id.textView);
        answersGrid = (GridView) findViewById(R.id.answers_grid);
        enterButton = (Button) findViewById(R.id.enter_button);

        task_number = getIntent().getIntExtra(Task.TASK_NUMBER, 0);

        Log.i("asd", "task_number: " + task_number + "\n");
        Log.i("asd", "list size: " + taskProvider.getTasks().length + "\n");

        // if current task exists - show it. else - move to results actvity
        if (taskProvider.getTasks().length > task_number) {
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selected_answer == -1) {
                        Toast.makeText(getApplicationContext(), "Wybierz odpowiedź!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Task t = taskProvider.getTasks()[task_number];
//                        Log.i("zad3", "selected_answer: " + selected_answer);
//                        Log.i("zad3", "correct_answer: " + );

                        // insert selected Answer to DB
                        AppDatabase.
                                getAppDatabase(getApplicationContext()).
                                userAnswerDao().
                                insertUserAnswer(new UserAnswer(selected_answer, t.id));

                        // switch to next task
                        getIntent().putExtra(Task.TASK_NUMBER, task_number + 1);
                        recreate();
                    }
                }
            });


            final Task task = taskProvider.getTasks()[task_number];

            // set background color for all - xml works badly since it omits margins
            for (int i = 0; i < answersGrid.getChildCount(); i++) {
                View vi = answersGrid.getChildAt(i);
                vi.setBackgroundColor(Color.parseColor("#4c996a"));
            }

            textView.setText(task.question);
            answersGrid.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {

                    return taskProvider.getAnswers(task).length;
                }

                @Override
                public Object getItem(int position) {
                    return taskProvider.getAnswers(task)[position].text;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    View result = (View) convertView;
                    if (result == null) {
                        result = (View) LayoutInflater.from(getApplicationContext()).inflate(R.layout.answer_view, null);
                        result.setBackgroundColor(Color.parseColor("#4c996a"));
                        result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // save set chosen answer inside activity, highlight it

                                for (int i = 0; i < answersGrid.getChildCount(); i++) {
                                    View vi = answersGrid.getChildAt(i);
                                    vi.setBackgroundColor(Color.parseColor("#4c996a"));
                                }
                                v.setBackgroundColor(Color.parseColor("#FF235837"));
                                selectAnswer(position);
                            }
                        });
                        TextView tv = result.findViewById(R.id.answer_element);
                        tv.setText(taskProvider.getAnswers(task)[position].text);
                    }
                    return result;

                }
            });
        }
        else {
            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
            startActivity(intent);
        }
    }

    private void selectAnswer(int i) {
        selected_answer = i;
    }
}
