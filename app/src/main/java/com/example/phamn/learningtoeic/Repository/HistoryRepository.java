package com.example.phamn.learningtoeic.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.phamn.learningtoeic.DAO.HistoryDAO;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Room.HistoryDatabase;

import java.util.List;

public class HistoryRepository {
    private HistoryDAO historyDAO;
    private LiveData<List<History>> listAllHistory;
    private History history;
    private String date;

    public HistoryRepository(Application application){
        HistoryDatabase database = HistoryDatabase.getInstance(application);
        historyDAO  = database.historyDAO();
        listAllHistory = historyDAO.getListAllHistory();
        //date = historyDAO.getDate();
        //history = historyDAO.getHistory(partID);
    }

    public  void insert(History history){
        new InsertHistoryAsyncTask(historyDAO).execute(history);
    }

//    public  void delete(History history){
//        new DeleteHistoryAsyncTask(historyDAO).execute(history);
//    }

    public  void update(History history){
        new UpdateHistoryAsyncTask(historyDAO).execute(history);
    }

    public void deleteHistory(int partID){
        new DeleteHistoryAsyncTask(historyDAO).execute(partID);
    }

    public void deleteAllHistory(){
        new DeleteALlHistoryAsyncTask(historyDAO).execute();
    }

    public LiveData<List<History>> getListAllHistory()
    {
        return listAllHistory;
    }

    public History getHistory() {
        return history;
    }

    private static class InsertHistoryAsyncTask extends AsyncTask<History, Void,Void> {
        private HistoryDAO historyDAO;
        private InsertHistoryAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.insert(histories[0]);
            return null;
        }
    }

    private static class UpdateHistoryAsyncTask extends AsyncTask<History, Void,Void>{
        private HistoryDAO historyDAO;
        private UpdateHistoryAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.update(histories[0]);
            return null;
        }
    }

    private static class DeleteHistoryAsyncTask extends AsyncTask<Integer, Void, Void>{
        private HistoryDAO historyDAO;
        private DeleteHistoryAsyncTask(HistoryDAO historyDAO){
            this.historyDAO= historyDAO;
        }

        @Override
        protected Void doInBackground(Integer... partID) {
            historyDAO.deleteHistory(partID[0]);
            return null;
        }
    }

    private static class DeleteALlHistoryAsyncTask extends AsyncTask<History, Void,Void>{
        private HistoryDAO historyDAO;
        private DeleteALlHistoryAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.deleteAllHistories();
            return null;
        }
    }

    public String getDate() {
        return date;
    }
}