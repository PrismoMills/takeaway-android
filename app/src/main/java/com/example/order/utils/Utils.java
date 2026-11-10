package com.example.order.utils;

import android.annotation.SuppressLint;
import android.content.Context;

public class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("This class requires an instance.");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取应用的ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("Application context is unavailable.");
    }
}
