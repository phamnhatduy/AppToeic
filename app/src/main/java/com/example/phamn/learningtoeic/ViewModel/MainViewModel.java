package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.phamn.learningtoeic.Model.History;
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

    private MutableLiveData<List<TitleOnPhone>> listAllTitle = new MutableLiveData<>();

    HistoryRepository repo;
    private LiveData<List<History>> listHistory = new MutableLiveData<>();
    List<Title> titleOnline = new ArrayList<>();

    private HistoryRepository historyRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new HistoryRepository(application);
//        repo.insert(new History(1, "11/11", "11"));
//        repo.insert(new History(2, "22/22", "22"));
//        repo.insert(new History(3, "3/3", "33"));
//        repo.insert(new History(4, "4/4", "44"));
//        repo.insert(new History(5, "55/55", "55"));
//        repo.insert(new History(6, "66/66", "66"));
//        repo.insert(new History(7, "7/7", "77"));
//        repo.insert(new History(8, "8/8", "88"));
        listHistory = repo.getListAllHistory();
        getAllTitle(application);
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
                updateTitle();
            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateTitle(){
        for (int i = 0; i < titleOnline.size() ; i++) {
            if(i % 4 == 0) {
                TitleOnPhone t = new TitleOnPhone();
                t.setTitleName(titleOnline.get(i).getTitleName());
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

    public MutableLiveData<List<TitleOnPhone>> getListAllTitle() {
        return listAllTitle;
    }

    public LiveData<List<History>> getListHistory() {
        return listHistory;
    }
}
