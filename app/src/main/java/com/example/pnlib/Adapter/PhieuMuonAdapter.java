package com.example.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.PhieuMuonDao;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<PhieuMuon> list;
    PhieuMuonDao phieuMuonDao;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        phieuMuonDao = new PhieuMuonDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlpm, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaPhieuQLPM.setText(String.valueOf(list.get(position).getMapm()));
        holder.txtThanhVienQLPM.setText(list.get(position).getTenTv());
        holder.txtTenSachQLPM.setText(list.get(position).getTenSach());
        holder.txtTienThueQLPM.setText(String.valueOf(list.get(position).getTienthue()));
        holder.txtNgayThue.setText(list.get(position).getNgay());
        if (list.get(position).getTrasach() == 1) {
            holder.txtTraSach.setText("Đã trả sách");
            holder.txtTraSach.setTextColor(Color.parseColor("#009C06"));
        }
        else{
            holder.txtTraSach.setText("Chưa trả sách");
            holder.txtTraSach.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPhieuQLPM, txtThanhVienQLPM, txtTenSachQLPM, txtTienThueQLPM, txtNgayThue, txtTraSach;
        ImageButton ibtnDeleteQLPM;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieuQLPM = itemView.findViewById(R.id.txtMaPhieuQLPM);
            txtThanhVienQLPM = itemView.findViewById(R.id.txtThanhVienQLPM);
            txtTenSachQLPM = itemView.findViewById(R.id.txtTenSachQLPM);
            txtTienThueQLPM = itemView.findViewById(R.id.txtTienThueQLPM);
            txtNgayThue = itemView.findViewById(R.id.txtNgayThue);
            txtTraSach = itemView.findViewById(R.id.txtTraSach);
            ibtnDeleteQLPM = itemView.findViewById(R.id.ibtnDeleteQLPM);
        }
    }
}
