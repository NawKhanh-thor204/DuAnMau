package com.example.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewHolder> {
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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qlls, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        LoaiSach loaiSach = list.get(position);
        holder.txtMaLoaiQLLS.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txtTenLoaiQLLS.setText(list.get(position).getTenLoai());
        holder.ibtnDeleteQLLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa loại sách này này?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String maLoai = String.valueOf(loaiSach.getMaLoai());
                        long delete = loaiSachDAO.delete(maLoai);
                        if (delete>0) {
                            list.clear();
                            list.addAll(loaiSachDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa loại sách mã ' " + holder.txtMaLoaiQLLS.getText().toString() + " '!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No",null);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_loai_sach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                //
                TextView txtMaLoai = view.findViewById(R.id.txtMaLoaiSach);
                EditText edtTenLoai = view.findViewById(R.id.edtTenLoaiSach);
                Button btnHuy = view.findViewById(R.id.btnHuyLoaiSach);
                Button btnLuu = view.findViewById(R.id.btnLuuLoaiSach);
                LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                //
                txtMaLoai.setText("Mã loại: "+loaiSach.getMaLoai()+"");
                edtTenLoai.setText(loaiSach.getTenLoai());

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTenLoai.getText().toString().trim();

                        if (ten.isEmpty()) {
                            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        loaiSach.setTenLoai(ten);

                        if(loaiSachDAO.update(loaiSach)!=-1){
                            list.clear();
                            list.addAll(loaiSachDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return false;
            }
        });
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
