package com.example.pnlib.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name = "PN";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name, null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng Thủ thư
        String createTableThuThu =
                "CREATE TABLE THUTHU(MATT TEXT PRIMARY KEY," +
                " HOTEN TEXT, MATKHAU TEXT)";
        db.execSQL(createTableThuThu);

            // Tạo bảng Thành viên
            String createTableThanhVien = "CREATE TABLE THANHVIEN(MATV INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " HOTEN TEXT , NAMSINH TEXT )";
            db.execSQL(createTableThanhVien);

        // Tạo bảng Loại sách
        String createTableLLoaiSach =
                "CREATE TABLE LOAISACH(" +
                        "MALOAI INTEGER PRIMARY KEY AUTOINCREMENT, TENLOAI TEXT )";
        db.execSQL(createTableLLoaiSach);

        // Tạo bảng Sách
        String createTableSach =
                "CREATE TABLE SACH(MASACH INTEGER PRIMARY KEY AUTOINCREMENT," +
                " TENSACH TEXT , GIATHUE INTEGER ," +
                " MALOAI INTEGER REFERENCES LOAISACH(MALOAI) )";
        db.execSQL(createTableSach);

        // Tạo bảng Phiếu mượn
        String createTablePhieuMuon =
                "CREATE TABLE PHIEUMUON(MAPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MATV INTEGER REFERENCES THANHVIEN(MATV)," +
                "MATT TEXT REFERENCES THUTHU(MATT) , " +
                "MASACH INTEGER REFERENCES SACH(MASACH) , " +
                "NGAY DATE , TRASACH INTEGER , TIENTHUE INTEGER )";
        db.execSQL(createTablePhieuMuon);

        db.execSQL("INSERT INTO LOAISACH VALUES(1, 'Thiếu nhi'),(2, 'CNTT')");
        db.execSQL("INSERT INTO SACH VALUES(1, 'Doraemon',2000,1),(2, 'Android',29865,2)");
        db.execSQL("INSERT INTO THUTHU VALUES('khanhsnph43678','Sằm Nam Khánh','khanh')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Hà Ngọc Ánh','2000'),(2,'Trần Mỹ Hương','2000'),(3,'Nguyễn Ngọc Lm','2000')");
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'khanhsnph43678', 1, '19/03/2022', 1, 2500),(2,2,'khanhsnph43678', 2, '19/04/2022', 0, 2500)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
