package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.R;

public class QuanLyLoaiSach extends Fragment {


    public QuanLyLoaiSach() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }
}