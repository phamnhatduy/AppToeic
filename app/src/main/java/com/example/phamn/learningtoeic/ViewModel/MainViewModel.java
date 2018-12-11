package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.phamn.learningtoeic.Model.Title;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel {
    public List<TitleOnPhone> list = new ArrayList<>();

    public MutableLiveData<List<TitleOnPhone>> listAllTitle = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        getAllTitle();
    }

    public void getAllTitle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Title>> call = apiService.getAllTitle();
        call.enqueue(new Callback<List<Title>>() {
            @Override
            public void onResponse(Call<List<Title>> call, Response<List<Title>> response) {
                List<Title> titles = response.body();
                for (int i = 0; i < titles.size() ; i++) {
                    if(i % 4 == 0) {
                        TitleOnPhone t = new TitleOnPhone();
                        t.setTitleName(titles.get(i).getTitleName());
                        t.setTime1(titles.get(i).getTime());
                        t.setTime2(titles.get(i + 1).getTime());
                        t.setTime3(titles.get(i + 2).getTime());
                        t.setTime4(titles.get(i + 3).getTime());
                        t.setNumberOfQuestions1(titles.get(i).getNumberOfQuestions());
                        t.setNumberOfQuestions2(titles.get(i + 1).getNumberOfQuestions());
                        t.setNumberOfQuestions3(titles.get(i + 2).getNumberOfQuestions());
                        t.setNumberOfQuestions4(titles.get(i + 3).getNumberOfQuestions());

                        list.add(t);
                    }
                }
                listAllTitle.setValue(list);
            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<List<TitleOnPhone>> getListAllTitle() {
        return listAllTitle;
    }
}
