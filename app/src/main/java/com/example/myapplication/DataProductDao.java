package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataProductDao {
    @Query("SELECT *FROM dataproduct")
    List<DataProduct> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DataProduct dataProduct);

    @Update
    void update(DataProduct dataProduct);

    @Delete
    void delete(DataProduct dataProduct);
}
