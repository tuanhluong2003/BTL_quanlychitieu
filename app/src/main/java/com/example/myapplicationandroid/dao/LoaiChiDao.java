package com.example.myapplicationandroid.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationandroid.entity.LoaiChi;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface LoaiChiDao {
    @Query("Select * from loaichi")
    LiveData<List<LoaiChi>> findAll();

    @Insert
    void insert(LoaiChi loaiChi);

    @Update
    void update (LoaiChi loaiChi);

    @Delete
    void delete(LoaiChi loaiChi);
}
