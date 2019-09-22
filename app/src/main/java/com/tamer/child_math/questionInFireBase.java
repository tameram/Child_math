package com.tamer.child_math;

public class questionInFireBase {

    private String question;
    private int answer;
    private int level ;
    private int operator;

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public questionInFireBase(String question, int answer, int level , int operator) {
        this.question = question;
        this.answer = answer;
        this.level = level;
        this.operator = operator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
