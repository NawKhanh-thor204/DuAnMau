package com.example.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Dao.SachDAO;

import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<Sach> list;
    SachDAO sachDao;
    LoaiSachDAO loaiSachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        sachDao = new SachDAO(context);
        loaiSachDAO = new LoaiSachDAO(context);
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
        Sach sach = list.get(position);
        holder.txtMaSachQLS.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSachQLS.setText(list.get(position).getTenSach());
        holder.txtGiaThueQLS.setText(String.valueOf(list.get(position).getGiaThue()));
        String tenloai = loaiSachDAO.getID(String.valueOf(sach.getMaLoai()));
        holder.txtLoaiSachQLS.setText(tenloai);
        holder.ibtnDeleteQLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa "+holder.txtTenSachQLS.getText().toString()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      String maSach = String.valueOf(sach.getMaSach());
                      long delete = sachDao.delete(maSach);
                      if(delete>0){
                          list.clear();
                          list.addAll(sachDao.getAll());
                          notifyDataSetChanged();
                          Toast.makeText(context, "Bạn đã xóa "+holder.txtTenSachQLS.getText().toString()+" ", Toast.LENGTH_SHORT).show();
                          dialog.dismiss();
                      }
                      else {
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
                View view = inflater.inflate(R.layout.dialog_sach, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                //
                EditText edtTen = view.findViewById(R.id.edtTenSach);
                EditText edtGiaThue = view.findViewById(R.id.edtGiaThue);
                Button btnHuy = view.findViewById(R.id.btnHuySach);
                Button btnLuu = view.findViewById(R.id.btnLuuSach);
                Spinner spnTenLoaiSach = view.findViewById(R.id.spnLoaiSach);
                //
                edtTen.setText(sach.getTenSach());
                edtGiaThue.setText(String.valueOf(sach.getGiaThue()));
                //
                ArrayList<LoaiSach> dsLoaiSach = loaiSachDAO.getAll();
                ArrayList<String> loaiSachls = new ArrayList<>();
                int index = 0;
                for (LoaiSach x : dsLoaiSach) {
                    loaiSachls.add(x.getTenLoai());
                    if (x.getMaLoai() == sach.getMaLoai()) {
                        index = list.indexOf(x);
                    }
                }
                spnTenLoaiSach.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1, loaiSachls));
                spnTenLoaiSach.setSelection(index);
                //
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTen.getText().toString().trim();
                        String gia = edtGiaThue.getText().toString().trim();
                        int maLoai = dsLoaiSach.get(spnTenLoaiSach.getSelectedItemPosition()).getMaLoai();
                        int maSach = sach.getMaSach();
                        if (ten.isEmpty() || gia.isEmpty()) {
                            Toast.makeText(context, "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(gia) <= 0) {
                            Toast.makeText(context, "Giá phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        sach.setTenSach(ten);
                        sach.setGiaThue(Integer.parseInt(gia));
                        sach.setMaSach(maSach);
                        sach.setMaLoai(maLoai);
                        if (sachDao.update(sach) != -1) {
                            list.clear();
                            list.addAll(sachDao.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Update thành công .", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Update thất bại .", Toast.LENGTH_SHORT).show();
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
