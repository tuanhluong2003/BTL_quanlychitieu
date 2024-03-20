package com.example.myapplicationandroid.ui.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.adapter.ThongKeLoaiChiRAdapter;
import com.example.myapplicationandroid.adapter.ThongKeLoaiThuRAdapter;
import com.example.myapplicationandroid.entity.ThongKeLoaiChi;
import com.example.myapplicationandroid.entity.ThongKeLoaiThu;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentThongKe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentThongKe extends Fragment {

    private ThongKeViewModel mThongKeViewModel;
    private EditText mEtTongThu;
    private RecyclerView rvThongKeLoaiThu;
    private ThongKeLoaiThuRAdapter mThongKeLoaiThuAdapter;

    private EditText mEtTongChi;
    private RecyclerView rvThongKeLoaiChiAdapter;

    private ThongKeLoaiChiRAdapter mThongKeLoaiChi;


    public FragmentThongKe() {
    }


    public static FragmentThongKe newInstance() {
        FragmentThongKe fragment = new FragmentThongKe();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_thong_ke, container, false);
        mEtTongThu = view.findViewById(R.id.etTongThu);
        rvThongKeLoaiThu = view.findViewById(R.id.rvThongKeLoaiThu);

        mEtTongChi = view.findViewById(R.id.etTongChi);
        rvThongKeLoaiChiAdapter = view.findViewById(R.id.rvThongKeLoaiChi);

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);
        mThongKeLoaiThuAdapter = new ThongKeLoaiThuRAdapter(getActivity());
        rvThongKeLoaiThu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiThu.setAdapter(mThongKeLoaiThuAdapter);

        mThongKeViewModel = new ViewModelProvider(this).get(ThongKeViewModel.class);
        mThongKeLoaiChi = new ThongKeLoaiChiRAdapter(getActivity());
        rvThongKeLoaiChiAdapter.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvThongKeLoaiChiAdapter.setAdapter(mThongKeLoaiChi);

        mThongKeViewModel.getTongThu().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongThu.setText(""+ tong);
            }
        });

        mThongKeViewModel.getTongChi().observe(getActivity(), new Observer<Float>() {
            @Override
            public void onChanged(Float tong) {
                mEtTongChi.setText(""+ tong);
            }
        });

        mThongKeViewModel.getThongKeLoaiThus().observe(getActivity(), new Observer<List<ThongKeLoaiThu>>() {
            @Override
            public void onChanged(List<ThongKeLoaiThu> thongKeLoaiThus) {
                mThongKeLoaiThuAdapter.setList(thongKeLoaiThus);
            }
        });

        mThongKeViewModel.getThongKeLoaiChis().observe(getActivity(), new Observer<List<ThongKeLoaiChi>>() {
            @Override
            public void onChanged(List<ThongKeLoaiChi> thongKeLoaiChis) {
                mThongKeLoaiChi.setList(thongKeLoaiChis);
            }
        });
        return view;
    }
}