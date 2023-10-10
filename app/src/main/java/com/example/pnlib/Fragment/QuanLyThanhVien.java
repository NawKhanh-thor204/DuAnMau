package com.example.pnlib.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pnlib.Adapter.LoaiSachAdapter;
import com.example.pnlib.Adapter.ThanhVienAdapter;
import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Dao.ThanhVienDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyThanhVien extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    ThanhVienAdapter adapter;
    private ArrayList<ThanhVien> list = new ArrayList<ThanhVien>();

    public QuanLyThanhVien() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSTV);
        fltAdd = view.findViewById(R.id.fltButtonDSTV);
        //
        thanhVienDAO = new ThanhVienDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        capNhatLv();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_add_thanh_vien, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                EditText edtTen =dialog.findViewById(R.id.edtAddHoVaTen);
                EditText edtNamSinh =dialog.findViewById(R.id.edtAddNamSinh);
                Button btnHuy = dialog.findViewById(R.id.btnHuyATV);
                Button btnLuu = dialog.findViewById(R.id.btnLuuATV);
                ThanhVien thanhVien = new ThanhVien();
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
                        String namSinh = edtNamSinh.getText().toString().trim();

                        if (ten.isEmpty()) {
                            Toast.makeText(getActivity(), "Vui lòng không bỏ trống Họ Tên", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (namSinh.isEmpty()) {
                            Toast.makeText(getActivity(), "Vui lòng không bỏ trống Năm sinh", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        thanhVien.setHoTen(ten);
                        thanhVien.setNamSinh(namSinh);

                        if (thanhVienDAO.insert(thanhVien)!=-1) {
                            list.clear();
                            list.addAll(thanhVienDAO.getAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }

    public void capNhatLv() {
        list = thanhVienDAO.getAll();
        adapter = new ThanhVienAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}