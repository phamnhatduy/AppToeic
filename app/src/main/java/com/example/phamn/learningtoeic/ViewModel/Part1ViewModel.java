package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Part1OnPhone;
import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.Service.APIService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Part1ViewModel extends AndroidViewModel{
    public List<Part1OnPhone> list = new ArrayList<>();

    public MutableLiveData<Part1OnPhone> question = new MutableLiveData<>();
    public MutableLiveData<List<Part1OnPhone>> listQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> serial = new MutableLiveData<>();
    public MutableLiveData<Integer> partID = new MutableLiveData<>();
    public Part1ViewModel(@NonNull Application application) {
        super(application);
        currentIndex.setValue(0);
    }

    public void getAllQuestion(int partID) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://myhost2018.000webhostapp.com/" + serial.getValue() + "/" + title.getValue() + "/Part1/")
//                .baseUrl("https://myhost2018.000webhostapp.com/Serial1/Test1/Part1/")
                .baseUrl("https://myhost2019.000webhostapp.com/src/JSON/")// + partID.getValue())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart1>> call = apiService.getAllQuestionPart1(partID);
        call.enqueue(new Callback<List<QuestionPart1>>() {
            @Override
            public void onResponse(Call<List<QuestionPart1>> call, Response<List<QuestionPart1>> response) {
                List<QuestionPart1> questionPart1List = response.body();
                for (int i = 0; i < questionPart1List.size() ; i++) {
                    Part1OnPhone q = new Part1OnPhone();
                    q.setQuestionNumber(questionPart1List.get(i).getNumber());
                    q.setAnswerA(questionPart1List.get(i).getAnswerA());
                    q.setAnswerB(questionPart1List.get(i).getAnswerB());
                    q.setAnswerC(questionPart1List.get(i).getAnswerC());
                    q.setAnswerD(questionPart1List.get(i).getAnswerD());
                    q.setAnswerChosen("");
                    q.setCorrectAnswer(questionPart1List.get(i).getCorrectAnswer());
                    q.setImage(questionPart1List.get(i).getImage());
                    list.add(q);
                }
                listQuestion.setValue(list);
                question.setValue(listQuestion.getValue().get(0));
            }

            @Override
            public void onFailure(Call<List<QuestionPart1>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateQuestion(int index){
        question.setValue(listQuestion.getValue().get(index));
        currentIndex.setValue(index);
    }

    public void nextQuestion(){
        if(currentIndex.getValue() < (listQuestion.getValue().size() - 1)) {
            currentIndex.setValue(currentIndex.getValue() + 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
        else
            Toast.makeText(getApplication(), "First question", Toast.LENGTH_SHORT).show();
    }

    public void previousQuestion(){
        if(currentIndex.getValue() > 0) {
            currentIndex.setValue(currentIndex.getValue() - 1);
            question.setValue(listQuestion.getValue().get(currentIndex.getValue()));
        }
        else
            Toast.makeText(getApplication(), "Last question", Toast.LENGTH_SHORT).show();
    }

    public void changeAnswer(String answer){
        listQuestion.getValue().get(currentIndex.getValue()).setAnswerChosen(answer);
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setPartID(int partID){
        this.partID.setValue(partID);
        getAllQuestion(partID);
    }

    public void setTitleName(String serial, String title) {
        if(title != null)
            this.title.setValue(title);
        else
            this.title.setValue("Test1");
        if(serial != null)
            this.serial.setValue(serial);
        else
            this.serial.setValue("Serial1");
        getAllQuestion(partID.getValue());
    }

    public MutableLiveData<Part1OnPhone> getQuestion() {
        return question;
    }

    public MutableLiveData<List<Part1OnPhone>> getListQuestion() {
        return listQuestion;
    }

    public MutableLiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }
}
