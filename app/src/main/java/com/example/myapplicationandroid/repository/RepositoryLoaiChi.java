package com.example.myapplicationandroid.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplicationandroid.dao.AppDtbChi;
import com.example.myapplicationandroid.dao.LoaiChiDao;
import com.example.myapplicationandroid.entity.LoaiChi;


import java.util.List;

public class RepositoryLoaiChi {
    private LoaiChiDao mLoaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;

    public RepositoryLoaiChi(Application application) {
        this.mLoaiChiDao = AppDtbChi.getDatabase(application).loaiChiDao();
        mAllLoaiChi= mLoaiChiDao.findAll(); //lấy về ds loại thu
    }
    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }


    public void insert(LoaiChi loaiChi){
        new InsertAsyncTask(mLoaiChiDao).execute(loaiChi);

    }
    public void delete(LoaiChi loaiChi){
        new DeleteAsyncTask(mLoaiChiDao).execute(loaiChi);

    }
    public void update(LoaiChi loaiChi){
        new UpdateAsyncTask(mLoaiChiDao).execute(loaiChi);
    }
    class UpdateAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public UpdateAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.update(loaiChis[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public InsertAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<LoaiChi, Void, Void>{
        private LoaiChiDao mLoaiChiDao;
        public DeleteAsyncTask(LoaiChiDao loaiChiDao){
            this.mLoaiChiDao=loaiChiDao;
        }
        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }
}
