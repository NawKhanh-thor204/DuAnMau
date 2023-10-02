package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.PhieuMuonAdapter;
import com.example.pnlib.Adapter.SachAdapter;
import com.example.pnlib.Dao.PhieuMuonDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLyPhieuMuon extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    PhieuMuonAdapter adapter;
    private ArrayList<PhieuMuon> list = new ArrayList<PhieuMuon>();

    public QuanLyPhieuMuon() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSPM);
        fltAdd = view.findViewById(R.id.fltButtonDSPM);
        //
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = phieuMuonDAO.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}