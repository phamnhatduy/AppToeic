package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Part1;
import com.example.phamn.learningtoeic.Model.Question_Part1;

import java.util.ArrayList;
import java.util.List;

public class Part1ViewModel extends AndroidViewModel{
    public Part1 part1;
    public List<Question_Part1> list = new ArrayList<>();

    public MutableLiveData<Question_Part1> question = new MutableLiveData<>();
    public MutableLiveData<List<Question_Part1>> listQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

    public Part1ViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public void init() {
        currentIndex.setValue(0);
        this.part1 = new Part1();
        list.add(new Question_Part1(1,1,"1 answerA","1 answerB",
                "1 answerC","1 answerD",'a'));
        list.add(new Question_Part1(1,1,"2 answerA","2 answerB",
                "2 answerC","2 answerD",'a'));
        list.add(new Question_Part1(1,1,"3 answerA","3 answerB",
                "3 answerC","3 answerD",'a'));
        list.add(new Question_Part1(1,1,"4 answerA","4 answerB",
                "4 answerC","4 answerD",'a'));
        // gán giá trị cho listQuestion
        listQuestion.setValue(list);
        // gán giá trị cho question
        question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
//        question.setValue(new Question_Part1(1,1,"1 answerA","1 answerB",
//                "1 answerC","1 answerD",'a'));

    }
    public Part1 getPart1(){
        return new Part1();
    }

    public MutableLiveData<Question_Part1> getQuestion() {
        return question;
    }

    public MutableLiveData<List<Question_Part1>> getListQuestion() {
        return listQuestion;
    }

    public MutableLiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }
    
    public void nextQuestion(){
        if(currentIndex.getValue() < (listQuestion.getValue().size() - 1)) {
            currentIndex.setValue(currentIndex.getValue() + 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
        else
            Toast.makeText(getApplication(), "đây là câu cuối", Toast.LENGTH_SHORT).show();
    }

    public void previousQuestion(){
        if(currentIndex.getValue() > 0) {
            currentIndex.setValue(currentIndex.getValue() - 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
        else
            Toast.makeText(getApplication(), "đây là câu đầu", Toast.LENGTH_SHORT).show();
    }
}
