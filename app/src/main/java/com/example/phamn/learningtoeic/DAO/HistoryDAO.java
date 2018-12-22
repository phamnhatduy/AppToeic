package com.example.phamn.learningtoeic.DAO;

import android.arch.lifecycle.LiveData;
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

    @Query("select * from table_history where PartID = :partID order by ID asc")
    LiveData<List<History>> getListHistory(int partID);

    @Query("select * from table_history order by ID asc")
    LiveData<List<History>> getListAllHistory();
}
