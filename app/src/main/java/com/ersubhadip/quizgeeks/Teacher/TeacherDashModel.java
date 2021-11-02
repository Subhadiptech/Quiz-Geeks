package com.ersubhadip.quizgeeks.Teacher;

public class TeacherDashModel {
    String setNumber,totalQuestions,time,posMarks,negMarks;

    public TeacherDashModel(String setNumber, String totalQuestions, String time, String posMarks, String negMarks) {
        this.setNumber = setNumber;
        this.totalQuestions = totalQuestions;
        this.time = time;
        this.posMarks = posMarks;
        this.negMarks = negMarks;
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

    public String getPosMarks() {
        return posMarks;
    }

    public void setPosMarks(String posMarks) {
        this.posMarks = posMarks;
    }

    public String getNegMarks() {
        return negMarks;
    }

    public void setNegMarks(String negMarks) {
        this.negMarks = negMarks;
    }
}
