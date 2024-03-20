package com.example.myapplicationandroid.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationandroid.dao.LoaiChiDao;
import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.repository.RepositoryLoaiChi;

import java.util.List;

public class FragmentLoaiChiViewModel extends AndroidViewModel {
    private RepositoryLoaiChi mRepositoryLoaiChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public FragmentLoaiChiViewModel(@NonNull Application application) {
        super(application);
        mRepositoryLoaiChi= new RepositoryLoaiChi(application);
        mAllLoaiChi = mRepositoryLoaiChi.getAllLoaiChi(); //trả về ds loại chi có trong csdl
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;

    }
    public void insert(LoaiChi loaiChi){
        mRepositoryLoaiChi.insert(loaiChi);
        //chèn loại chi vào csdl

    }
    public void delete(LoaiChi loaiChi){
        mRepositoryLoaiChi.delete(loaiChi);
    }
    public void update(LoaiChi loaiChi){
        mRepositoryLoaiChi.update(loaiChi);
    }
}