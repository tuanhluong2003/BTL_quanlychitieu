package com.example.myapplicationandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.ThongKeLoaiThu;

import java.util.List;

public class ThongKeLoaiThuRAdapter extends
        RecyclerView.Adapter<ThongKeLoaiThuRAdapter.ThongKeLoaiThuViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<ThongKeLoaiThu> mList;

    public ThongKeLoaiThuRAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ThongKeLoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycierview_thongke_loaithu,parent, false);

        return new ThongKeLoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeLoaiThuViewHolder holder, int position) {
        if(mList != null){
            holder.tvTenLoaiThu.setText(mList.get(position).ten);
            holder.etTongLoaiThu.setText(""+mList.get(position).tong);
        }
    }

    @Override
    public int getItemCount() {
        if (mList ==null)
            return 0;
        return mList.size();
    }

    public void setList(List<ThongKeLoaiThu> list){
        this.mList =list;
        notifyDataSetChanged();
    }

    public static class ThongKeLoaiThuViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTenLoaiThu;
        public EditText etTongLoaiThu;
        public ThongKeLoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenLoaiThu=itemView.findViewById(R.id.tvTenLoaiThu);
            etTongLoaiThu= itemView.findViewById(R.id.etTongLoaiThu);
        }
    }
}
