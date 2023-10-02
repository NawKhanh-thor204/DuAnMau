package com.example.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // check đăng nhập
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",
                        new String[]{username, password});
        int row = cursor.getCount();
        return (row > 0);
    }
    public long insert(ThuThu thuThu) {
        sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MATT", thuThu.getMaTT());
        values.put("HOTEN", thuThu.getHoTen());
        values.put("MATKHAU", thuThu.getMatKhau());
        return sqLiteDatabase.insert("THUTHU", null, values);
    }

    public long updatePass(ThuThu thuThu) {
        sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTEN", thuThu.getHoTen());
        values.put("MATKHAU", thuThu.getMatKhau());
        return sqLiteDatabase.update("THUTHU", values, "MATT = ?", new String[]{thuThu.getMaTT()});
    }
    public ArrayList<ThuThu> getAll() {
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM THUTHU WHERE MATT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }
    private ArrayList<ThuThu> getData(String sql, String... selectionArgs) {
        ArrayList<ThuThu> list = new ArrayList<>();
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(cursor.getString(0));
            thuThu.setHoTen(cursor.getString(1));
            thuThu.setMatKhau(cursor.getString(2));
            list.add(thuThu);
        }
        return list;
    }
}
