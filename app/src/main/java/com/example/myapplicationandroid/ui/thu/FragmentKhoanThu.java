package com.example.myapplicationandroid.ui.thu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplicationandroid.MainActivity;
import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.adapter.ItemClickListener;
import com.example.myapplicationandroid.adapter.ThuRAdapter;
import com.example.myapplicationandroid.dialog.ChiDialog;
import com.example.myapplicationandroid.dialog.ThuDialog;
import com.example.myapplicationandroid.entity.Chi;
import com.example.myapplicationandroid.entity.Thu;
import com.example.myapplicationandroid.ui.chi.FragmentKhoanChi;

import java.util.List;

public class FragmentKhoanThu extends Fragment {

    private FragmentKhoanThuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRAdapter mAdapter;

    public static FragmentKhoanThu newInstance() {
        return new FragmentKhoanThu();
    }

    public FragmentKhoanThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new ThuRAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        final FragmentKhoanThu currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDialog dialog = new ThuDialog(getActivity(),currentFragment, thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu= mAdapter.getItem(position);
                ThuDialog dialog = new ThuDialog(getActivity(),currentFragment, thu);
                dialog.show();
            }
        });
        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getLayoutPosition();
                        Thu lt = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản chi đã được xóa",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);
                    }
                }
        );
        helper.attachToRecyclerView((mRv));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_khoan_thu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentKhoanThuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
    }

}