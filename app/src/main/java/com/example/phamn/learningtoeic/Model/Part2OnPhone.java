package com.example.phamn.learningtoeic.Model;

public class Part2OnPhone {
    private int questionNumber;
    private String questionContent;
    private String answerA;
    private String answerB;
    private String answerC;
    private String correctAnswer;
    private String answerChosen;

    public Part2OnPhone(){

    }

    public Part2OnPhone(int questionNumber, String questionContent, String answerA, String answerB, String answerC, String correctAnswer, String answerChosen) {
        this.questionNumber = questionNumber;
        this.questionContent = questionContent;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.correctAnswer = correctAnswer;
        this.answerChosen = answerChosen;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswerChosen() {
        return answerChosen;
    }

    public void setAnswerChosen(String answerChosen) {
        this.answerChosen = answerChosen;
    }
}

