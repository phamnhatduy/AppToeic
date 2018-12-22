package com.example.phamn.learningtoeic.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.phamn.learningtoeic.DAO.HistoryDAO;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Room.HistoryDatabase;

import java.util.List;

public class HistoryRepository {
    private HistoryDAO historyDAO;
    private LiveData<List<History>> listAllHistory;

    public HistoryRepository(Application application){
        HistoryDatabase databse = HistoryDatabase.getInstance(application);
        historyDAO  = databse.historyDAO();
        listAllHistory = historyDAO.getListAllHistory();
    }

    public  void insert(History history){
        new InsertNoteAsyncTask(historyDAO).execute(history);
    }

    public  void delete(History history){
        new DeleteNoteAsyncTask(historyDAO).execute(history);
    }

    public  void update(History history){
        new UpdateNoteAsyncTask(historyDAO).execute(history);
    }

    public void deleteAllHistory(){
        new DeleteALlNoteAsyncTask(historyDAO).execute();
    }

    public LiveData<List<History>> getListAllHistory()
    {
        return listAllHistory;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<History, Void,Void> {
        private HistoryDAO historyDAO;
        private InsertNoteAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.insert(histories[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<History, Void,Void>{
        private HistoryDAO historyDAO;
        private UpdateNoteAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.update(histories[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<History, Void,Void>{
        private HistoryDAO historyDAO;
        private DeleteNoteAsyncTask(HistoryDAO historyDAO){
            this.historyDAO= historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.delete(histories[0]);
            return null;
        }
    }

    private static class DeleteALlNoteAsyncTask extends AsyncTask<History, Void,Void>{
        private HistoryDAO historyDAO;
        private DeleteALlNoteAsyncTask(HistoryDAO historyDAO){
            this.historyDAO=historyDAO;
        }

        @Override
        protected Void doInBackground(History... histories) {
            historyDAO.deleteAllHistories();
            return null;
        }
    }
}