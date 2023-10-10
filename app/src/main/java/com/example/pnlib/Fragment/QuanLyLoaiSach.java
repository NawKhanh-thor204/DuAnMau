package com.example.pnlib.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pnlib.Adapter.LoaiSachAdapter;
import com.example.pnlib.Adapter.SachAdapter;
import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyLoaiSach extends Fragment {
    LoaiSachDAO loaiSachDAO;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    LoaiSachAdapter adapter;
    private ArrayList<LoaiSach> list = new ArrayList<LoaiSach>();

    public QuanLyLoaiSach() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSLS);
        fltAdd = view.findViewById(R.id.fltButtonDSLS);
        //
        loaiSachDAO = new LoaiSachDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        capNhatLv();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_loai_sach, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                EditText edtTenLoai = view1.findViewById(R.id.edtTenLoaiSach);
                TextView txtMaLoai = view1.findViewById(R.id.txtMaLoaiSach);
                Button btnHuy = view1.findViewById(R.id.btnHuyLoaiSach);
                Button btnLuu = view1.findViewById(R.id.btnLuuLoaiSach);
                LoaiSach loaiSach = new LoaiSach();
                //
                txtMaLoai.setText("Mã loại: ...");
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
                            Toast.makeText(getActivity(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        loaiSach.setTenLoai(ten);
                        if (loaiSachDAO.insert(loaiSach) != -1) {
                            list.clear();
                            list.addAll(loaiSachDAO.getAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }

    public void capNhatLv() {
        list = loaiSachDAO.getAll();
        adapter = new LoaiSachAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}