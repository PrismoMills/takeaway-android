package com.example.order.model;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int image;

    private double money;

    private String time;

    private String username;
    private String batchId;
    private String notes;
    private int quantity;
    private final java.util.List<Order> items = new java.util.ArrayList<>();

    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public java.util.List<Order> getItems() { return items; }

    public Order() {
    }

    public Order(String name, int image, double money, String time) {
        this.name = name;
        this.image = image;
        this.money = money;
        this.time = time;
    }

    //订单时间
    @SuppressLint("SimpleDateFormat")
    public Order(Snack snack) {
        this.name = snack.getName();
        this.quantity = snack.getCount();
        this.image = snack.getImage();
        // 计算金额
        BigDecimal money = BigDecimal.valueOf(snack.getPrice()).multiply(BigDecimal.valueOf(snack.getCount()));
        this.money = money.doubleValue();
        // 订单产生时间（格式化）
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = simpleDateFormat.format(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }
}
