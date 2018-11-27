package com.example.phamn.learningtoeic.Model;

import com.google.gson.annotations.SerializedName;

public class QuestionPart4 {
    @SerializedName("number")
    private int number;
    @SerializedName("questionContent")
    private String questionContent;
    @SerializedName("answerA")
    private String answerA;
    @SerializedName("answerB")
    private String answerB;
    @SerializedName("answerC")
    private String answerC;
    @SerializedName("answerD")
    private String answerD;
    @SerializedName("correctAnswer")
    private String correctAnswer;
    @SerializedName("note")
    private String note;

    public QuestionPart4(int number, String questionContent, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String note) {
        this.number = number;
        this.questionContent = questionContent;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.note = note;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
