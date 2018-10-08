package com.example.phamn.learningtoeic.Model;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class Question_Part1_List {
    public List<Question_Part1> list = new ArrayList<>();
    public MutableLiveData<List<Question_Part1>> listQuestion;
    public Question_Part1_List(){
        list.add(new Question_Part1(1,1,"1 answerA","1 answerB",
                "1 answerC","1 answerD",'a'));
        list.add(new Question_Part1(1,1,"2 answerA","2 answerB",
                "2 answerC","2 answerD",'a'));
        list.add(new Question_Part1(1,1,"3 answerA","3 answerB",
                "3 answerC","3 answerD",'a'));
        listQuestion.setValue(list);
    }

    public MutableLiveData<List<Question_Part1>> getListQuestion() {
        return listQuestion;
    }
}
