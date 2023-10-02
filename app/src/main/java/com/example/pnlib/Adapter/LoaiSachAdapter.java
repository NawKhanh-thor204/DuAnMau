package com.example.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewHolder>{
    private final Context context;
    private final ArrayList<LoaiSach> list;
    LoaiSachDAO loaiSachDAO;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
        loaiSachDAO = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlls,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaLoaiQLLS.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txtTenLoaiQLLS.setText(list.get(position).getTenLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoaiQLLS, txtTenLoaiQLLS;
        ImageButton ibtnDeleteQLLS;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoaiQLLS = itemView.findViewById(R.id.txtMaLoaiQLLS);
            txtTenLoaiQLLS = itemView.findViewById(R.id.txtTenLoaiQLLS);
            ibtnDeleteQLLS = itemView.findViewById(R.id.ibtnDeleteQLLS);
        }
    }
}
