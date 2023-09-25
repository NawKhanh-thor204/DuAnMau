package com.example.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pnlib.Fragment.DoanhThu;
import com.example.pnlib.Fragment.DoiMatKhau;
import com.example.pnlib.Fragment.QuanLyLoaiSach;
import com.example.pnlib.Fragment.QuanLyPhieuMuon;
import com.example.pnlib.Fragment.QuanLySach;
import com.example.pnlib.Fragment.QuanLyThanhVien;
import com.example.pnlib.Fragment.TaoTaiKhoan;
import com.example.pnlib.Fragment.Top10;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav);
        //
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Mặc định đến quản lý phiếu mượn
        FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportActionBar().setTitle(R.string.qlpm);
        fragmentManager.beginTransaction().replace(R.id.frmNav, new QuanLyPhieuMuon()).commit();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //

                //
                if (item.getItemId() == R.id.mQuanLyPhieuMuon) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new QuanLyPhieuMuon()).commit();
                }
                if (item.getItemId() == R.id.mQuanLyLoaiSach) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new QuanLyLoaiSach()).commit();
                }
                if (item.getItemId() == R.id.mQuanLySach) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new QuanLySach()).commit();
                }
                if (item.getItemId() == R.id.mQuanLyThanhVien) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new QuanLyThanhVien()).commit();
                }
                if (item.getItemId() == R.id.mTop10) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new Top10()).commit();
                }
                if (item.getItemId() == R.id.mDoanhThu) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new DoanhThu()).commit();
                }
                if (item.getItemId() == R.id.mTaoTaiKhoan) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new TaoTaiKhoan()).commit();
                }
                if (item.getItemId() == R.id.mDoiMatKhau) {
                    toolbar.setTitle(item.getTitle());
                    fragmentManager.beginTransaction().replace(R.id.frmNav, new DoiMatKhau()).commit();
                }
                if (item.getItemId() == R.id.mDangXuat) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, DangNhap.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
}