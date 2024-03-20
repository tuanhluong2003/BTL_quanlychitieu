package com.example.myapplicationandroid.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Query;

import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.entity.Thu;
import com.example.myapplicationandroid.repository.RepositoryLoaiThu;
import com.example.myapplicationandroid.repository.RepositoryThu;

import java.util.List;

public class FragmentKhoanThuViewModel extends AndroidViewModel {
    private RepositoryThu mrepositoryThu;
    private RepositoryLoaiThu mRepositoryLoaiThu;

    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public FragmentKhoanThuViewModel(@NonNull Application application) {
        super(application);
        mrepositoryThu= new RepositoryThu(application);
        mAllThu = mrepositoryThu.getAllthu(); //trả về ds loại thu có trong csdl
        mRepositoryLoaiThu = new RepositoryLoaiThu(application);
        mAllLoaiThu = mRepositoryLoaiThu.getAllLoaiThu();
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;

    }
    public LiveData<List<Thu>> getAllThu(){
        return mAllThu;

    }
    public void insert(Thu thu){
        mrepositoryThu.insert(thu);
        //chèn loại thu vào csdl

    }


    public void delete(Thu thu){
        mrepositoryThu.delete(thu);
    }
    public void update(Thu thu){
        mrepositoryThu.update(thu);
    }
}