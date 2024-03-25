package com.example.myapplicationandroid.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChiViewModel;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại chi
public class LoaiChiCTDialog {
    private FragmentLoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvid,tvName;


    public LoaiChiCTDialog(Context context, FragmentLoaiChi fragment, LoaiChi loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chitiet_loaichi,null);
        tvid = view.findViewById(R.id.tvid);
        tvName = view.findViewById(R.id.tvName);
        tvid.setText(""+loaiChi.idloaichi);
        tvName.setText(loaiChi.ten);


        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });

        mDialog=builder.create();
    }
    public void show() {
        mDialog.show();
    }
}
