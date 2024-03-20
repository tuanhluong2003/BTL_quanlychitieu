package com.example.myapplicationandroid.ui.thongke;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplicationandroid.entity.ThongKeLoaiChi;
import com.example.myapplicationandroid.entity.ThongKeLoaiThu;
import com.example.myapplicationandroid.repository.RepositoryThu;


import com.example.myapplicationandroid.entity.ThongKeLoaiChi;
import com.example.myapplicationandroid.repository.RepositoryChi;



import java.util.List;

public class ThongKeViewModel extends AndroidViewModel {
    private RepositoryThu mRepositoryThu;

    private LiveData<Float> mTongThu;

    private LiveData<List<ThongKeLoaiThu>> mThongKeLoaiThus;
    public ThongKeViewModel(@NonNull Application application) {
        super(application);

        mRepositoryThu = new RepositoryThu(application);
        mTongThu = mRepositoryThu.sumTongThu();
        mThongKeLoaiThus=mRepositoryThu.sumByLoaiThu();

       // super(application);

        mRepositoryChi = new RepositoryChi(application);
        mTongChi = mRepositoryChi.sumTongChi();
        mThongKeLoaiChis=mRepositoryChi.sumByLoaiChi();
    }

    private RepositoryChi mRepositoryChi;

    private LiveData<Float> mTongChi;

    private LiveData<List<ThongKeLoaiChi>> mThongKeLoaiChis;




    public LiveData<Float> getTongThu() {
        return mTongThu;
    }

    public LiveData<Float> getTongChi() {
        return mTongChi;
    }



    public LiveData<List<ThongKeLoaiChi>> getThongKeLoaiChis() {
        return mThongKeLoaiChis;
    }

    public LiveData<List<ThongKeLoaiThu>> getThongKeLoaiThus() {
        return mThongKeLoaiThus;
    }
}
