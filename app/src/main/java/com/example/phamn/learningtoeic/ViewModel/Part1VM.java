package com.example.phamn.learningtoeic.ViewModel;

import android.arch.lifecycle.ViewModel;

import com.example.phamn.learningtoeic.Model.Question_Part1;

import java.util.ArrayList;
import java.util.List;

public class Part1VM extends ViewModel {
    private  List<Question_Part1> listQestionPart1 = new ArrayList<>();

    public List<Question_Part1> getListQestionPart1() {
        return listQestionPart1;
    }

    public Part1VM() {
        for(int i = 0; i < 4; i++){
            Question_Part1 q = new Question_Part1();
            q.setQuestionNumber(i + 1);
            q.setAnswerA("A"+i + 1);
            q.setAnswerB("B"+i + 1);
            q.setAnswerC("C"+i + 1);
            q.setAnswerD("D"+i + 1);
            q.setCorrectAnswer('A');
            q.setImage(i+1);
            listQestionPart1.add(q);
        }
    }
}
