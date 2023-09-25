package com.example.pnlib.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pnlib.Database.DbHelper;
import com.example.pnlib.Model.Sach;

import java.util.ArrayList;

public class SachDao {
    private final DbHelper dbHelper;

    public SachDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> selectAll() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                list.add(new Sach(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)
                        ));
                cursor.moveToNext();
            }
        }
        return list;
    }
}
