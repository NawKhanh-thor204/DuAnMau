package com.example.pnlib.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.PhieuMuon;
import java.util.ArrayList;


public class PhieuMuonDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;


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
