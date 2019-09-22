package com.tamer.child_math;

public class Members {


    private String name;
    private String password;
    private String email ;
    private boolean ifHisATeacher ;
    private int statusOfAdd;
    private int statusOfSub;
    private int coreectAnswer ;



    public Members(String name, String password, String email, boolean ifHisATeacher, int statusOfAdd, int statusOfSub, int statusOfMulti, int statusOfDivide , int coreectAnswer) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.ifHisATeacher = ifHisATeacher;
        this.statusOfAdd = statusOfAdd;
        this.statusOfSub = statusOfSub;
        this.statusOfMulti = statusOfMulti;
        this.statusOfDivide = statusOfDivide;
        this.coreectAnswer = coreectAnswer;
    }

    public boolean isIfHisATeacher() {
        return ifHisATeacher;
    }

    public void setIfHisATeacher(boolean ifHisATeacher) {
        this.ifHisATeacher = ifHisATeacher;
    }



    private int statusOfMulti;
    private int statusOfDivide;

    public Members() {

    }

    public int getCoreectAnswer() {
        return coreectAnswer;
    }

    public void setCoreectAnswer(int coreectAnswer) {
        this.coreectAnswer = coreectAnswer;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatusOfAdd() {
        return statusOfAdd;
    }

    public void setStatusOfAdd(int statusOfAdd) {
        this.statusOfAdd = statusOfAdd;
    }

    public int getStatusOfSub() {
        return statusOfSub;
    }

    public void setStatusOfSub(int statusOfSub) {
        this.statusOfSub = statusOfSub;
    }

    public int getStatusOfMulti() {
        return statusOfMulti;
    }

    public void setStatusOfMulti(int statusOfMulti) {
        this.statusOfMulti = statusOfMulti;
    }

    public int getStatusOfDivide() {
        return statusOfDivide;
    }

    public void setStatusOfDivide(int statusOfDivide) {
        this.statusOfDivide = statusOfDivide;
    }
}
