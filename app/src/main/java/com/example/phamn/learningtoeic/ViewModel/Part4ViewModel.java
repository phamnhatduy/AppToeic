package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Part4OnPhone;
import com.example.phamn.learningtoeic.Model.QuestionPart4;
import com.example.phamn.learningtoeic.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Part4ViewModel extends AndroidViewModel{
    public List<Part4OnPhone> list = new ArrayList<>();

    public MutableLiveData<Part4OnPhone> question = new MutableLiveData<>();
    public MutableLiveData<List<Part4OnPhone>> listQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();
    public MutableLiveData<List<String>> listAnswerChosen = new MutableLiveData<>();
    public List<String> stringList = new ArrayList<>();
    
    public Part4ViewModel(@NonNull Application application) {
        super(application);
        getAllQuestion();
        for (int i = 0; i < list.size(); i++){
            stringList.add("A");
        }
        listAnswerChosen.setValue(stringList);
    }
    public void getAllQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/Test1/Part4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart4>> call = apiService.getAllQuestionPart4();
        call.enqueue(new Callback<List<QuestionPart4>>() {
            @Override
            public void onResponse(Call<List<QuestionPart4>> call, Response<List<QuestionPart4>> response) {
                List<QuestionPart4> questionPart4List = response.body();
                for (int i = 0; i < questionPart4List.size(); i++) {
                    Part4OnPhone q = new Part4OnPhone();
                    q.setQuestionNumber(questionPart4List.get(i).getNumber());
                    q.setQuestionContent(questionPart4List.get(i).getQuestionContent());
                    q.setAnswerA(questionPart4List.get(i).getAnswerA());
                    q.setAnswerB(questionPart4List.get(i).getAnswerB());
                    q.setAnswerC(questionPart4List.get(i).getAnswerC());
                    q.setAnswerD(questionPart4List.get(i).getAnswerD());
                    q.setAnswerChosen("");
                    q.setCorrectAnswer(questionPart4List.get(i).getCorrectAnswer());
                    q.setNote(questionPart4List.get(i).getNote());
                    list.add(q);
                }
                listQuestion.setValue(list);
                question.setValue(listQuestion.getValue().get(0));
            }

            @Override
            public void onFailure(Call<List<QuestionPart4>> call, Throwable t) {
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
        } else
            Toast.makeText(getApplication(), "đây là câu cuối", Toast.LENGTH_SHORT).show();
    }

    public void previousQuestion() {
        if (currentIndex.getValue() > 0) {
            currentIndex.setValue(currentIndex.getValue() - 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        } else
            Toast.makeText(getApplication(), "đây là câu đầu", Toast.LENGTH_SHORT).show();
    }

    public void changeAnswer(String answer) {
        listQuestion.getValue().get(currentIndex.getValue()).setAnswerChosen(answer);
    }

    public MutableLiveData<Part4OnPhone> getQuestion() {
        return question;
    }

    public MutableLiveData<List<Part4OnPhone>> getListQuestion() {
        return listQuestion;
    }

    public MutableLiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }

    public MutableLiveData<List<String>> getListAnswerChosen() {
        return listAnswerChosen;
    }
}
