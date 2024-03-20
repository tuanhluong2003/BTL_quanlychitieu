package com.example.myapplicationandroid.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.LoaiChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChi;
import com.example.myapplicationandroid.ui.chi.FragmentLoaiChiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại chi
public class   LoaiChiDialog {
    private FragmentLoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etid,etName;
    private boolean mEditMode; //có đang ở trạng thái edit hay ko

    public LoaiChiDialog(Context context, FragmentLoaiChi fragment, LoaiChi ...loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loaichi,null);
        etid = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        if(loaiChi != null && loaiChi.length>0){
            etid.setText(""+loaiChi[0].idloaichi);
            etName.setText(loaiChi[0].ten);
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
                        LoaiChi lt = new LoaiChi();
                        lt.ten = etName.getText().toString();
                        if (mEditMode){
                            lt.idloaichi=Integer.parseInt(etid.getText().toString());
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
