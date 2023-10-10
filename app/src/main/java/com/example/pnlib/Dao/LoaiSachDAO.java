package com.example.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.ThanhVien;

import java.util.ArrayList;


public class LoaiSachDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public String getID(String id) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT TENLOAI FROM LOAISACH WHERE MALOAI =?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String tenLoai = cursor.getString(0);
            cursor.close();
            return tenLoai;
        }
        cursor.close();
        return "Không tìm thấy";
    }
    public long insert(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("TENLOAI", loaiSach.getTenLoai());
        return sqLiteDatabase.insert("LOAISACH", null, values);
    }

    public long update(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("TENLOAI", loaiSach.getTenLoai());
        return sqLiteDatabase.update("LOAISACH", values, "MALOAI=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }

    public long delete(String id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("LOAISACH", "MALOAI=?", new String[]{id});
    }
    public ArrayList<LoaiSach> getAll() {
        String sql = "SELECT * FROM LOAISACH";
        return getData(sql);
    }
    private ArrayList<LoaiSach> getData(String sql, String... selectionArgs) {
        ArrayList<LoaiSach> list = new ArrayList<>();
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(cursor.getInt(0));
            loaiSach.setTenLoai(cursor.getString(1));
            list.add(loaiSach);
        }
        return list;
    }
}
