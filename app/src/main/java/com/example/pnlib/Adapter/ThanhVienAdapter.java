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
        thanhVienDAO = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_qltv, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ThanhVien thanhVien = list.get(position);
        holder.txtMaTvQLTV.setText(String.valueOf(list.get(position).getMaTV()));
        holder.txtHoTenQLTV.setText(list.get(position).getHoTen());
        holder.txtNamSinhQLTV.setText(list.get(position).getNamSinh());
        holder.ibtnDeleteQLTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa thành viên này?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String maTV = String.valueOf(thanhVien.getMaTV());
                        long delete = thanhVienDAO.delete(maTV);
                        if (delete>0) {
                            list.clear();
                            list.addAll(thanhVienDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa thành viên mã ' " + holder.txtMaTvQLTV.getText().toString() + " '!", Toast.LENGTH_SHORT).show();
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
                View view = inflater.inflate(R.layout.dialog_add_thanh_vien, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                //
                EditText edtTen = dialog.findViewById(R.id.edtAddHoVaTen);
                EditText edtNamSinh = dialog.findViewById(R.id.edtAddNamSinh);
                Button btnHuy = dialog.findViewById(R.id.btnHuyATV);
                Button btnLuu = dialog.findViewById(R.id.btnLuuATV);
                //
                edtTen.setText(thanhVien.getHoTen());
                edtNamSinh.setText(thanhVien.getNamSinh());

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTen.getText().toString().trim();
                        String namSinh = edtNamSinh.getText().toString().trim();

                        if (ten.isEmpty()) {
                            Toast.makeText(context, "Vui lòng không bỏ trống Họ Tên", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (namSinh.isEmpty()) {
                            Toast.makeText(context, "Vui lòng không bỏ trống Năm sinh", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        thanhVien.setHoTen(ten);
                        thanhVien.setNamSinh(namSinh);

                        if (thanhVienDAO.update(thanhVien)!=-1) {
                            list.clear();
                            list.addAll(thanhVienDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Updatee thất bại", Toast.LENGTH_SHORT).show();
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
