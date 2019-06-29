package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Part3OnPhone;
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
    public List<Part4OnPhone> list1 = new ArrayList<>();
    public List<Part4OnPhone> list2 = new ArrayList<>();

    public MutableLiveData<List<Part4OnPhone>> listAllQuestion = new MutableLiveData<>();

    public MutableLiveData<List<Part4OnPhone>> listCurrentQuestion = new MutableLiveData<>();
    public MutableLiveData<Integer> currentIndex = new MutableLiveData<>();
    public MutableLiveData<String> titleName = new MutableLiveData<>();
    public MutableLiveData<String> serial = new MutableLiveData<>();
    public MutableLiveData<Integer> partID = new MutableLiveData<>();
    
    public Part4ViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllQuestion(int partID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2019.000webhostapp.com/src/JSON/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart4>> call = apiService.getAllQuestionPart4(partID);
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
                    q.setQuestionInGroup(questionPart4List.get(i).getQuestionInGroup());
                    list1.add(q);
                }
                listAllQuestion.setValue(list1);
                for(int i = 0; i < listAllQuestion.getValue().get(0).getQuestionInGroup(); i++){
                    list2.add(listAllQuestion.getValue().get(i));
                }
                currentIndex.setValue(0);
                listCurrentQuestion.setValue(list2);
            }

            @Override
            public void onFailure(Call<List<QuestionPart4>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateQuestion(int index) {
        list2.clear();
        if(listAllQuestion.getValue().get(index).getQuestionInGroup() == 1){
            int i = 0;
            do {
                i++;
            } while (listAllQuestion.getValue().get(index - i).getQuestionInGroup() == 1);
            index -= i;
        }
        for(int i = index; i < index + listAllQuestion.getValue().get(index).getQuestionInGroup(); i++)
            list2.add(listAllQuestion.getValue().get(i));
        listCurrentQuestion.setValue(list2);
        currentIndex.setValue(index);
    }

    public void nextQuestion() {
        int t = currentIndex.getValue()     // lấy vị trí hiện tại
                + listAllQuestion.getValue().get(currentIndex.getValue()).getQuestionInGroup(); // cộng thêm cho số câu hỏi trong nhóm
        if (t <= listAllQuestion.getValue().size()) {   // kiểm tra xem t có vượt quá phạm vi của danh sách hay không
            currentIndex.setValue(t);
            list2.clear();
            for(int i = 0; i < listAllQuestion.getValue().get(t).getQuestionInGroup(); i++)
                list2.add(listAllQuestion.getValue().get(t + i));
            listCurrentQuestion.setValue(list2);
        }
    }

    public void previousQuestion() {
        if(currentIndex.getValue() > 0){
            int i = 0;
            do {
                i += 1;
            } while (listAllQuestion.getValue().get(currentIndex.getValue() - i).getQuestionInGroup() == 1);
            list2.clear();
            currentIndex.setValue(currentIndex.getValue() - i);
            for(int j = 0; j < listAllQuestion.getValue().get(currentIndex.getValue()).getQuestionInGroup(); j++)
                list2.add(listAllQuestion.getValue().get(currentIndex.getValue() + j));
            listCurrentQuestion.setValue(list2);
        }
    }

    public void changeAnswer(String answer, int number) {   // listAnswer là 1 chuỗi các đáp án liền nhau
        listAllQuestion.getValue().get(currentIndex.getValue() + number).setAnswerChosen(answer);
    }

    public MutableLiveData<String> getTitleName() {
        return titleName;
    }

    public void setPartID(int partID){
        this.partID.setValue(partID);
        getAllQuestion(partID);
    }
    public void setTitleName(String serial, String titleName) {
        if(titleName != null)
            this.titleName.setValue(titleName);
        else
            this.titleName.setValue("Test1");
        if(serial != null)
            this.serial.setValue(serial);
        else
            this.serial.setValue("Serial1");
        getAllQuestion(partID.getValue());
    }

    public MutableLiveData<List<Part4OnPhone>> getListAllQuestion() {
        return listAllQuestion;
    }

    public MutableLiveData<List<Part4OnPhone>> getListCurrentQuestion() {
        return listCurrentQuestion;
    }

    public MutableLiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }
}
