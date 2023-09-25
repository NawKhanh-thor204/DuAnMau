package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.SachAdapter;
import com.example.pnlib.Dao.SachDao;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLySach extends Fragment {

    SachDao sachDao;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    SachAdapter adapter;
    private ArrayList<Sach> list = new ArrayList<Sach>();

    public QuanLySach() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);

        recyclerView = view.findViewById(R.id.rcvDSS);
        fltAdd = view.findViewById(R.id.fltButtonDSS);

        //
        sachDao = new SachDao(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = sachDao.selectAll();
        adapter = new SachAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}