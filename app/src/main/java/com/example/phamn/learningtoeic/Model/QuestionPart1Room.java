package com.example.phamn.learningtoeic.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "QuestionPart1")
public class QuestionPart1Room {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private int questionNumber;
    private int image;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private char correctAnswer;


    public QuestionPart1Room(@NonNull int questionNumber, @NonNull int image,
                             @NonNull String answerA,
                             @NonNull String answerB,
                             @NonNull String answerC,
                             @NonNull String answerD,
                             @NonNull char correctAnswer) {
        this.questionNumber = questionNumber;
        this.image = image;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getImage() {
        return image;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}
