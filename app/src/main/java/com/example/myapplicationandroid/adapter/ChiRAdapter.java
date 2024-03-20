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
import com.example.myapplicationandroid.entity.Chi;

import java.util.List;

public class ChiRAdapter extends RecyclerView.Adapter<ChiRAdapter.ChiViewHolder>{
    private LayoutInflater mlayoutInflater;
    private List<Chi> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public ChiRAdapter(Context context) {
        mlayoutInflater= LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ChiRAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ChiRAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = mlayoutInflater.inflate(R.layout.recyclerview_chi_item, parent, false);

       return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        if (mList !=null){
            holder.textviewName.setText(mList.get(position).ten);
            holder.tvAmount.setText(""+mList.get(position).sotien+"Đồng");
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (mList==null)
            return 0;
        return mList.size();
    }
    public Chi getItem(int position){
        if(mList==null || position>= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName,tvAmount;
        public ImageView imageviewCT,imageviewedit;
        public CardView cv;
        public int position;
        public ChiViewHolder(@NonNull View itemView) {

            super(itemView);
            textviewName = itemView.findViewById(R.id.textviewName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
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

