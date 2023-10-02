package com.example.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnlib.Dao.ThuThuDAO;

public class DangNhap extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    CheckBox chkRememberPass;
    ThuThuDAO thuThuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //
        edtTaiKhoan = findViewById(R.id.edtTaiKhoanDN);
        edtMatKhau = findViewById(R.id.edtMatKhauDN);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        thuThuDao = new ThuThuDAO(this);
        //
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rememberPass = pref.getBoolean("REMEMBER", false);

        edtTaiKhoan.setText(user);
        edtMatKhau.setText(pass);
        chkRememberPass.setChecked(rememberPass);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    public void rememberUser(String user, String pass, boolean trangThai) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (!trangThai) {
            // Xóa trạng thái trước đó
            edit.clear();
        } else {
            edit.putString("USERNAME", user);
            edit.putString("PASSWORD", pass);
            edit.putBoolean("REMEMBER", trangThai);
        }
        // Lưu toàn bộ dữ liệu
        edit.commit();
    }

    public void checkLogin() {
        String username = edtTaiKhoan.getText().toString().trim();
        String password = edtMatKhau.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
        } else {
            if (thuThuDao.checkLogin(username, password)) {
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(username, password, chkRememberPass.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}