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

import com.example.pnlib.Dao.PhieuMuonDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Dao.ThanhVienDAO;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<PhieuMuon> list;
    ThanhVienDAO thanhVienDAO;
    SachDAO sachDAO;
    PhieuMuonDAO phieuMuonDAO;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
        thanhVienDAO = new ThanhVienDAO(context);
        sachDAO = new SachDAO(context);
        phieuMuonDAO = new PhieuMuonDAO(context);
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
        PhieuMuon phieuMuon = list.get(position);
        holder.txtMaPhieuQLPM.setText(String.valueOf(list.get(position).getMaPM()));
        String tenTV = thanhVienDAO.getID(String.valueOf(phieuMuon.getMaTV()));
        holder.txtThanhVienQLPM.setText(tenTV);
        String tenSach = sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
        holder.txtTenSachQLPM.setText(tenSach);
        int giaThue = sachDAO.getIDmoney(phieuMuon.getMaPM());
        holder.txtTienThueQLPM.setText(String.valueOf(giaThue));
        if (list.get(position).getTraSach() == 1) {
            holder.txtTraSach.setText("Đã trả sách");
            holder.txtTraSach.setTextColor(Color.parseColor("#009C06"));
        } else {
            holder.txtTraSach.setText("Chưa trả sách");
            holder.txtTraSach.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.txtNgayThue.setText(list.get(position).getNgay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPhieuQLPM, txtThanhVienQLPM, txtTenSachQLPM, txtTienThueQLPM, txtTraSach, txtNgayThue;
        ImageButton ibtnDeleteQLPM;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieuQLPM = itemView.findViewById(R.id.txtMaPhieuQLPM);
            txtThanhVienQLPM = itemView.findViewById(R.id.txtThanhVienQLPM);
            txtTenSachQLPM = itemView.findViewById(R.id.txtTenSachQLPM);
            txtTienThueQLPM = itemView.findViewById(R.id.txtTienThueQLPM);
            txtTraSach = itemView.findViewById(R.id.txtTraSach);
            txtNgayThue = itemView.findViewById(R.id.txtNgayThue);
            ibtnDeleteQLPM = itemView.findViewById(R.id.ibtnDeleteQLPM);
        }
    }
}
