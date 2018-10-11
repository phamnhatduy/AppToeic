package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Part1ViewModel extends AndroidViewModel{
    public List<QuestionPart1> list = new ArrayList<>();

    public MutableLiveData<QuestionPart1> question = new MutableLiveData<>();
    public MutableLiveData<List<QuestionPart1>> listQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();

    public Part1ViewModel(@NonNull Application application) {
        super(application);
        currentIndex.setValue(0);
        getAllQuestion();
    }

    public void getAllQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/Test1/Part1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart1>> call = apiService.getAllQuestion();
        call.enqueue(new Callback<List<QuestionPart1>>() {
            @Override
            public void onResponse(Call<List<QuestionPart1>> call, Response<List<QuestionPart1>> response) {
                List<QuestionPart1> questionPart1List = response.body();
                for (int i = 0; i < questionPart1List.size() ; i++) {
                    list.add(questionPart1List.get(i));
                }
                listQuestion.setValue(list);
                question.setValue(list.get(0));
            }

            @Override
            public void onFailure(Call<List<QuestionPart1>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<QuestionPart1> getQuestion() {
        return question;
    }

    public MutableLiveData<List<QuestionPart1>> getListQuestion() {
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
