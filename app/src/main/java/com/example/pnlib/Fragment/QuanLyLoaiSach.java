package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        View view= inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSLS);
        fltAdd = view.findViewById(R.id.fltButtonDSLS);
        //
        loaiSachDAO = new LoaiSachDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        capNhatLv();
        return view;
    }
    public void capNhatLv(){
        list = loaiSachDAO.getAll();
        adapter = new LoaiSachAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}