package com.example.myapplicationandroid.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThu;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại thu
public class LoaiThuCTDialog {
    private FragmentLoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvid,tvName;
    //private boolean mEditMode; //có đang ở trạng thái edit hay ko

    public LoaiThuCTDialog(Context context, FragmentLoaiThu fragment, LoaiThu loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chitiet_loaithu,null);
        tvid = view.findViewById(R.id.tvid);
        tvName = view.findViewById(R.id.tvName);
        tvid.setText(""+loaiThu.idloaithu);
        tvName.setText(loaiThu.ten);


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
