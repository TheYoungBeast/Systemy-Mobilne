package com.example.zadanie1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button trueButton, falseButton, nextButton;
    private TextView question;

    private Question[] questions = new Question[]
            {
                    new Question(R.string.question1, true),
                    new Question(R.string.question2, true),
                    new Question(R.string.question3, false),
                    new Question(R.string.question4, true),
                    new Question(R.string.question5, true)
            };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        question = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentIndex = (currentIndex+1) % questions.length;
                SetNextQueststion();
            }
        });
        SetNextQueststion();
    }

    private void SetNextQueststion()
    {
        question.setText(questions[currentIndex].getQuestionID());
    }

    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;

        if(userAnswer == correctAnswer)
            resultMessageId = R.string.correct_answer;
        else
            resultMessageId = R.string.incorrect_answer;

        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
}