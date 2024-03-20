package com.example.myapplicationandroid.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.navigation.PopUpToBuilder;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.entity.Chi;

@Database(entities = {LoaiChi.class, Chi.class}, version = 3)

public abstract class AppDtbChi extends RoomDatabase {
    public  abstract LoaiChiDao loaiChiDao();
    public  abstract ChiDao chiDao();

    public static AppDtbChi INSTANCE;
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };
    public  static AppDtbChi getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDtbChi.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDtbChi.class, "personal_db_chi")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)  //đổ dl khi bđ chạy
                        .build();
            }
        }
        return  INSTANCE;
    }
    public static class  PopulateData extends AsyncTask<Void, Void,Void> {
        private LoaiChiDao loaiChiDao;
        private ChiDao chiDao;

        public PopulateData(AppDtbChi db) {
            loaiChiDao = db.loaiChiDao();
            chiDao= db.chiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] cacloaichi = new String[]{"An uong", "Quan ao", "Vui choi"};
            for (String it : cacloaichi){
                LoaiChi lt = new LoaiChi();
                lt.ten = it;
                loaiChiDao.insert(lt);
            }
//            Chi chi = new Chi();
//            chi.ten = "Tiền ăn tháng 1";
//            chi.sotien = 500000;
//            chi.idloaichi = 1;
//            chi.ghichu = "";
//            chiDao.insert(chi);
            return null;
        }
    }
}
