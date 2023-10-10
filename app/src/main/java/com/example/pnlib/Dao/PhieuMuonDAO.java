package com.example.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.ThanhVien;

import java.util.ArrayList;


public class PhieuMuonDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("MATV", phieuMuon.getMaTV());
        values.put("MATT", phieuMuon.getMaTT());
        values.put("MASACH", phieuMuon.getMaSach());
        values.put("NGAY", phieuMuon.getNgay());
        values.put("TRASACH", phieuMuon.getTraSach());
        values.put("TIENTHUE", phieuMuon.getTienThue());
        return sqLiteDatabase.insert("PHIEUMUON", null, values);
    }

    public long update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        values.put("TRASACH", phieuMuon.getTraSach());
        return sqLiteDatabase.update("PHIEUMUON", values, "MAPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public long delete(String id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.delete("PHIEUMUON", "MAPM=?", new String[]{String.valueOf(id)});
    }
    public PhieuMuonDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PHIEUMUON";
        return getData(sql);
    }
    private ArrayList<PhieuMuon> getData(String sql, String... selectionArgs) {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(cursor.getInt(0));
            phieuMuon.setMaTV(cursor.getInt(1));
            phieuMuon.setMaTT(cursor.getString(2));
            phieuMuon.setMaSach(cursor.getInt(3));
            phieuMuon.setNgay(cursor.getString(4));
            phieuMuon.setTraSach(cursor.getInt(5));
            list.add(phieuMuon);
        }
        return list;
    }
}
