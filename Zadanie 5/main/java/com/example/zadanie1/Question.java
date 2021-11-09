package com.example.zadanie1;

public class Question
{
    private int questionID;
    private boolean trueAnswer;

    public Question(int qid, boolean answer)
    {
        this.questionID = qid;
        this.trueAnswer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }
}