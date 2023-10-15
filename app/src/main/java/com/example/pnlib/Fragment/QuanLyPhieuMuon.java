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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pnlib.Adapter.PhieuMuonAdapter;
import com.example.pnlib.Adapter.SachAdapter;
import com.example.pnlib.Dao.PhieuMuonDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Dao.ThanhVienDAO;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class QuanLyPhieuMuon extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    PhieuMuonAdapter adapter;
    ThanhVienDAO thanhVienDAO ;

    SachDAO sachDAO ;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        PhieuMuon phieuMuon = new PhieuMuon();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = phieuMuonDAO.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_add_phieu_muon,null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                Spinner spnThanhVien = view1.findViewById(R.id.spnThanhVien);
                Spinner spnSach = view1.findViewById(R.id.spnSach);
                Button btnHuy = view1.findViewById(R.id.btnHuyPhieuMuonAdd);
                Button btnLuu = view1.findViewById(R.id.btnLuuPhieuMuonAdd);
                //
                ThanhVien thanhVien = new ThanhVien();
                Sach sach = new Sach();
                //
                thanhVienDAO = new ThanhVienDAO(getActivity());
                sachDAO = new SachDAO(getActivity());
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
                spnThanhVien.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1,newdsTV));
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
                spnSach.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1,newdsSach));
                spnSach.setSelection(index1);
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
                        int maSach = dsSach.get(spnSach.getSelectedItemPosition()).getMaSach();
                        int maTV = dsTV.get(spnThanhVien.getSelectedItemPosition()).getMaTV();
                        String ngay = sdf.format(new Date());
                        //
                        phieuMuon.setMaSach(maSach);
                        phieuMuon.setMaTV(maTV);
                        phieuMuon.setNgay(ngay);
                        if(phieuMuonDAO.insert(phieuMuon)!=-1){
                            list.clear();
                            list.addAll(phieuMuonDAO.getAll());
                            Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getActivity(), "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }
}