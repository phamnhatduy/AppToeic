package com.example.phamn.learningtoeic.Model;

import android.databinding.ObservableField;

public class Q_Part1 {
    public static ObservableField<Integer> number = new ObservableField<>();
    public static ObservableField<String> answerA = new ObservableField<>();
    public static ObservableField<String> answerB = new ObservableField<>();

    public static ObservableField<Integer> getNumber() {
        return number;
    }

    public static ObservableField<String> getAnswerA() {
        return answerA;
    }

    public static ObservableField<String> getAnswerB() {
        return answerB;
    }
}
