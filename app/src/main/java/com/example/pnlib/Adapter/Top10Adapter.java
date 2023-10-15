package com.example.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Dao.ThongKeDAO;
import com.example.pnlib.Model.Top;
import com.example.pnlib.R;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.viewHolder>{
    private final Context context;
    private final ArrayList<Top> list;
    ThongKeDAO thongKeDAO;

    public Top10Adapter(Context context, ArrayList<Top> list) {
        this.context = context;
        this.list = list;
        thongKeDAO = new ThongKeDAO(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SachDAO sachDAO = new SachDAO(context);
        holder.txtTen.setText(sachDAO.getID(list.get(position).getTenSach()));
        holder.txtSoLuong.setText(String.valueOf(list.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
    TextView txtTen, txtSoLuong;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen= itemView.findViewById(R.id.txtTenSachTop10);
            txtSoLuong= itemView.findViewById(R.id.txtSoLuongTop10);
        }
    }
}
