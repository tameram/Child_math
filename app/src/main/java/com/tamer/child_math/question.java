package com.tamer.child_math;

import java.io.Serializable;

public class question implements Serializable {

    String question  ;
    int level , operator  , answer;

    public question() {

    }

    public question(int answer, int level, int operator , String question) {
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

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return  question + " =" ;
    }
}
