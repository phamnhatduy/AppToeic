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
    public MutableLiveData<List<String>> listAnswerChosen = new MutableLiveData<>();
    public List<String> stringList = new ArrayList<>();

    public Part1ViewModel(@NonNull Application application) {
        super(application);
        currentIndex.setValue(0);
        getAllQuestion();
        for (int i = 0; i < list.size(); i++){
            stringList.add("A");
        }
        listAnswerChosen.setValue(stringList);
    }

    public void getAllQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/Test1/Part1/")
                //.baseUrl("http://www.myhost2018.byethost31.com/Toeic/Test1/Part1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart1>> call = apiService.getAllQuestionPart1();
        call.enqueue(new Callback<List<QuestionPart1>>() {
            @Override
            public void onResponse(Call<List<QuestionPart1>> call, Response<List<QuestionPart1>> response) {
                List<QuestionPart1> questionPart1List = response.body();
                for (int i = 0; i < questionPart1List.size() ; i++) {
                    //list.add(questionPart1List.get(i));
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

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public void changeAnswer(String answer){
        listQuestion.getValue().get(currentIndex.getValue()).setAnswerChosen(answer);
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

    public MutableLiveData<List<String>> getListAnswerChosen() {
        return listAnswerChosen;
    }
}
