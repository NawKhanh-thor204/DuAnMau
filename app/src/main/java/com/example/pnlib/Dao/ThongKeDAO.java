package com.example.pnlib.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private final Context context;

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Top> getTop() {
        String sqlTop = "SELECT MASACH,COUNT(MASACH) AS SOLUONG FROM PHIEUMUON GROUP BY MASACH ORDER BY SOLUONG DESC LIMIT 10";
        ArrayList<Top> list = new ArrayList<Top>();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            @SuppressLint("Range") String maSach = cursor.getString(cursor.getColumnIndex("MASACH"));
            @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("SOLUONG"));

            top.setTenSach(maSach);
            top.setSoLuong(soLuong);

            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(TIENTHUE) AS DOANHTHU FROM PHIEUMUON WHERE NGAY BETWEEN ? AND ?";
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{convertDateFormat(tuNgay), convertDateFormat(denNgay)});
        int doanhThu = 0;
        if (cursor.moveToNext()) {
            doanhThu = cursor.getInt(cursor.getColumnIndex("DOANHTHU"));
        }
        cursor.close();
        return doanhThu;
    }

    private String convertDateFormat(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }
}
