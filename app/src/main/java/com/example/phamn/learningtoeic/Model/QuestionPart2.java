package com.example.phamn.learningtoeic.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionPart2 implements Serializable{
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
    @SerializedName("correctAnswer")
    private String correctAnswer;

    public QuestionPart2(int number, String questionContent, String answerA, String answerB, String answerC, String correctAnswer) {
        this.number = number;
        this.questionContent = questionContent;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctAnswer = correctAnswer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}