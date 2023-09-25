package com.example.pnlib.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDao {
    private final DbHelper dbHelper;
    public PhieuMuonDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getListPhieuMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT PM.MAPM, PM.MATV, TV.HOTEN, PM.MATT, TT.HOTEN," +
                " PM.MASACH,BOOK.TENSACH, PM.NGAY, PM.TRASACH, PM.TIENTHUE FROM PHIEUMUON PM," +
                " THANHVIEN TV, THUTHU TT, SACH BOOK  WHERE PM.MATV = TV.MATV and PM.MATT = TT.MATT " +
                "AND PM.MASACH = BOOK.MASACH ORDER BY PM.MAPM", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
