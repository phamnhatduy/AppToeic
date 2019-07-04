package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        getAllSerial(application);
        getAllTitle(application);
    }

    public void getAllSerial(final Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2019.000webhostapp.com/src/")
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
                .baseUrl("https://myhost2019.000webhostapp.com/src/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Title>> call = apiService.getAllTitle();
        call.enqueue(new Callback<List<Title>>() {
            @Override
            public void onResponse(Call<List<Title>> call, Response<List<Title>> response) {
                titleOnline = response.body();
                updateTitle(titleOnline.get(0).getSerialID());
            }

            @Override
            public void onFailure(Call<List<Title>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public void convertTitle(){
        list.clear();
        int t = 1;
        String str = "";
        List<String> lS = new ArrayList<>();
        for(int i = 0; i < titleOnline.size(); i++){
            if(!str.contains("" + titleOnline.get(i).getSerialID() + titleOnline.get(i).getTitleName())){
                str += titleOnline.get(i).getSerialID() + titleOnline.get(i).getTitleName() + ",";
                TitleOnPhone title = new TitleOnPhone();
                title.setTitleName(titleOnline.get(i).getTitleName());
                title.setSerialName(titleOnline.get(i).getSerialName());
                title.setSerialID(titleOnline.get(i).getSerialID());
                if(titleOnline.get(i).getPartName().equals("Part1")){
                    title.setPart1ID(titleOnline.get(i).getPartID());
                    title.setPart1Audio(titleOnline.get(i).getAudio());
                    title.setTime1(titleOnline.get(i).getTime());
                    title.setNumberOfQuestions1(titleOnline.get(i).getNumberOfQuestions());
                }
                if(titleOnline.get(i).getPartName().equals("Part2")){
                    title.setPart2ID(titleOnline.get(i).getPartID());
                    title.setPart2Audio(titleOnline.get(i).getAudio());
                    title.setTime2(titleOnline.get(i).getTime());
                    title.setNumberOfQuestions2(titleOnline.get(i).getNumberOfQuestions());
                }
                if(titleOnline.get(i).getPartName().equals("Part3")){
                    title.setPart3ID(titleOnline.get(i).getPartID());
                    title.setPart3Audio(titleOnline.get(i).getAudio());
                    title.setTime3(titleOnline.get(i).getTime());
                    title.setNumberOfQuestions3(titleOnline.get(i).getNumberOfQuestions());
                }
                if(titleOnline.get(i).getPartName().equals("Part4")){
                    title.setPart4ID(titleOnline.get(i).getPartID());
                    title.setPart4Audio(titleOnline.get(i).getAudio());
                    title.setTime4(titleOnline.get(i).getTime());
                    title.setNumberOfQuestions4(titleOnline.get(i).getNumberOfQuestions());
                }
                list.add(title);
                lS.add(titleOnline.get(i).getSerialID() + titleOnline.get(i).getTitleName());
            }
            else{
                for(int k = 0; k < lS.size(); k++){
                    if((titleOnline.get(i).getSerialID() + titleOnline.get(i).getTitleName()).equals(lS.get(k))){
                        if(titleOnline.get(i).getPartName().equals("Part1")){
                            list.get(k).setPart1ID(titleOnline.get(i).getPartID());
                            list.get(k).setPart1Audio(titleOnline.get(i).getAudio());
                            list.get(k).setTime1(titleOnline.get(i).getTime());
                            list.get(k).setNumberOfQuestions1(titleOnline.get(i).getNumberOfQuestions());
                            break;
                        }
                        if(titleOnline.get(i).getPartName().equals("Part2")){
                            list.get(k).setPart2ID(titleOnline.get(i).getPartID());
                            list.get(k).setPart2Audio(titleOnline.get(i).getAudio());
                            list.get(k).setTime2(titleOnline.get(i).getTime());
                            list.get(k).setNumberOfQuestions2(titleOnline.get(i).getNumberOfQuestions());
                            break;
                        }
                        if(titleOnline.get(i).getPartName().equals("Part3")){
                            list.get(k).setPart3ID(titleOnline.get(i).getPartID());
                            list.get(k).setPart3Audio(titleOnline.get(i).getAudio());
                            list.get(k).setTime3(titleOnline.get(i).getTime());
                            list.get(k).setNumberOfQuestions3(titleOnline.get(i).getNumberOfQuestions());
                            break;
                        }
                        if(titleOnline.get(i).getPartName().equals("Part4")){
                            list.get(k).setPart4ID(titleOnline.get(i).getPartID());
                            list.get(k).setPart4Audio(titleOnline.get(i).getAudio());
                            list.get(k).setTime4(titleOnline.get(i).getTime());
                            list.get(k).setNumberOfQuestions4(titleOnline.get(i).getNumberOfQuestions());
                            break;
                        }
                    }
                }
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
