package com.example.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pnlib.Dao.ThuThuDao;

public class DangNhap extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //
        EditText edtTaiKhoan = findViewById(R.id.edtTaiKhoanDN);
        EditText edtMatKhau = findViewById(R.id.edtMatKhauDN);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);
        ThuThuDao thuThuDao = new ThuThuDao(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtTaiKhoan.getText().toString().trim();
                String password = edtMatKhau.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chinh xác!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (thuThuDao.checkLogin(username, password)) {
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangNhap.this, MainActivity.class));
                    } else {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chinh xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}