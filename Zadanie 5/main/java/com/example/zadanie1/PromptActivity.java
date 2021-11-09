package com.example.zadanie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    public static  final String KEY_EXTRA_ANSWER_SHOWN = "answerShown";
    private boolean correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        findViewById(R.id.show_answer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                TextView t = findViewById(R.id.answer_text_view);
                t.setText(answer);
                SetAnswerShownResult(true);
            }
        });
    }

    private void SetAnswerShownResult(boolean answerWasShown)
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }
}