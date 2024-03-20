package com.example.myapplicationandroid.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiThu {
    @PrimaryKey(autoGenerate = true)
    public int idloaithu;

    @ColumnInfo(name = "ten")
    public String ten;
}
