package com.example.myapplicationandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationandroid.R;
import com.example.myapplicationandroid.entity.LoaiChi;

import java.util.List;

public class LoaiChiRAdapter extends RecyclerView.Adapter<LoaiChiRAdapter.LoaiChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<LoaiChi> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public LoaiChiRAdapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiChiRAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiChiRAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.recyclerverview_loaichi_item, parent, false);

        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiViewHolder holder, int position) {
        if (mList !=null){
            holder.textviewName.setText(mList.get(position).ten);
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public LoaiChi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName;
        public ImageView imageviewCT,imageviewedit;
        public CardView cv;
        public int position;
        public LoaiChiViewHolder(@NonNull View itemView) {

            super(itemView);
            textviewName = itemView.findViewById(R.id.textviewName);
            imageviewCT=itemView.findViewById(R.id.imageviewCT);
            imageviewedit=itemView.findViewById(R.id.imageviewedit);
            cv = (CardView) itemView;

            imageviewCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemViewClickListener !=null){
                        itemViewClickListener.onItemClick(position);

                    }
                }
            });



            imageviewedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }

    }
}

