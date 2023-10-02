package com.example.pnlib.Fragment;

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

public class TaoTaiKhoan extends Fragment {
    EditText edtTaiKhoanTTK,edtHoVaTen,edtMatKhauTTK,edtNhapLaiMatKhauTTK;
    Button btnHuyLuuTTK,btnLuuTTK;
    public TaoTaiKhoan() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_tao_tai_khoan, container, false);
        //
        edtTaiKhoanTTK=view.findViewById(R.id.edtTaiKhoanTTK);
        edtHoVaTen=view.findViewById(R.id.edtHoVaTen);
        edtMatKhauTTK=view.findViewById(R.id.edtMatKhauTTK);
        edtNhapLaiMatKhauTTK=view.findViewById(R.id.edtNhapLaiMatKhauTTK);
        btnHuyLuuTTK=view.findViewById(R.id.btnHuyLuuTTK);
        btnLuuTTK=view.findViewById(R.id.btnLuuTTK);
        ThuThuDAO thuThuDAO = new ThuThuDAO(getActivity());
        //

        btnHuyLuuTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTaiKhoanTTK.setText("");
                edtHoVaTen.setText("");
                edtMatKhauTTK.setText("");
                edtNhapLaiMatKhauTTK.setText("");
            }
        });

        btnLuuTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edtTaiKhoanTTK.getText().toString());
                thuThu.setHoTen(edtHoVaTen.getText().toString());
                thuThu.setMatKhau(edtMatKhauTTK.getText().toString());
                if(validate()>0){
                    if(thuThuDAO.insert(thuThu)>0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edtTaiKhoanTTK.setText("");
                        edtHoVaTen.setText("");
                        edtMatKhauTTK.setText("");
                        edtNhapLaiMatKhauTTK.setText("");
                    }
                    else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    public int validate(){
        int check =1;
        if(edtTaiKhoanTTK.getText().length()==0||edtHoVaTen.getText().length()==0||edtMatKhauTTK.getText().length()==0||edtNhapLaiMatKhauTTK.getText().length()==0){
            Toast.makeText(getActivity(), "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        else {
            String pass = edtMatKhauTTK.getText().toString();
            String rePass = edtNhapLaiMatKhauTTK.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getActivity(), "Mật kẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}