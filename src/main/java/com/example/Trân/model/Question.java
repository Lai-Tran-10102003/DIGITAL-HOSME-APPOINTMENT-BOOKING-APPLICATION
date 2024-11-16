package com.example.Trân.model;

import java.io.Serializable;

public class Question implements Serializable {
    int QuestionID;
    String QueTitle;
    String QueAnswer;

    public Question(int questionID, String queTitle, String queAnswer) {
        QuestionID = questionID;
        QueTitle = queTitle;
        QueAnswer = queAnswer;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getQueTitle() {
        return QueTitle;
    }

    public void setQueTitle(String queTitle) {
        QueTitle = queTitle;
    }

    public String getQueAnswer() {
        return QueAnswer;
    }

    public void setQueAnswer(String queAnswer) {
        QueAnswer = queAnswer;
    }
}
