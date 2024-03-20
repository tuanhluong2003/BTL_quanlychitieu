package com.example.myapplicationandroid.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationandroid.entity.LoaiThu;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại thu
public interface LoaiThuDao {
    @Query("Select * from loaithu")
    LiveData<List<LoaiThu>> findAll();

    @Insert
    void insert(LoaiThu loaiThu);

    @Update
    void update (LoaiThu loaiThu);

    @Delete
    void delete(LoaiThu loaiThu);
}
