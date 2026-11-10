package com.example.order.dao;
// 创建数据库

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, int version) {
        super(context, "OrderStore.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createOrders = "create table orders (" +
                "id integer primary key autoincrement," +
                "name text," +
                "image integer," +
                "money real," +
                "time text," +
                "username text, batch_id text, quantity integer, notes text)";

        db.execSQL(createOrders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE orders ADD COLUMN batch_id TEXT");
            db.execSQL("ALTER TABLE orders ADD COLUMN quantity INTEGER");
            db.execSQL("ALTER TABLE orders ADD COLUMN notes TEXT");
        }

    }
}
