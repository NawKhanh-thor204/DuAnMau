package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        View view= inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSTV);
        fltAdd = view.findViewById(R.id.fltButtonDSTV);
        //
        thanhVienDAO = new ThanhVienDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        capNhatLv();
        return view;
    }
    public void capNhatLv() {
        list = thanhVienDAO.getAll();
        adapter = new ThanhVienAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}