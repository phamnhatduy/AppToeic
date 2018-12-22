package com.example.phamn.learningtoeic.Room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.phamn.learningtoeic.DAO.HistoryDAO;
import com.example.phamn.learningtoeic.Model.History;

@Database(entities = {History.class},version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    private static HistoryDatabase instance;
    public abstract HistoryDAO historyDAO();

    public static  synchronized HistoryDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,HistoryDatabase.class,"history_database").fallbackToDestructiveMigration().addCallback(rommCallback   ).build();
        }
        return instance;
    }
    private static RoomDatabase.Callback rommCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private HistoryDAO historyDAO;

        private PopulateDbAsyncTask(HistoryDatabase historyDatabase)
        {
            historyDAO = historyDatabase.historyDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            historyDAO.insert(new History(1,"20/12/2018","100"));
            historyDAO.insert(new History(2,"20/12/2018","100"));
            return null;
        }
    }
}
