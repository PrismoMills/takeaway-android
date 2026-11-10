package com.example.order.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.order.model.Order;
import com.example.order.utils.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private static final DatabaseHelper dbHelper = new DatabaseHelper(Utils.getContext(), 2);

    public static void saveOrder(List<Order> items) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (Order item : items) {
                ContentValues values = new ContentValues();
                values.put("name", item.getName());
                values.put("image", item.getImage());
                values.put("money", item.getMoney());
                values.put("time", item.getTime());
                values.put("username", item.getUsername());
                values.put("batch_id", item.getBatchId());
                values.put("quantity", item.getQuantity());
                values.put("notes", item.getNotes());
                db.insertOrThrow("orders", null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public static List<Order> findAllByUsername(String username) {
        Map<String, Order> groups = new LinkedHashMap<>();
        try (Cursor c = dbHelper.getReadableDatabase().query(
                "orders", null, "username=?", new String[]{username}, null, null, "id DESC")) {
            while (c.moveToNext()) {
                String id = c.getString(c.getColumnIndexOrThrow("batch_id"));
                String key = id == null ? "legacy:" + c.getLong(c.getColumnIndexOrThrow("id")) : id;
                String name = c.getString(c.getColumnIndexOrThrow("name"));
                String time = c.getString(c.getColumnIndexOrThrow("time"));
                double money = c.getDouble(c.getColumnIndexOrThrow("money"));
                Order group = groups.get(key);
                if (group == null) {
                    group = new Order(name, 0, 0, time);
                    group.setBatchId(id);
                    group.setNotes(c.getString(c.getColumnIndexOrThrow("notes")));
                    groups.put(key, group);
                }
                Order item = new Order(name, 0, money, time);
                item.setQuantity(c.getInt(c.getColumnIndexOrThrow("quantity")));
                group.getItems().add(0, item);
                group.setMoney(BigDecimal.valueOf(group.getMoney()).add(BigDecimal.valueOf(money)).doubleValue());
            }
        }
        return new ArrayList<>(groups.values());
    }
}
