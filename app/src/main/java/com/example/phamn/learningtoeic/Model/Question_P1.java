package com.example.phamn.learningtoeic.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class Question_P1 extends AndroidViewModel{
    private MutableLiveData<Integer> number;
    private MutableLiveData<Integer> image;
    private MutableLiveData<String> answerA;
    private MutableLiveData<String> answerB;
    private MutableLiveData<String> answerC;
    private MutableLiveData<String> answerD;
    private MutableLiveData<Character> correctAnswer;

    public Question_P1(@NonNull Application application,
                       MutableLiveData<Integer> number,
                       MutableLiveData<Integer> image,
                       MutableLiveData<String> answerA,
                       MutableLiveData<String> answerB,
                       MutableLiveData<String> answerC,
                       MutableLiveData<String> answerD,
                       MutableLiveData<Character> correctAnswer) {
        super(application);
        this.number = number;
        this.image = image;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public MutableLiveData<Integer> getNumber() {
        return number;
    }

    public MutableLiveData<Integer> getImage() {
        return image;
    }

    public MutableLiveData<String> getAnswerA() {
        return answerA;
    }

    public MutableLiveData<String> getAnswerB() {
        return answerB;
    }

    public MutableLiveData<String> getAnswerC() {
        return answerC;
    }

    public MutableLiveData<String> getAnswerD() {
        return answerD;
    }

    public MutableLiveData<Character> getCorrectAnswer() {
        return correctAnswer;
    }
}
