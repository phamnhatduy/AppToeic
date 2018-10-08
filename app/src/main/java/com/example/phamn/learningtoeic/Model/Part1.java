package com.example.phamn.learningtoeic.Model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Part1 {
    private static final String TAG = Part1.class.getSimpleName();
    public Question_Part1 question_part1;
    //public MutableLiveData<Question_Part1> question_part1MutableLiveData = new MutableLiveData<>();
    public int currentIndex = 0;
    public List<Question_Part1> part1List = new ArrayList<>();
    public String abc = "abc";
    public Q_Part1 q_part1;

    public Part1(){
        part1List.add(new Question_Part1(1,1,"Câu 1 answerA","Câu 1 answerB",
                        "Câu 1 answerC","Câu 1 answerD",'a'));
        part1List.add(new Question_Part1(1,1,"Câu 2 answerA","answerB",
                "answerC","answerD",'a'));
        part1List.add(new Question_Part1(1,1,"Câu 3 answerA","answerB",
                "answerC","answerD",'a'));
        //question_part1MutableLiveData.setValue(question_part1);
//        changeCurrentQuestion();
        q_part1 = new Q_Part1();
        q_part1.number.set(0);
        q_part1.answerA.set("Alần " + currentIndex);
        q_part1.answerB.set("Blần " + currentIndex);

    }

    public void changeCurrentQuestion(){
//        this.question_part1 = part1List.get(currentIndex);
//        this.question_part1 = new Question_Part1(1,1,"Câu 1 answerA","Câu 1 answerB",
//                "answerC","answerD",'a');//part1List.get(currentIndex);
    }

    public void nextQuestion(){
        currentIndex += 1;
        q_part1.number.set(q_part1.number.get() + 1);
        q_part1.answerA.set("Alần " + currentIndex);
        q_part1.answerB.set("Blần " + currentIndex);
//        Log.e("","next");
//        if(currentIndex >= part1List.size()){
//            changeCurrentQuestion();
//        }
//        else {
//            currentIndex += 1;
//            changeCurrentQuestion();
//        }
    }

    public void previousQuestion(){
        Log.e("","previous");
        if(currentIndex <= 0){
            changeCurrentQuestion();
        }
        else {
            currentIndex -= 1;
            changeCurrentQuestion();
        }
    }
}
