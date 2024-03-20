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
import com.example.myapplicationandroid.adapter.LoaiThuSpinnerAdapter;
import com.example.myapplicationandroid.entity.LoaiThu;
import com.example.myapplicationandroid.entity.Thu;
import com.example.myapplicationandroid.ui.thu.FragmentKhoanThu;
import com.example.myapplicationandroid.ui.thu.FragmentKhoanThuViewModel;
import com.example.myapplicationandroid.ui.thu.FragmentThu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

//đn hộp thoại để cho phép hiển thị lên để ng dùng thêm và lưu loại thu
public class ThuDialog {
    private FragmentKhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etid,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode; //có đang ở trạng thái edit hay ko
    private LoaiThuSpinnerAdapter mAdapter;
    public ThuDialog(Context context, FragmentKhoanThu fragment, Thu ...thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_thu,null);
        etid = view.findViewById(R.id.etid);
        etName = view.findViewById(R.id.etName);
        etAmount= view.findViewById(R.id.etAmount);
        etNote=view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });



        spType.setAdapter(mAdapter);
        if(thu != null && thu.length>0){
            etid.setText(""+thu[0].idthu);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(thu[0].ghichu);
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
                        Thu lt = new Thu();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu = etNote.getText().toString();
                        lt.idthu = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).idloaithu;
                        if (mEditMode){
                            lt.idthu=Integer.parseInt(etid.getText().toString());
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
