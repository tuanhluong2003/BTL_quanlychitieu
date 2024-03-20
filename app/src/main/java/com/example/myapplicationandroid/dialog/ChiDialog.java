package com.example.myapplicationandroid.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.adapter.LoaiChiSpinnerAdapter;
import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.entity.Chi;
import com.example.myapplicationandroid.ui.chi.FragmentKhoanChi;
import com.example.myapplicationandroid.ui.chi.FragmentKhoanChiViewModel;
import com.example.myapplicationandroid.ui.chi.FragmentChi;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại chi
public class ChiDialog {
    private FragmentKhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etid,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode; //có đang ở trạng thái edit hay ko
    private LoaiChiSpinnerAdapter mAdapter;
    public ChiDialog(Context context, FragmentKhoanChi fragment, Chi ...chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etid = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        etAmount= view.findViewById(R.id.etAmount);
        etNote=view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });



        spType.setAdapter(mAdapter);
        if(chi != null && chi.length>0){
            etid.setText(""+chi[0].idchi);
            etName.setText(chi[0].ten);
            etAmount.setText(""+chi[0].sotien);
            etNote.setText(chi[0].ghichu);
            mEditMode= true;
        }else {
            mEditMode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Chi lt = new Chi();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu = etNote.getText().toString();
                        lt.idchi = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).idloaichi;
                        if (mEditMode){
                            lt.idchi=Integer.parseInt(etid.getText().toString());
                            mViewModel.update(lt);

                        }else {
                            mViewModel.insert(lt);
                            Toast.makeText(context, "Loại chi được lưu", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
        mDialog=builder.create();
    }
    public void show() {
        mDialog.show();
    }
}
