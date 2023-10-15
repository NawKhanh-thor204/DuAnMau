package com.example.pnlib.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pnlib.Adapter.Top10Adapter;
import com.example.pnlib.Dao.ThongKeDAO;
import com.example.pnlib.Model.Sach;
import com.example.pnlib.Model.Top;
import com.example.pnlib.R;

import java.util.ArrayList;


public class Top10 extends Fragment {
    RecyclerView recyclerView;
    ThongKeDAO thongKeDAO ;
    public Top10() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_top10, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvDSTop10);
        thongKeDAO = new ThongKeDAO(getActivity());
        //
        ArrayList<Top> list = thongKeDAO.getTop();

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        Top10Adapter addapter = new Top10Adapter(getActivity(),list);
        recyclerView.setAdapter(addapter);
        return view;
    }
}