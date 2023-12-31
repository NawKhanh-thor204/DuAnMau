package com.example.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.LoaiSach;
import com.example.pnlib.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getAll() {
        String sql = "SELECT * FROM SACH";
        return getData(sql);
    }
    public String getID(String id) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT TENSACH FROM SACH WHERE MASACH =?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String tenSach = cursor.getString(0);
            cursor.close();
            return tenSach;
        }
        cursor.close();
        return "Không tìm thấy";
    }public int getIDmoney(int id) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT GIATHUE FROM SACH WHERE MASACH =?", new String[]{String.valueOf(id)});
        int giaThue=-1;
        if (cursor.moveToFirst()) {
            giaThue= cursor.getInt(0);
        }
        cursor.close();
        sqLiteDatabase.close();
        return giaThue;
    }
    public long insert(Sach sach) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("TENSACH", sach.getTenSach());
        values.put("GIATHUE", sach.getGiaThue());
        values.put("MALOAI", sach.getMaLoai());
        return sqLiteDatabase.insert("SACH", null, values);
    }

    public long update(Sach sach) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("TENSACH", sach.getTenSach());
        values.put("GIATHUE", sach.getGiaThue());
        values.put("MALOAI", sach.getMaLoai());
        return sqLiteDatabase.update("SACH", values, "MASACH=?", new String[]{String.valueOf(sach.getMaSach())});
    }

    public long delete(String id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("SACH", "MASACH=?", new String[]{id});
    }
    public ArrayList<Sach> getData(String sql, String... selectionArgs) {
        ArrayList<Sach> list = new ArrayList<>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            list.add(new Sach(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)
            ));
        }
        return list;
    }
}
