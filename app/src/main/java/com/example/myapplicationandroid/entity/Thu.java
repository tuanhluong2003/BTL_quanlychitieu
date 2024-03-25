package com.example.myapplicationandroid.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Thu {
    @PrimaryKey(autoGenerate = true)
    public int idthu;
    @ColumnInfo(name = "idloaithu")
    public int idloaithu;
    @ColumnInfo(name = "ten")
    public String ten;
    @ColumnInfo(name = "sotien")
    public float sotien;
    @ColumnInfo(name = "ghichu")
    public String ghichu;
}
