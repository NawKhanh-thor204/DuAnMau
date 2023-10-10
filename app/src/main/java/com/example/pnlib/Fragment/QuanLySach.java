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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pnlib.Adapter.SachAdapter;
import com.example.pnlib.Dao.LoaiSachDAO;
import com.example.pnlib.Dao.SachDAO;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLySach extends Fragment {

    SachDAO sachDao;
    RecyclerView recyclerView;
    FloatingActionButton fltAdd;
    SachAdapter adapter;
    LoaiSachDAO loaiSachDAO ;
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
        sachDao = new SachDAO(getActivity());
        loaiSachDAO= new LoaiSachDAO(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = sachDao.getAll();
        adapter = new SachAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_sach, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                Spinner spinner = dialog.findViewById(R.id.spnLoaiSach);
                EditText edtTenSach = dialog.findViewById(R.id.edtTenSach);
                EditText edtGiaThue = dialog.findViewById(R.id.edtGiaThue);
                Button btnHuy = dialog.findViewById(R.id.btnHuySach);
                Button btnLuu = dialog.findViewById(R.id.btnLuuSach);
                Sach sach = new Sach();
                //
                ArrayList<LoaiSach> dsLoaiSach = loaiSachDAO.getAll();
                if (dsLoaiSach.size() <= 0) {
                    Toast.makeText(getActivity(), "Vui lòng thêm loại sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<String> newDSLoaiSach = new ArrayList<>();
                for (LoaiSach x : dsLoaiSach) {
                    newDSLoaiSach.add(x.getTenLoai());
                }
                spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, newDSLoaiSach));
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edtTenSach.getText().toString().trim();
                        String giaThue = edtGiaThue.getText().toString().trim();
                        int maLoai = dsLoaiSach.get(spinner.getSelectedItemPosition()).getMaLoai();
                        if (ten.isEmpty() || giaThue.isEmpty()) {
                            Toast.makeText(getActivity(), "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(giaThue) <= 0) {
                            Toast.makeText(getActivity(), "Giá thuê phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        sach.setTenSach(ten);
                        sach.setGiaThue(Integer.parseInt(giaThue));
                        sach.setMaLoai(maLoai);
                        if(sachDao.insert(sach)!=-1){
                            list.clear();
                            list.addAll(sachDao.getAll());
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        return view;
    }
}