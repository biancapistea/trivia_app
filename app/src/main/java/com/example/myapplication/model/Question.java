package com.example.myapplication.model;

public class Question {
    private int questionId;
    private boolean answer;
    private boolean userAnswer;
    private boolean isAnswered;

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Question(int questionId, boolean answer) {
        this.questionId = questionId;
        this.answer=answer;
    }
}
