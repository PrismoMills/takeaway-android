package com.example.order.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * 小吃类
 */
public class Snack implements Serializable {

    private static final long serialVersionUID = 1L;

    //名称
    private String name;

    //价格
    private double price;

    //图片
    private int image;

    //介绍
    private String detail;

    //数量
    private int count;

    public Snack() {
    }

    public Snack(String name, double price, int image, String detail) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.count = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 重写equals方法 用于比较小吃是否已添加到购物车
     *
     * @param o 待比较小吃对象
     * @return 比较结果
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return image == snack.image && Objects.equals(name, snack.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image);
    }

    @NotNull
    @Override
    public String toString() {
        return "Snack{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", detail='" + detail + '\'' +
                ", count=" + count +
                '}';
    }
}
