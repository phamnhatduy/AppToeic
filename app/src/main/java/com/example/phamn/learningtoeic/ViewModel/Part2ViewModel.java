package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.phamn.learningtoeic.Model.Part2OnPhone;
import com.example.phamn.learningtoeic.Model.QuestionPart2;
import com.example.phamn.learningtoeic.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Part2ViewModel extends AndroidViewModel {
    public List<Part2OnPhone> list = new ArrayList<>();

    public MutableLiveData<Part2OnPhone> question = new MutableLiveData<>();
    public MutableLiveData<List<Part2OnPhone>> listQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();
    public MutableLiveData<List<String>> listAnswerChosen = new MutableLiveData<>();
    public List<String> stringList = new ArrayList<>();
    public MutableLiveData<String> titleName = new MutableLiveData<>();


    public Part2ViewModel(@NonNull Application application) {
        super(application);
        currentIndex.setValue(0);
        for (int i = 0; i < list.size(); i++){
            stringList.add("A");
        }
        listAnswerChosen.setValue(stringList);
    }

    public void getAllQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/" + titleName.getValue() + "/Part2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart2>> call = apiService.getAllQuestionPart2();
        call.enqueue(new Callback<List<QuestionPart2>>() {
            @Override
            public void onResponse(Call<List<QuestionPart2>> call, Response<List<QuestionPart2>> response) {
                List<QuestionPart2> questionPart2List = response.body();
                for (int i = 0; i < questionPart2List.size(); i++) {
                    Part2OnPhone q = new Part2OnPhone();
                    q.setQuestionNumber(questionPart2List.get(i).getNumber());
                    q.setQuestionContent(questionPart2List.get(i).getQuestionContent());
                    q.setAnswerA(questionPart2List.get(i).getAnswerA());
                    q.setAnswerB(questionPart2List.get(i).getAnswerB());
                    q.setAnswerC(questionPart2List.get(i).getAnswerC());
                    q.setAnswerChosen("");
                    q.setCorrectAnswer(questionPart2List.get(i).getCorrectAnswer());
                    list.add(q);
                }
                listQuestion.setValue(list);
                question.setValue(listQuestion.getValue().get(0));
            }

            @Override
            public void onFailure(Call<List<QuestionPart2>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateQuestion(int index) {
        question.setValue(listQuestion.getValue().get(index));
        currentIndex.setValue(index);
    }

    public void nextQuestion() {
        if (currentIndex.getValue() < (listQuestion.getValue().size() - 1)) {
            currentIndex.setValue(currentIndex.getValue() + 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
    }

    public void previousQuestion() {
        if (currentIndex.getValue() > 0) {
            currentIndex.setValue(currentIndex.getValue() - 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
    }

    public void changeAnswer(String answer) {
        listQuestion.getValue().get(currentIndex.getValue()).setAnswerChosen(answer);
    }

    public MutableLiveData<String> getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        if(titleName != null)
            this.titleName.setValue(titleName);
        else
            this.titleName.setValue("Test1");
        getAllQuestion();
    }

    public MutableLiveData<Part2OnPhone> getQuestion() {
        return question;
    }

    public MutableLiveData<List<Part2OnPhone>> getListQuestion() {
        return listQuestion;
    }

    public MutableLiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }

    public MutableLiveData<List<String>> getListAnswerChosen() {
        return listAnswerChosen;
    }

}
