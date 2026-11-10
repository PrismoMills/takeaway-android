package com.example.order.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.order.R;
import com.example.order.model.Order;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderAdapter(List<Order> orders) {
        super(R.layout.item_order, orders);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Order order) {
        String id = order.getBatchId();
        baseViewHolder.setText(R.id.orderName, id == null ? "Previous order" : "Order #" + id.substring(0, 8));
        baseViewHolder.setText(R.id.orderTime, "Ordered: " + order.getTime());
        StringBuilder lines = new StringBuilder();
        for (Order item : order.getItems()) {
            if (lines.length() > 0) lines.append('\n');
            if (item.getQuantity() > 0) lines.append(item.getQuantity()).append(" × ");
            lines.append(item.getName()).append("  ").append(money(item.getMoney()));
        }
        if (order.getNotes() != null && !order.getNotes().isEmpty()) {
            lines.append("\nNotes: ").append(order.getNotes());
        }
        baseViewHolder.setText(R.id.orderItems, lines.toString());
        baseViewHolder.setText(R.id.orderMoney, "Total: " + money(order.getMoney()));
    }

    private static String money(double amount) {
        return String.format(java.util.Locale.ENGLISH, "¥%.2f", amount);
    }
}
