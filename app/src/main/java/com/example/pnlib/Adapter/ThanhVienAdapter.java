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

import com.example.pnlib.Dao.ThanhVienDAO;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<ThanhVien> list;
    ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        thanhVienDAO= new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qltv,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaTvQLTV.setText(String.valueOf(list.get(position).getMaTV()));
        holder.txtHoTenQLTV.setText(list.get(position).getHoTen());
        holder.txtNamSinhQLTV.setText(list.get(position).getNamSinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaTvQLTV, txtHoTenQLTV, txtNamSinhQLTV;
        ImageButton ibtnDeleteQLTV;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTvQLTV = itemView.findViewById(R.id.txtMaTvQLTV);
            txtHoTenQLTV = itemView.findViewById(R.id.txtHoTenQLTV);
            txtNamSinhQLTV = itemView.findViewById(R.id.txtNamSinhQLTV);
            ibtnDeleteQLTV = itemView.findViewById(R.id.ibtnDeleteQLTV);
        }
    }
}
