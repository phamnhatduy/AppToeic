package com.example.phamn.learningtoeic.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    private LiveData<List<History>> listAllHistory;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
//        repository = new HistoryRepository(application);
//        listAllHistory = repository.getListAllHistory();
    }

    public  void insert(History history)
    {
        repository.insert(history);
    }

    public  void  update(History history)
    {
        repository.update(history);
    }

//    public  void delete(History history)
//    {
//        repository.delete(history);
//    }

    public void deleteAllHistory(History history)
    {
        repository.deleteAllHistory();
    }

    public  LiveData<List<History>> getListAllHistory()
    {
        return  listAllHistory;
    }
}

