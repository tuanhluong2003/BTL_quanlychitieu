package com.example.myapplicationandroid.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.entity.ThongKeLoaiChi;
import com.example.myapplicationandroid.entity.Chi;

import java.util.List;

@Dao
// phương thức để thực hiện các thao tác / bảng loại chi
public interface ChiDao {
    @Query("Select * from chi")
    LiveData<List<Chi>> findAll();

    @Query("Select sum(sotien) from chi")
    LiveData<Float> sumTongChi();

    @Query("Select b.idloaichi,b.ten,sum(a.sotien) as tong from chi a INNER JOIN loaichi b on a.idloaichi = b.idloaichi"+
            " GROUP BY  b.ten")
    LiveData<List<ThongKeLoaiChi>> sumByLoaiChi();

    @Insert
    void insert(Chi chi);

    @Update
    void update (Chi chi);

    @Delete
    void delete(Chi chi);
}
