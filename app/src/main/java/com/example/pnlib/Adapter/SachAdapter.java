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

import com.example.pnlib.Dao.SachDao;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDao sachDao;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        sachDao = new SachDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qls, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaSachQLS.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSachQLS.setText(list.get(position).getTenSach());
        holder.txtGiaThueQLS.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtLoaiSachQLS.setText(String.valueOf(list.get(position).getMaLoai()));
        Sach sach = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSachQLS, txtTenSachQLS, txtGiaThueQLS, txtLoaiSachQLS;
        ImageButton ibtnDeleteQLS;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSachQLS = itemView.findViewById(R.id.txtMaSachQLS);
            txtTenSachQLS = itemView.findViewById(R.id.txtTenSachQLS);
            txtGiaThueQLS = itemView.findViewById(R.id.txtGiaThueQLS);
            txtLoaiSachQLS = itemView.findViewById(R.id.txtLoaiSachQLS);
            ibtnDeleteQLS = itemView.findViewById(R.id.ibtnDeleteQLS);
        }
    }
}