package com.example.phamn.learningtoeic.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.phamn.learningtoeic.Model.History;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Insert
    void insert(History history);

    @Update
    void update(History history);

    @Delete
    void delete(History history);

    @Query("delete from table_history")
    void deleteAllHistories();

    @Query("delete from table_history where partID = :partID")
    void deleteHistory(Integer partID);

    @Query("select * from table_history where partID = :partID order by ID asc")
    History getHistory(int partID);

    @Query("select * from table_history order by id asc")
    LiveData<List<History>> getListAllHistory();

    @Query("select date from table_history where partID = 1")
    String getDate();
}
