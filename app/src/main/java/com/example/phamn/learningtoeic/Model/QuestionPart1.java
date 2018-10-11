package com.example.phamn.learningtoeic.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionPart1 implements Serializable{
    @SerializedName("number")
    private int number;
    @SerializedName("image")
    private String image;
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

    public QuestionPart1(int number, String image, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        this.number = number;
        this.image = image;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}