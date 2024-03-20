package com.example.myapplicationandroid.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplicationandroid.dao.LoaiThuDao;
import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.repository.RepositoryLoaiThu;

import java.util.List;

public class FragmentLoaiThuViewModel extends AndroidViewModel {
    private RepositoryLoaiThu mrepositoryLoaiThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public FragmentLoaiThuViewModel(@NonNull Application application) {
        super(application);
        mrepositoryLoaiThu= new RepositoryLoaiThu(application);
        mAllLoaiThu = mrepositoryLoaiThu.getAllLoaiThu(); //trả về ds loại thu có trong csdl
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;

    }
    public void insert(LoaiThu loaiThu){
        mrepositoryLoaiThu.insert(loaiThu);
        //chèn loại thu vào csdl

    }

    public void delete(LoaiThu loaiThu){
        mrepositoryLoaiThu.delete(loaiThu);
    }
    public void update(LoaiThu loaiThu){
        mrepositoryLoaiThu.update(loaiThu);
    }
}