package com.example.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnlib.Dao.PhieuMuonDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Dao.ThanhVienDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.Sach;
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
        Sach sach = new Sach();
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
        holder.ibtnDeleteQLPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa phiếu này?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String maPhieu = String.valueOf(phieuMuon.getMaPM());
                        long delete = phieuMuonDAO.delete(maPhieu);
                        if (delete > 0) {
                            list.clear();
                            list.addAll(phieuMuonDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa phiếu mã số " + holder.txtMaPhieuQLPM.getText().toString() + "", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_update_phieu_muon,null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                //
                Spinner spnThanhVien = view.findViewById(R.id.spnThanhVienU);
                Spinner spnSach = view.findViewById(R.id.spnSachU);
                CheckBox chkTraSach = view.findViewById(R.id.chkTraSAch);
                Button btnHuy = view.findViewById(R.id.btnHuyPhieuMuonU);
                Button btnLuu = view.findViewById(R.id.btnLuuPhieuMuonU);
                ThanhVien thanhVien = new ThanhVien();

                //
                ArrayList<ThanhVien> dsTV = thanhVienDAO.getAll();
                ArrayList<String> newdsTV = new ArrayList<>();
                int index = 0;
                for (ThanhVien x : dsTV) {
                    newdsTV.add(x.getHoTen());
                    if (x.getMaTV() == thanhVien.getMaTV()) {
                        index = list.indexOf(x);
                    }
                }
                spnThanhVien.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1,newdsTV));
                spnThanhVien.setSelection(index);
                //
                ArrayList<Sach> dsSach = sachDAO.getAll();
                ArrayList<String> newdsSach = new ArrayList<>();
                int index1 = 0;
                for (Sach x : dsSach) {
                    newdsSach.add(x.getTenSach());
                    if (x.getMaSach() == sach.getMaSach()) {
                        index1 = list.indexOf(x);
                    }
                }
                spnSach.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_expandable_list_item_1,newdsSach));
                spnSach.setSelection(index1);
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //
                        if(chkTraSach.isChecked()){
                            phieuMuon.setTraSach(1);
                        }
                        else {
                            phieuMuon.setTraSach(0);
                        }
                        //
                        int maSach = dsSach.get(spnSach.getSelectedItemPosition()).getMaSach();
                        int maTV = dsTV.get(spnThanhVien.getSelectedItemPosition()).getMaTV();
                        int maPM = phieuMuon.getMaPM();
                        int tienThue = phieuMuon.getTienThue();
                        //
                        phieuMuon.setMaSach(maSach);
                        phieuMuon.setMaTV(maTV);
                        phieuMuon.setMaPM(maPM);
                        phieuMuon.setTienThue(tienThue);
                        if(phieuMuonDAO.update(phieuMuon)!=-1){
                            list.clear();
                            list.addAll(phieuMuonDAO.getAll());
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
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
