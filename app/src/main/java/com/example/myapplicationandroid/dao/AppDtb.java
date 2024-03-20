package com.example.myapplicationandroid.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.entity.Thu;

@Database(entities = {LoaiThu.class, Thu.class}, version = 3)

public abstract class AppDtb extends RoomDatabase {


    public  abstract LoaiThuDao loaiThuDao();
    public  abstract ThuDao thuDao();

    public static AppDtb INSTANCE;

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };


    public  static AppDtb getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDtb.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDtb.class, "personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)  //đổ dl khi bđ chạy
                        .build();
            }
        }
        return  INSTANCE;
    }
    public static class  PopulateData extends AsyncTask<Void, Void,Void> {
        private LoaiThuDao loaiThuDao;
        private ThuDao thuDao;

        public PopulateData(AppDtb db) {
            loaiThuDao = db.loaiThuDao();
            thuDao= db.thuDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] cacloaithu = new String[]{"Luong", "Thuong", "Co phieu"};
            for (String it : cacloaithu){
                LoaiThu lt = new LoaiThu();
                lt.ten = it;
                loaiThuDao.insert(lt);
            }
            return null;
        }
    }
}
