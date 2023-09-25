package com.example.pnlib.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;

public class ThuThuDao {
    private final DbHelper dbHelper;

    public ThuThuDao(Context context) {
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
}
