package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.Serial;
import com.example.phamn.learningtoeic.Model.Title;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
import com.example.phamn.learningtoeic.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {
    private List<TitleOnPhone> list = new ArrayList<>();

    private MutableLiveData<List<Serial>> listAllSerial = new MutableLiveData<>();
    private MutableLiveData<List<TitleOnPhone>> listAllTitle = new MutableLiveData<>();
    private MutableLiveData<List<TitleOnPhone>> listTitleOfSerial = new MutableLiveData<>();

    HistoryRepository repo;
    private LiveData<List<History>> listHistory = new MutableLiveData<>();
    List<Title> titleOnline = new ArrayList<>();


    private HistoryRepository historyRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new HistoryRepository(application);
        listHistory = repo.getListAllHistory();
        getAllTitle(application);
    }

    public void getAllSerial(final Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Serial>> call = apiService.getAllSerial();
        call.enqueue(new Callback<List<Serial>>() {
            @Override
            public void onResponse(Call<List<Serial>> call, Response<List<Serial>> response) {
                listAllSerial.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Serial>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getAllTitle(final Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Title>> call = apiService.getAllTitle();
        call.enqueue(new Callback<List<Title>>() {
            @Override
            public void onResponse(Call<List<Title>> call, Response<List<Title>> response) {
                titleOnline = response.body();
                updateTitle(1);
            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void convertTitle(){
        list.clear();
        for (int i = 0; i < titleOnline.size() ; i++) {
            if(i % 4 == 0) {
                TitleOnPhone t = new TitleOnPhone();
                t.setSerialID(titleOnline.get(i).getSerialID());
                t.setSerialName(titleOnline.get(i).getSerialName());
                t.setTitleName(titleOnline.get(i).getTitleName());
                t.setPart1Audio(titleOnline.get(i).getAudio());
                t.setPart2Audio(titleOnline.get(i + 1).getAudio());
                t.setPart3Audio(titleOnline.get(i + 2).getAudio());
                t.setPart4Audio(titleOnline.get(i + 3).getAudio());
                t.setPart1ID(titleOnline.get(i).getPartID());
                t.setPart2ID(titleOnline.get(i + 1).getPartID());
                t.setPart3ID(titleOnline.get(i + 2).getPartID());
                t.setPart4ID(titleOnline.get(i + 3).getPartID());
                t.setTime1(titleOnline.get(i).getTime());
                t.setTime2(titleOnline.get(i + 1).getTime());
                t.setTime3(titleOnline.get(i + 2).getTime());
                t.setTime4(titleOnline.get(i + 3).getTime());
                t.setNumberOfQuestions1(titleOnline.get(i).getNumberOfQuestions());
                t.setNumberOfQuestions2(titleOnline.get(i + 1).getNumberOfQuestions());
                t.setNumberOfQuestions3(titleOnline.get(i + 2).getNumberOfQuestions());
                t.setNumberOfQuestions4(titleOnline.get(i + 3).getNumberOfQuestions());
                list.add(t);
            }
        }
        listAllTitle.setValue(list);
    }

    public void updateTitle(int serialID){
        List<TitleOnPhone> list = new ArrayList<>();
        convertTitle();
        for(int i = 0; i < listAllTitle.getValue().size(); i++){
            if(listAllTitle.getValue().get(i).getSerialID() == serialID) {
                list.add(listAllTitle.getValue().get(i));
            }
        }
        listTitleOfSerial.setValue(list);
        listAllTitle.setValue(list);
    }
    public MutableLiveData<List<TitleOnPhone>> getListAllTitle() {
        return listAllTitle;
    }

    public LiveData<List<History>> getListHistory() {
        return listHistory;
    }

    public MutableLiveData<List<TitleOnPhone>> getListTitleOfSerial() {
        return listTitleOfSerial;
    }

    public MutableLiveData<List<Serial>> getListAllSerial() {
        return listAllSerial;
    }
}
