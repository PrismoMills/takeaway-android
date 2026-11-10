package com.example.order.data;

import android.annotation.SuppressLint;

import com.example.order.R;
import com.example.order.model.Order;
import com.example.order.model.Snack;
import com.example.order.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataServer {

    private static List<String> snackOrderList;

    private static List<Snack> homeList;

    private static List<Snack> shaxianList;

    private static List<Snack> yongkangList;

    private static List<Snack> guangzhouList;

    private static List<Snack> hangzhouList;

    private static List<Snack> tongluList;

    private static List<User> accountList;

    //首页内容（图片加上解释）
    public static List<Snack> getHomeList() {
        List<Snack> featured = new ArrayList<>();
        featured.addAll(getFujianList().subList(0, 2));
        featured.addAll(getGuangxiList().subList(0, 2));
        featured.addAll(getGuangzhouList().subList(0, 2));
        return featured;
    }

    /**
     * 点菜页面分类左边列表数据
     */
    public static List<String> getSnackOrderList() {
        if (snackOrderList == null) {
            snackOrderList = new ArrayList<String>() {{
                add("ShaXian Food");
                add("YongKang Food");
                add("GuangZhou Food");
                add("HangZhou Food");
                add("TongLu Food");
            }};
        }
        return snackOrderList;
    }

    //沙县小吃
    public static List<Snack> getFujianList() {
        if (shaxianList == null) {
            shaxianList = new ArrayList<Snack>() {{
                add(new Snack("Steamed dumpling", 5.00, R.mipmap.zhengjiao, "Steamed dumplings with a savory filling."));
                add(new Snack("Dried Noodles", 5.00, R.mipmap.banmian, "Noodles tossed in a savory sauce."));
                add(new Snack("Wonton", 5.00, R.mipmap.shaxianhuntun, "Tender wontons served in a light broth."));
                add(new Snack("Fried wonton", 10.00, R.mipmap.zhahuntun, "Golden, crisp fried wontons."));
                add(new Snack("Duck leg with rice", 150.00, R.mipmap.yatuifan, "Roasted duck leg served over rice."));
            }};
        }
        return shaxianList;
    }


     //永康小吃

    public static List<Snack> getGuangxiList() {
        if (yongkangList == null) {
            yongkangList = new ArrayList<Snack>() {{
                add(new Snack("Meat loaf", 10.00, R.mipmap.roumaibing, "Pan-fried flatbread with a savory meat filling."));
                add(new Snack("Wonton", 5.00, R.mipmap.yongkanghuntun, "Yongkang-style wontons in broth."));
                add(new Snack("Burrito", 7.00, R.mipmap.juanbing, "A thin flatbread rolled around a savory filling."));
                add(new Snack("Persimmon", 10.00, R.mipmap.shizi, "Sweet, ripe Fangshan persimmon."));
            }};
        }
        return yongkangList;
    }

    //广州小吃，我也不知道有什么
    public static List<Snack> getGuangzhouList() {
        if (guangzhouList == null) {
            guangzhouList = new ArrayList<Snack>() {{
                add(new Snack("Cheung fun", 60.00, R.mipmap.changfen, "Silky Cantonese steamed rice rolls."));
                add(new Snack("Char Siu", 280.00, R.mipmap.chashao, "Cantonese-style barbecued pork."));
                add(new Snack("Chao He Fen", 80.00, R.mipmap.chaohefen, "Stir-fried wide rice noodles."));
                add(new Snack("Seafood Congee", 12.00, R.mipmap.tingzaizhou, "Rice congee with mixed seafood."));
                add(new Snack("Shrimp Dumpling", 18.00, R.mipmap.xiajiao, "Steamed shrimp dumplings with translucent wrappers."));
            }};
        }
        return guangzhouList;
    }

    //杭州小吃
    public static List<Snack> getBeijingList() {
        if (hangzhouList == null) {
            hangzhouList = new ArrayList<Snack>() {{
                add(new Snack("Steamed buns", 30, R.mipmap.xiaolongbao, "Hangzhou-style steamed soup dumplings. Enjoy carefully while hot."));
                add(new Snack("Mao er Duo", 15.8, R.mipmap.maoerduo, "Cat-ear-shaped noodles in a light broth with peas, ham and shrimp."));
            }};
        }
        return hangzhouList;
    }

    //桐庐小吃
    public static List<Snack> getChongqingList() {
        if (tongluList == null) {
            tongluList = new ArrayList<Snack>() {{
                add(new Snack("Smelly tofu", 5.00, R.mipmap.youfeimantou, "Crisp fried bun with fermented tofu and chili sauce."));
                add(new Snack("Rice wine bun", 3.00, R.mipmap.jiuniangmantou, "Soft Tonglu bun fermented with sweet rice wine."));
                add(new Snack("Egg burrito", 5.00, R.mipmap.jidanbing, "Crisp Tonglu pastry filled with egg, minced meat and scallions."));
            }};
        }
        return tongluList;
    }

    //账号信息
    public static List<User> getAccountList() {
        if (accountList == null) {
            accountList = new ArrayList<User>() {{
                add(new User("jkl", "123456", "Demo customer", R.drawable.ic_account_placeholder));
            }};
        }
        return accountList;
    }

    public static List<Order> getOrderTest() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new ArrayList<Order>() {{
            add(new Order("Sample order", R.mipmap.jiemian, 10.9, simpleDateFormat.format(new Date())));
            add(new Order("Sample order", R.mipmap.jiemian, 10.9, simpleDateFormat.format(new Date())));
            add(new Order("Sample order", R.mipmap.jiemian, 10.9, simpleDateFormat.format(new Date())));
        }};
    }
}
