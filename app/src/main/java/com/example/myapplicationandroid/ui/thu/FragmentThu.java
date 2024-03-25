package com.example.myapplicationandroid.ui.thu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.adapter.ChiVP2Adapter;
import com.example.myapplicationandroid.adapter.ThuVP2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentThu extends Fragment {
    private ViewPager2 mVp;
    private TabLayout mTl;

    public FragmentThu() {
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentThu newInstance(String param1, String param2) {
        FragmentThu fragment = new FragmentThu();
        return fragment;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp = view.findViewById(R.id.viewPager2);
        mTl = view.findViewById(R.id.tabLayout);
        ThuVP2Adapter adapter = new ThuVP2Adapter(getActivity());
        mVp.setAdapter(adapter);
        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position ==0){
                    tab.setIcon(R.drawable.baseline_add_card_24);
                    tab.setText("Khoản thu");

                }else {
                    tab.setIcon(R.drawable.baseline_shopping_cart_checkout_24);
                    tab.setText("Loại Khoản Thu");

                }
            }
        }).attach();}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thu, container, false);
    }
}