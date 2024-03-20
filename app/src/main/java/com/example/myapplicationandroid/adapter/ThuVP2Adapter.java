package com.example.myapplicationandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplicationandroid.ui.chi.FragmentKhoanChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChi;
import com.example.myapplicationandroid.ui.thu.FragmentKhoanThu;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThu;

public class ThuVP2Adapter extends FragmentStateAdapter {
    public ThuVP2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position ==0){
            fragment = FragmentKhoanThu.newInstance();
        }
        else {
            fragment = FragmentLoaiThu.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
