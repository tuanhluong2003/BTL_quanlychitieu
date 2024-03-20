package com.example.myapplicationandroid.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplicationandroid.dao.AppDtbChi;
import com.example.myapplicationandroid.dao.ChiDao;
import com.example.myapplicationandroid.entity.ThongKeLoaiChi;
import com.example.myapplicationandroid.entity.Chi;

import java.util.List;

public class RepositoryChi {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;

    public RepositoryChi(Application application) {
        this.mChiDao = AppDtbChi.getDatabase(application).chiDao();
        mAllChi= mChiDao.findAll(); //lấy về ds loại chi
    }
    public LiveData<List<Chi>> getAllchi(){
        return mAllChi;
    }

    public LiveData<Float> sumTongChi(){
        return mChiDao.sumTongChi();
    }

    public LiveData<List<ThongKeLoaiChi>> sumByLoaiChi(){
        return mChiDao.sumByLoaiChi();
    }

    public void insert(Chi chi){
        new InsertAsyncTask(mChiDao).execute(chi);

    }
    public void delete(Chi chi){
        new DeleteAsyncTask(mChiDao).execute(chi);

    }

    public void update(Chi chi){
        new UpdateAsyncTask(mChiDao).execute(chi);
    }
    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public UpdateAsyncTask(ChiDao chiDao){
            this.mChiDao=chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.update(chis[0]);
            return null;
        }
    }
    class InsertAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public InsertAsyncTask(ChiDao chiDao){
            this.mChiDao=chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.insert(chis[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void>{
        private ChiDao mChiDao;
        public DeleteAsyncTask(ChiDao chiDao){
            this.mChiDao=chiDao;
        }
        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.delete(chis[0]);
            return null;
        }
    }
}
