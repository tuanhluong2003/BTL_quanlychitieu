package com.example.myapplicationandroid.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chi {
    @PrimaryKey(autoGenerate = true)
    public int idchi;
    @ColumnInfo(name = "idloaichi")
    public int idloaichi;

    @ColumnInfo(name = "ten")
    public String ten;
    @ColumnInfo(name = "sotien")
    public float sotien;
    @ColumnInfo(name = "ghichu")
    public String ghichu;
}
