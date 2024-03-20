package com.example.myapplicationandroid.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplicationandroid.dao.AppDtb;
import com.example.myapplicationandroid.dao.LoaiThuDao;
import com.example.myapplicationandroid.entity.LoaiThu;


import java.util.List;

public class RepositoryLoaiThu {
    private LoaiThuDao mloaiThuDao;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public RepositoryLoaiThu(Application application) {
        this.mloaiThuDao = AppDtb.getDatabase(application).loaiThuDao();
        mAllLoaiThu= mloaiThuDao.findAll(); //lấy về ds loại thu
    }
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }


    public void insert(LoaiThu loaiThu){
        new InsertAsyncTask(mloaiThuDao).execute(loaiThu);

    }
    public void delete(LoaiThu loaiThu){
        new DeleteAsyncTask(mloaiThuDao).execute(loaiThu);

    }
    public void update(LoaiThu loaiThu){
        new UpdateAsyncTask(mloaiThuDao).execute(loaiThu);
    }
    class UpdateAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public UpdateAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public InsertAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<LoaiThu, Void, Void>{
        private LoaiThuDao mloaiThuDao;
        public DeleteAsyncTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }
        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }
}
