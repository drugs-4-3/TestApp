package com.example.michal.zad3;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by michal on 20.03.18.
 */

public class AnswerView extends View {

    TextView button;

    public AnswerView(Context context) {
        super(context);

        button = (TextView) findViewById(R.id.answer_element);
    }

    public void setAnswerText(String text) {
        button.setText(text);
    }
}
