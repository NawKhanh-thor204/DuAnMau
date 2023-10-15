package com.example.pnlib.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnlib.Dao.ThuThuDAO;
import com.example.pnlib.Model.ThuThu;
import com.example.pnlib.R;

public class DoiMatKhau extends Fragment {
    EditText edtDoiMKCu, edtDoiMKMoi, edtNhapLaiMKMoi;
    Button btnHuyLuuMK, btnLuuMK;
    public DoiMatKhau() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        //
        edtDoiMKCu=view.findViewById(R.id.edtDoiMKCu);
        edtDoiMKMoi=view.findViewById(R.id.edtDoiMKMoi);
        edtNhapLaiMKMoi=view.findViewById(R.id.edtNhapLaiMKMoi);
        btnHuyLuuMK=view.findViewById(R.id.btnHuyLuuMK);
        btnLuuMK=view.findViewById(R.id.btnLuuMK);
        ThuThuDAO thuThuDAO = new ThuThuDAO(getActivity());
        //

        btnHuyLuuMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDoiMKCu.setText("");
                edtNhapLaiMKMoi.setText("");
                edtDoiMKMoi.setText("");
            }
        });


        btnLuuMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu = thuThuDAO.getID(user);
                    thuThu.setMatKhau(edtDoiMKMoi.getText().toString());
                    thuThuDAO.updatePass(thuThu);
                    if(thuThuDAO.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edtDoiMKCu.setText("");
                        edtNhapLaiMKMoi.setText("");
                        edtDoiMKMoi.setText("");
                    }
                    else {
                        Toast.makeText(getActivity(), "Thây đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    public int validate(){
        int check =1;
        if(edtDoiMKCu.getText().length()==0||edtDoiMKMoi.getText().length()==0||edtNhapLaiMKMoi.getText().length()==0){
            Toast.makeText(getActivity(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

            String passOld = sharedPreferences.getString("PASSWORD","");
            String passNew = edtDoiMKMoi.getText().toString();
            String rePass = edtNhapLaiMKMoi.getText().toString();
            if(!passOld.equals(edtDoiMKCu.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!passNew.equals(rePass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}