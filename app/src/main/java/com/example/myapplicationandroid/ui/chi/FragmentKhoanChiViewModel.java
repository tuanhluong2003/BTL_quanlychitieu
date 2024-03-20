package com.example.myapplicationandroid.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.entity.Chi;
import com.example.myapplicationandroid.repository.RepositoryLoaiChi;
import com.example.myapplicationandroid.repository.RepositoryChi;

import java.util.List;

public class FragmentKhoanChiViewModel extends AndroidViewModel {
    private RepositoryChi mrepositoryChi;
    private RepositoryLoaiChi mRepositoryLoaiChi;

    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public FragmentKhoanChiViewModel(@NonNull Application application) {
        super(application);
        mrepositoryChi= new RepositoryChi(application);
        mAllChi = mrepositoryChi.getAllchi(); //trả về ds loại chi có trong csdl
        mRepositoryLoaiChi = new RepositoryLoaiChi(application);
        mAllLoaiChi = mRepositoryLoaiChi.getAllLoaiChi();
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public LiveData<List<Chi>> getAllChi(){
        return mAllChi;

    }
    public void insert(Chi chi){
        mrepositoryChi.insert(chi);

    }
    public void delete(Chi chi){
        mrepositoryChi.delete(chi);
    }


    public void update(Chi chi){
        mrepositoryChi.update(chi);
    }
}