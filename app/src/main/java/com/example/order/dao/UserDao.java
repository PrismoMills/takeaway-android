package com.example.order.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.example.order.model.User;
import com.example.order.utils.Utils;

public class UserDao {

    // 实例化SharedPreferences对象
    private static SharedPreferences data = Utils.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);

    // Gson对象
    private static Gson gson = new Gson();

    public static User findAccount(String username) {
        String json = data.getString("account:" + username, "");
        User account = gson.fromJson(json, User.class);
        if (account != null) return account;
        String legacy = com.blankj.utilcode.util.CacheDiskUtils.getInstance().getString("User");
        account = gson.fromJson(legacy, User.class);
        return account != null && username.equals(account.getUsername()) ? account : null;
    }

    public static boolean registerAccount(User user) {
        if (findAccount(user.getUsername()) != null) return false;
        return data.edit().putString("account:" + user.getUsername(), gson.toJson(user)).commit();
    }

    public static SharedPreferences profile() {
        return Utils.getContext().getSharedPreferences("profiles", Context.MODE_PRIVATE);
    }

    public static boolean isLogin() {
        return data.getBoolean("isLogin", false);
    }

    public static void isLogin(boolean bool) {
        SharedPreferences.Editor edit = data.edit();
        edit.putBoolean("isLogin", bool);
        edit.apply();
    }

    /**
     * 获取已登录用户对象
     */
    public static User getUser() {
        String userJson = data.getString("user", "");
        return gson.fromJson(userJson, User.class);
    }

    public static void saveUser(User user) {
        String userJson = gson.toJson(user);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("user", userJson);
        edit.apply();
    }


      //退出清除

    public static void removeUserAndLoginStatus() {
        SharedPreferences.Editor edit = data.edit();
        edit.remove("user");
        edit.remove("isLogin");
        edit.apply();
    }

    public static void removeAll() {
        SharedPreferences.Editor edit = data.edit();
        edit.clear();
        edit.apply();
    }

    //保存账号信息
    public static void saveUsername(String username) {
        SharedPreferences.Editor editor = data.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public static String getUsername() {
        return data.getString("username", "");
    }
}
