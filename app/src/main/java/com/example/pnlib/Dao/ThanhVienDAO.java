package com.example.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.ThanhVien;
import com.example.pnlib.Model.ThuThu;

import java.util.ArrayList;

public class ThanhVienDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
    }


    public long insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("HOTEN", thanhVien.getHoTen());
        values.put("NAMSINH", thanhVien.getNamSinh());
        return sqLiteDatabase.insert("THANHVIEN", null, values);
    }

    public long update(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("HOTEN", thanhVien.getHoTen());
        values.put("NAMSINH", thanhVien.getNamSinh());
        return sqLiteDatabase.update("THANHVIEN", values, "MATV=?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }

    public long delete(String id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("THANHVIEN", "MATV=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<ThanhVien> getAll() {
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }

    public String getID(String id) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT HOTEN FROM THANHVIEN WHERE MATV =?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String hoten = cursor.getString(0);
            cursor.close();
            return hoten;
        }
        cursor.close();
        return "Không tìm thấy";
    }

    private ArrayList<ThanhVien> getData(String sql, String... selectionArgs) {
        ArrayList<ThanhVien> list = new ArrayList<>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(cursor.getInt(0));
            thanhVien.setHoTen(cursor.getString(1));
            thanhVien.setNamSinh(cursor.getString(2));
            list.add(thanhVien);
        }
        return list;
    }

}
