package com.example.myapplicationandroid.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThu;
import com.example.myapplicationandroid.ui.thu.FragmentLoaiThuViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại thu
public class LoaiThuDialog {
    private FragmentLoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etid,etName;
    private boolean mEditMode; //có đang ở trạng thái edit hay ko

    public LoaiThuDialog(Context context, FragmentLoaiThu fragment, LoaiThu ...loaiThu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loaithu,null);
        etid = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        if(loaiThu != null && loaiThu.length>0){
            etid.setText(""+loaiThu[0].idloaithu);
            etName.setText(loaiThu[0].ten);
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
                        LoaiThu lt = new LoaiThu();
                        lt.ten = etName.getText().toString();
                        if (mEditMode){
                            lt.idloaithu=Integer.parseInt(etid.getText().toString());
                            mViewModel.update(lt);

                        }else {
                            mViewModel.insert(lt);
                            Toast.makeText(context, "Loại thu được lưu", Toast.LENGTH_SHORT).show();

                        }
                        }

                });
        mDialog=builder.create();
    }
    public void show() {
        mDialog.show();
    }
}
