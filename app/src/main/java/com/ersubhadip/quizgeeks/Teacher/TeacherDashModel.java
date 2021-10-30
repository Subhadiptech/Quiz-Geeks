package com.ersubhadip.quizgeeks.Teacher;

public class TeacherDashModel {
    String setNumber,totalQuestions,time;

    public TeacherDashModel(String setNumber, String totalQuestions, String time) {
        this.setNumber = setNumber;
        this.totalQuestions = totalQuestions;
        this.time = time;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
